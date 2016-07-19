package ro.teamnet.zth.web;

import org.codehaus.jackson.map.ObjectMapper;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.appl.controller.DepartmentController;
import ro.teamnet.zth.appl.controller.EmployeeController;

import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import java.lang.Object.*;

/**
 * Created by user on 7/14/2016.
 */
public class DispatcherServlet extends HttpServlet {
    public HashMap<String, MethodAttributes> allowedMethods;
    public void init() throws ServletException {
        allowedMethods = new HashMap<String, MethodAttributes>();
        try {
            List<Class> controllers = (List<Class>) AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
            List<Class> services = (List<Class>) AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.service.impl");
            for (Class controller : controllers) {
                if (controller.isAnnotationPresent(MyController.class)) {
                    MyController annotation = (MyController) controller.getAnnotation(MyController.class);
                    String urlPath = annotation.urlPath();
                    Method[] methods = controller.getMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(MyRequestMethod.class)) {
                            MyRequestMethod methodAnnotation = method.getAnnotation(MyRequestMethod.class);
                            String methodUrlPath = methodAnnotation.urlPath();
                            String methodType = methodAnnotation.methodType();
                            MethodAttributes newMethodAttributes = new MethodAttributes();
                            newMethodAttributes.setControllerClass(controller.getName());
                            newMethodAttributes.setMethodName(method.getName());
                            newMethodAttributes.setMethodType(methodType);

                            // set the parameters
                            newMethodAttributes.setParameterList(method.getParameterTypes());
                            String finalPath = urlPath + methodUrlPath + methodAnnotation.methodType();
                            allowedMethods.put(finalPath, newMethodAttributes);
                        }
                    }
                }
            }
            } catch(ClassNotFoundException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }
        }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("GET", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("POST", req, resp);
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        dispatchReply("DELETE", req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("PUT", req, resp);
    }

    private void dispatchReply(String requestType, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object objectToReply = null;
        try {
            objectToReply = dispatch(requestType, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        reply(objectToReply, req, resp);
    }

    private Object dispatch(String requestType, HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        String urlPath = req.getPathInfo();
        MethodAttributes methodAttributes = allowedMethods.get(urlPath + requestType);
        Class controllerClass = Class.forName(methodAttributes.getControllerClass());
        Method methodToExecute = controllerClass.getMethod(methodAttributes.getMethodName(), methodAttributes.getParameterList());
        Parameter[] methodParameters = methodToExecute.getParameters();
        List<Object> methodArguments = new ArrayList<Object>();
        if (requestType.equalsIgnoreCase("post") || requestType.equalsIgnoreCase("put")) {
            String payload = req.getReader().readLine();
            Object objectData = new ObjectMapper().readValue(payload, methodToExecute.getReturnType());
            methodArguments.add(objectData);
            Object objectToReply = controllerClass.newInstance();
            objectToReply = methodToExecute.invoke(objectToReply, methodArguments.toArray());
            return objectToReply;
        }
        for (Parameter parameter : methodParameters) {
            if (parameter.isAnnotationPresent(MyRequestParam.class)) {
                MyRequestParam annotation = parameter.getAnnotation(MyRequestParam.class);
                String name = annotation.name();
                String parameterValueFromUrl = req.getParameter(name);
                Object argument = new ObjectMapper().readValue(parameterValueFromUrl, parameter.getType());
                methodArguments.add(argument);
            }
        }
        Object objectToReply = controllerClass.newInstance();
        return methodToExecute.invoke(objectToReply, methodArguments.toArray());
    }

    private void reply(Object objectToReply, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(objectToReply));
    }
}
