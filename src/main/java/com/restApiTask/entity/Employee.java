package com.restApiTask.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee_table")
@NoArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Field is required")
    private String name;

    @NotNull(message = "Field is required")
    private String surname;

    public Employee(EmployeeDTO employeeDTO) {
        this.name = employeeDTO.getName();
        this.surname = employeeDTO.getSurname();
    }

}
