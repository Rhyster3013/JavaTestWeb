package Tuan2.LuuThanhDat.Services;

import Tuan2.LuuThanhDat.Entities.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final List<Employee> employees;

    public List<Employee> getAllEmployees(){
        return employees;
    }

    public Optional<Employee> getEmployeeById(Long id){
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public void updateEmployee(Employee employee) {
        var employeeOptional = getEmployeeById(employee.getId());
        if (employeeOptional.isPresent()) {
            Employee employeeUpdate = employeeOptional.get();
            employeeUpdate.setTitle(employee.getTitle());
            employeeUpdate.setPhone(employee.getPhone());
            employeeUpdate.setBirth(employee.getBirth());
        }
    }

    public void deleteEmployeeById(Long id) {
        getEmployeeById(id).ifPresent(employees::remove);
    }
}
