package project.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import project.datasourse.HibernateSessionFactoryUtil;
import project.model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao {

    private final Connection connection;

    @Override
    public void addEmployee(Employee employee) {

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            // Создаем транзакцию и начинаем ее
            Transaction transaction = session.beginTransaction();
            // вызываем на объекте сессии метод save
            // данный метод внутри себя содержит необходимый запрос к базе
            // для создания новой строки
            session.save(employee);
            // Выполняем коммит, то есть сохраняем изменения,
            // которые совершили в рамках транзакции
            transaction.commit();
            System.out.println("Новый сотрудник успешно добавлен!");

        }
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            return session.get(Employee.class, id);
        }
    }

    @Override
    public List<Employee> readAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            return session.createQuery("FROM Employee").list();
        }
    }

    @Override
    public void updateEmployee(Integer id, Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            System.out.println("сотрудник изменен");

        }
    }

    @Override
    public void deleteEmployee(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
            System.out.println("сотрудник удален");

        }
    }

    private Employee employeeFromResultSet(ResultSet resultSet) throws SQLException {
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String gender = resultSet.getString("gender");
        Integer age = resultSet.getInt("age");
        Integer cityId = resultSet.getInt("city_id");
        return new Employee(firstName, lastName, gender, age, cityId);
    }
}
