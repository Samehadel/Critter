package com.project.critter.user;

import com.project.critter.user.dto.CustomerDTO;
import com.project.critter.user.dto.EmployeeDTO;
import com.project.critter.user.dto.EmployeeRequestDTO;
import com.project.critter.user.entities.Customer;
import com.project.critter.user.entities.Employee;
import com.project.critter.user.helper.UserControllerHelper;
import com.project.critter.user.service.CustomerService;
import com.project.critter.user.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomerService customerService;


    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = UserControllerHelper.convertCustomerDto(customerDTO);

        customer = customerService.save(customer);

        return UserControllerHelper.convertCustomerEntity(customer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();

        return UserControllerHelper.convertListOfCustomerEntities(customers);
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer = customerService.findOwnerByPetId(petId);

        return UserControllerHelper.convertCustomerEntity(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = UserControllerHelper.convertEmployeeDto(employeeDTO);

        employee = employeeService.save(employee);

        return UserControllerHelper.convertEmployeeEntity(employee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.findEmployee(employeeId);

        return UserControllerHelper.convertEmployeeEntity(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setEmployeeAvailability(employeeId, daysAvailable);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeRequestDto) {
        List<Employee> availableEmployees = employeeService.assignEmployeesForService(employeeRequestDto);

        return UserControllerHelper.convertListOfEmployeeEntities(availableEmployees);
    }
}
