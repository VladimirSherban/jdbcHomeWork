package project;

import project.dao.CityDao;
import project.dao.CityDaoImpl;
import project.dao.EmployeeDao;
import project.dao.EmployeeDaoImpl;
import project.model.City;
import project.model.Employee;



public class Application {
    public static void main(String[] args)  {


        EmployeeDao employeeDao = new EmployeeDaoImpl();
        CityDao cityDao = new CityDaoImpl();

        City city = cityDao.getCityById(cityDao.addCity(new City("Ростов2222", null)));

        Employee employee = new Employee("Tom", "Владимирович", "male",
                29, city);

        employeeDao.addEmployee(employee);

        System.out.println("ПРОВЕРКА СОЗДАНИЯ ГОРОДА");
        cityDao.readAll().forEach(System.out::println);

        System.out.println("ПРОВЕРКА СОЗДАНИЯ СОТРУДНИКА");
        employeeDao.readAll().forEach(System.out::println);

        cityDao.deleteCity(city);

        System.out.println("Конец работы");


    }
}