package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.domain.Employee;

import java.util.List;

/**
 * Created by cizuss94 on 7/16/2016.
 */
public interface EmployeeService {
    List<Employee> findAllEmployees();
    Employee findOneEmployee(Long employeeId);
    void deleteOneEmployee(Long employeeId);
    void saveOneEmployee(String firstName, String lastName);
}
