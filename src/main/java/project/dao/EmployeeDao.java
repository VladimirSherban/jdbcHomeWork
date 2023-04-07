package project.dao;

import project.model.Employee;

import java.util.List;

public interface EmployeeDao {

    void addEmployee(Employee employee);

    Employee getEmployeeById(Integer id);

    List<Employee> readAll();

    void updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);
}
