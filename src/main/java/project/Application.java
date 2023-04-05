package project;

import project.dao.EmployeeDao;
import project.dao.EmployeeDaoImpl;
import project.datasourse.DataSourceManager;
import project.model.City;
import project.model.Employee;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {


        EmployeeDao employeeDao = new EmployeeDaoImpl(DataSourceManager.getConnection());

        Employee employee = new Employee("Владимир", "Владимирович", "male",
                29, new City(1));

//        employeeDao.addEmployee(employee);
        System.out.println(employeeDao.getEmployeeById(5));
        // employeeDao.updateEmployee(7,employee);
        //   employeeDao.deleteEmployeeById(7);
        System.out.println(employeeDao.readAll());


//        try (final Connection connection = getConnection();
//             PreparedStatement statement
//                     = connection.prepareStatement("SELECT emp.first_name, emp.gender, emp.last_name, ct.city_name\n" +
//                     "FROM employee emp\n" +
//                     "         LEFT JOIN city ct ON emp.city_id = ct.city_id WHERE id = (?)")) {
//
//            statement.setInt(1, 2);
//
//            final ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                String firstName = "first name: " + resultSet.getString("first_name");
//                String last_name = "last name: " + resultSet.getString("last_name");
//                String gender = "gender: " + resultSet.getString("gender");
//                String city = "city: " + resultSet.getString("city_name");
//
//                System.out.println(firstName);
//                System.out.println(last_name);
//                System.out.println(gender);
//                System.out.println(city);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static Connection getConnection() {
//        String url = "jdbc:postgresql://localhost:5433/skypro";
//        String login = "postgres";
//        String password = "S09DFV123abu1";
//
//        try {
//            return DriverManager.getConnection(url, login, password);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    }
}