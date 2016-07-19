package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.service.EmployeeService;
import ro.teamnet.zth.appl.service.impl.EmployeeServiceImpl;

import java.util.List;

/**
 * Created by cizuss94 on 7/15/2016.
=======

/**
 * Created by user on 7/14/2016.
>>>>>>> 855c1a6880e16f18104918fdd2e8cbca3602e0f4
 */
@MyController(urlPath = "/employees")
public class EmployeeController {
    @MyRequestMethod(urlPath = "/all")
    public List<Employee> getAllEmployees() {
        return new EmployeeServiceImpl().findAllEmployees();
    }
    @MyRequestMethod(urlPath = "/one")
    public Employee getOneEmployee(@MyRequestParam(name = "id") Long employeeId) {
        return new EmployeeServiceImpl().findOneEmployee(employeeId);
    }
    @MyRequestMethod(urlPath = "/one", methodType = "DELETE")
    public void deleteOneEmployee(@MyRequestParam(name = "id") Long employeeId) {
        new EmployeeServiceImpl().deleteOneEmployee(employeeId);
    }
    @MyRequestMethod(urlPath = "/one", methodType = "POST")
    public void saveEmployee(@MyRequestParam(name = "firstname") String firstName,
                             @MyRequestParam(name = "lastname") String lastName)
    {
        new EmployeeServiceImpl().saveOneEmployee(firstName, lastName);
    }
}
