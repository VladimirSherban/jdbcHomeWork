package project.dao;

import project.model.City;

import java.util.List;

public interface CityDao {

    int addCity(City city);

    void updateCity(City city);

    void deleteCity(City city);

    City getCityById(Integer id);

    List<City> readAll();
}
