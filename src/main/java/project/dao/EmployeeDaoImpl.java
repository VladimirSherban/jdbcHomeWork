package project.dao;

import lombok.RequiredArgsConstructor;
import project.model.City;
import project.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao {

    private final Connection connection;

    @Override
    public void addEmployee(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement
                ("INSERT INTO employee (first_name, last_name, gender, age, city_id)" + "VALUES ((?),(?),(?),(?),(?))")) {

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCity().getCityId());

            statement.execute();
            System.out.println("Новый сотрудник успешно добавлен!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Employee employee = new Employee();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT emp.first_name, emp.last_name, ct.city_name, emp.gender, emp.age, ct.city_id\n" +
                        "FROM employee emp\n" +
                        "         INNER JOIN city ct ON emp.city_id = ct.city_id where id = (?) "
        )) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setGender(resultSet.getString("gender"));
                employee.setCity(new City(resultSet.getInt("city_id"), resultSet.getString("city_name")));
            }
            if (employee.getFirstName() == null && employee.getLastName() == null && employee.getAge() == null) {
                throw new IllegalArgumentException("Сотрудника с таким id не существует");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public List<Employee> readAll() {
        List<Employee> employees = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * from employee"
        )) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employees.add(employeeFromResultSet(resultSet));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    @Override
    public void updateEmployee(Integer id, Employee employee) {
        Employee createdEmployee = getEmployeeById(id);

        if (createdEmployee != null) {
            try (PreparedStatement statement = connection.prepareStatement
                    ("Update employee SET first_name = (?), last_name = (?), gender = (?), age = (?), city_id = (?) WHERE id = (?)")) {

                statement.setString(1, employee.getFirstName());
                statement.setString(2, employee.getLastName());
                statement.setString(3, employee.getGender());
                statement.setInt(4, employee.getAge());
                statement.setInt(5, employee.getCity().getCityId());
                statement.setInt(6, id);

                statement.execute();
                System.out.println("Сотрудник изменен");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        Employee createdEmployee = getEmployeeById(id);
        if (createdEmployee != null) {
            try (PreparedStatement statement = connection.prepareStatement
                    ("DELETE from employee WHERE id = (?)")) {

                statement.setInt(1, id);
                statement.execute();
                System.out.println("Сотрудник удален");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Employee employeeFromResultSet(ResultSet resultSet) throws SQLException {
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String gender = resultSet.getString("gender");
        Integer age = resultSet.getInt("age");
        Integer cityId = resultSet.getInt("city_id");
        return new Employee(firstName, lastName, gender, age, new City(cityId));
    }
}
