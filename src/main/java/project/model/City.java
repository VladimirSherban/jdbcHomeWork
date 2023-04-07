package project.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "city")
public class City {

    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cityId;

    @Column(name = "city_name")
    private String cityName;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Employee> employeeList;

    public City(String cityName, List<Employee> employeeList) {
        this.cityName = cityName;
        this.employeeList = employeeList;
    }

    // должен быть лист сотрудников oneToMany с одного города может быть много сотрудников (Сити владеемая сторона емплоями)


    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
