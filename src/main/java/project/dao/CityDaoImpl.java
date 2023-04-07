package project.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import project.datasourse.HibernateSessionFactoryUtil;
import project.model.City;

import java.util.List;

public class CityDaoImpl implements CityDao {
    @Override
    public int addCity(City city) {
        int result;

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {

            Transaction transaction = session.beginTransaction();
            result = (int) session.save(city);
            transaction.commit();
            System.out.println("Новый город успешно добавлен!");
        }
        return result;
    }

    @Override
    public City getCityById(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            return session.get(City.class, id);
        }
    }

    @Override
    public List<City> readAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            return session.createQuery("FROM City").list();
        }
    }

    @Override
    public void updateCity(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
            System.out.println("город изменен");

        }
    }

    @Override
    public void deleteCity(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
            System.out.println("город удален");

        }
    }
}
