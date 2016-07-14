package ro.teamnet.zth.web;

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
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 7/14/2016.
 */
public class DispatcherServlet extends HttpServlet {
    public HashMap<String, MethodAttributes> allowedMethods;
    public void init() throws ServletException {
        allowedMethods = new HashMap<String, MethodAttributes>();
        try {
            List<Class> controllers = (List<Class>) AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
            for (Class controller: controllers) {
                if (controller.isAnnotationPresent(MyController.class)) {
                    MyController myControllerAnnotation = (MyController)controller.getAnnotation(MyController.class);
                    String urlPath = myControllerAnnotation.urlPath();
                    Method[] methods = controller.getMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(MyRequestMethod.class)) {
                            MyRequestMethod ann = method.getAnnotation(MyRequestMethod.class);
                            MethodAttributes methodAttributes = new MethodAttributes();
                            methodAttributes.setControllerClass(controller.getName());
                            methodAttributes.setMethodName(method.getName());
                            methodAttributes.setMethodType(ann.methodType());

                            allowedMethods.put(urlPath + ann.urlPath(), methodAttributes);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        dispatchReply("GET", request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        dispatchReply("POST", request, response);
    }
    private void dispatchReply(String id, HttpServletRequest request, HttpServletResponse response) {
        Object r = null;
        try {
            r = dispatch(request, response);
        }
        catch (Exception e) {
            sendExceptionError(e, request, response);
        }
        try {
            reply(r, request, response);
        } catch (IOException e) {
            sendExceptionError(e, request, response);
        }
    }
    private Object dispatch(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String pathInfo = request.getPathInfo();
        MethodAttributes methodAttributes = allowedMethods.get(pathInfo);
        Class controllerClass = Class.forName(methodAttributes.getControllerClass());
        Method methodToExecute = controllerClass.getDeclaredMethod(methodAttributes.getMethodName());
        Object objectToReturn = controllerClass.newInstance();
        objectToReturn = methodToExecute.invoke(objectToReturn);
        return objectToReturn;
    }
    private void reply(Object resp, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write(String.valueOf(resp));
    }
    private void sendExceptionError(Exception e, HttpServletRequest request, HttpServletResponse response) {

    }
}
