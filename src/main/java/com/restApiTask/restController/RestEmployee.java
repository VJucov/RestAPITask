package com.restApiTask.restController;

import com.restApiTask.entity.EmployeeDTO;
import com.restApiTask.exceptionHandler.ExceptionsHandler;
import com.restApiTask.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/employees")
public class RestEmployee {

    private final EmployeeService employeeService;
    private final ExceptionsHandler exceptionsHandler;

    @GetMapping("/all")
    public ResponseEntity<Object> employees() throws Exception {
        return new ResponseEntity<>(employeeService.getEmployees(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeeInfo(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody EmployeeDTO employeeDTO) throws Exception {
        employeeService.addEmployee(employeeDTO);
        return new ResponseEntity<>("Employee added", CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) throws Exception {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted", OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateEmployeeInfo(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) throws Exception {
        employeeDTO.setId(id);
        employeeService.updateEmployee(employeeDTO);
        return new ResponseEntity<>("Employee updated", OK);
    }
}
