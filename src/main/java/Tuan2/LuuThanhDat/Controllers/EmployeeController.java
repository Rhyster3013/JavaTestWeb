package Tuan2.LuuThanhDat.Controllers;


import Tuan2.LuuThanhDat.Entities.Employee;
import Tuan2.LuuThanhDat.Services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public String showAllEmployees(@NotNull Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employee/list";
    }

    @GetMapping("/add")
    public String addEmployeeForm(@NotNull Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/add";
    }
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute("employee") Employee employee) {
        if(employeeService.getEmployeeById(employee.getId()).isEmpty())
            employeeService.addEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@NotNull Model model, @PathVariable long id)
    {
        var employee = employeeService.getEmployeeById(id).orElse(null);
        model.addAttribute("employee", employee != null ? employee : new Employee());
        return "employee/edit";
    }
    @PostMapping("/edit")
    public String editEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable long id) {
        if (employeeService.getEmployeeById(id).isPresent())
            employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }
}
