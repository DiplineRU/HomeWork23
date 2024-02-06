package com.example.homework23.Services;

import com.example.homework23.Employee;
import com.example.homework23.Exceptions.WrongFirstOrLastName;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class EmployeeService {
    private final Map<String, Employee> employeesList;

    public EmployeeService(Map<String, Employee> employeesList){
        this.employeesList = employeesList;
    }
    public Employee add(String firstName, String lastName, int departmentId, float salary) {
        checkLastAndFirstName(firstName,lastName);
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), departmentId, salary);
        employeesList.put(employee.toKey(), employee);
        return employee;
    }
    public Employee remove(String firstName, String lastName) {
        checkLastAndFirstName(firstName,lastName);
        Employee employee = new Employee(StringUtils.capitalize(firstName),StringUtils.capitalize(lastName));
        Employee removedEmployee = employeesList.get(lastName + firstName);
        employeesList.remove(employee.toKey());
        return removedEmployee;
    }
    public Employee find(String firstName, String lastName) {
        checkLastAndFirstName(firstName,lastName);
        Employee employee = new Employee(StringUtils.capitalize(firstName),StringUtils.capitalize(lastName));
        return employeesList.get(employee.toKey());
    }
    public Map<String,Employee> getEmployees(){
        return this.employeesList;
    }
    private static void checkLastAndFirstName(String firstName, String lastName) {
        isAlphaNumeric(firstName);
        isAlphaNumeric(lastName);
    }
    private static void isAlphaNumeric(String s) {
        if(!(s != null && s.matches("^[a-zA-Zа-яА-Я]*$"))) {
            throw new WrongFirstOrLastName();
        }
    }
}
