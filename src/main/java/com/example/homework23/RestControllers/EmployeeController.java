package com.example.homework23.RestControllers;

import com.example.homework23.Employee;
import com.example.homework23.Exceptions.WrongFirstOrLastName;
import com.example.homework23.Services.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.WrongMethodTypeException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/add")
    public Employee add(@RequestParam(value = "firstName") String firstName,
                        @RequestParam(value = "lastName") String lastName,
                        @RequestParam(value = "departmentId") int departmentId,
                        @RequestParam(value = "salary") float salary) throws WrongFirstOrLastName {
        return employeeService.add(firstName,lastName,departmentId,salary);
    }
    @GetMapping("/remove")
    public Employee remove(@RequestParam (value = "firstName") String firstName,
                           @RequestParam(value = "lastName") String lastName) throws WrongFirstOrLastName {
        return employeeService.remove(firstName,lastName);
    }
    @GetMapping("/find")
    public Employee find(@RequestParam (value = "firstName") String firstName,
                         @RequestParam(value = "lastName") String lastName) throws WrongMethodTypeException, WrongFirstOrLastName {
        return employeeService.find(firstName,lastName);
    }
}
