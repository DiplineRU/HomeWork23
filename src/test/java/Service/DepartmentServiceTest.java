package Service;

import com.example.homework23.Employee;
import com.example.homework23.Services.DepartmentService;
import com.example.homework23.Services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    private final Collection<Employee> employees = List.of(
            new Employee("Артем", "1",1,50000),
            new Employee("Арина", "2",2,40000),
            new Employee("Катя",   "3",2,30000),
            new Employee("Дима","4",3,20000)
    );
    @Mock
    private final EmployeeService employeeService;
    @InjectMocks
    private final DepartmentService departmentService;

    @BeforeEach
    public void beforeEach() {
        when(employeeService.getEmployees()).thenReturn(employees);
    }
    @Test
    void maxSalaryPositive() {
        int expected = 50000; 
        assertThat(departmentService.maxSalary(1)).isEqualTo(expected);
    }
    @Test
    void maxSalaryNegative() {
        assertThat(departmentService.maxSalary(5)).isNull();
    }
    @Test
    void minSalaryPositive() {
        int expected = 50000;
        assertThat(departmentService.minSalary(1)).isEqualTo(expected);
    }
    @Test
    void minSalaryNegative() {
        assertThat(departmentService.minSalary(5)).isNull();
    }
    @Test
    void sumPositive(){
        int expected = 50000;
        assertThat(departmentService.sumSalary(1)).isEqualTo(expected);
    }
    @Test
    void sumNegative(){
        assertThat(departmentService.sumSalary(5)).isNull();
    }
    @Test
    void getEmployeesFromDepartment(){
        assertThat(departmentService.allForDepartments(2)).containsExactlyInAnyOrder(
                new Employee("Арина", "2",2,40000),
                new Employee("Катя",   "3",2,30000)
        );
    }
    @Test
    void getEmployeesFromDepartmentNegative(){
        assertThat(departmentService.allForDepartments(5)).isEmpty();
    }
    @Test
    void getAll(){
        assertThat(departmentService.all()).containsExactlyInAnyOrderElementsOf(
                Map.of(
                        1, List.of(new Employee("Артем", "1",1,50000)),
                        2, List.of( new Employee("Арина", "2",2,40000),new Employee("Катя","3",2,30000)),
                        3, List.of(new Employee("Дима","4",3,20000))
                )
        );
    }
}
