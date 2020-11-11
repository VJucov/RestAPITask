package com.restApiTask.service;

import com.restApiTask.dao.EmployeeDAO;
import com.restApiTask.entity.Employee;
import com.restApiTask.entity.EmployeeDTO;
import com.restApiTask.exceptionHandler.CustomEmployeeServiceException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
@Data
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDAO employeeDAO;

    public List<EmployeeDTO> getEmployees() throws CustomEmployeeServiceException {
        try {
            List<EmployeeDTO> employeeDTOS = new ArrayList<>();
            for (Employee employee: employeeDAO.findAll()) {
                employeeDTOS.add(new EmployeeDTO(employee));
            }
            return employeeDTOS;
        } catch (Exception e) {
            throw new CustomEmployeeServiceException("Data Source issue, could not get the employees", INTERNAL_SERVER_ERROR);
        }
    }

    public EmployeeDTO getEmployeeById(Long id) throws CustomEmployeeServiceException {
        Optional<Employee> optionalEmployee = employeeDAO.findById(id);
        if (optionalEmployee.isPresent()) {
            return new EmployeeDTO(optionalEmployee.get());
        }
        throw new CustomEmployeeServiceException("The employee could not be found", INTERNAL_SERVER_ERROR);
    }

    public void addEmployee(EmployeeDTO employeeDTO) throws CustomEmployeeServiceException {
        try {
            Employee employee = new Employee(employeeDTO);
            employeeDAO.save(employee);
        } catch (Exception e) {
            throw new CustomEmployeeServiceException("Data Source issue, employee could not be saved", INTERNAL_SERVER_ERROR);
        }
    }

    public void updateEmployee(EmployeeDTO employeeDTO) throws CustomEmployeeServiceException {
        Optional<Employee> optionalEmployee = employeeDAO.findById(employeeDTO.getId());
        employeePresent(optionalEmployee);
        Employee toBeUpdated = optionalEmployee.get();
        toBeUpdated.setName(employeeDTO.getName());
        toBeUpdated.setSurname(employeeDTO.getSurname());
        try {
            employeeDAO.save(toBeUpdated);
        } catch (Exception e) {
            throw new CustomEmployeeServiceException("Data Source issue, employee could not be updated", INTERNAL_SERVER_ERROR);
        }
    }

    public void deleteEmployee(Long id) throws CustomEmployeeServiceException {
        try {
            employeeDAO.deleteById(id);
        } catch (Exception e) {
            throw new CustomEmployeeServiceException("Data Source issue, could not delete employee", INTERNAL_SERVER_ERROR);
        }
    }

    private void employeePresent(Optional<Employee> optionalEmployee) throws CustomEmployeeServiceException {
        if (optionalEmployee.isPresent()) {
            return;
        }
        throw new CustomEmployeeServiceException("Employee not found", INTERNAL_SERVER_ERROR);
    }

}
