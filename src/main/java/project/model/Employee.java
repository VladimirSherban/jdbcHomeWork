package project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private City city;
}
