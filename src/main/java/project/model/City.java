package project.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class City {
    private Integer cityId;
    private String cityName;

    public City(Integer cityId) {
        this.cityId = cityId;
    }
}
