package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.domain.Department;

import java.util.List;

/**
 * Created by cizuss94 on 7/16/2016.
 */
public interface DepartmentService {
    List<Department> findAllDepartments();
    Department findOneDepartment(Long id);
    void deleteOneDepartment(Long id);
    void saveOneDepartment(String departmentName, Long locationId);
}
