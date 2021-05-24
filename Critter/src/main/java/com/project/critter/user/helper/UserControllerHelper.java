package com.project.critter.user.helper;

import com.project.critter.pet.Pet;
import com.project.critter.user.dto.CustomerDTO;
import com.project.critter.user.dto.EmployeeDTO;
import com.project.critter.user.entities.Customer;
import com.project.critter.user.entities.Employee;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class UserControllerHelper {

    /*
    Employee Conversion Methods
        convertEmployeeDto: Converts EmployeeDto to Employee entity
        convertEmployeeEntity: Converts Employee entity to EmployeeDto
        convertListOfEmployeeEntities: Converts a list of Employee to a list of EmployeeDto
     */
    public static Employee convertEmployeeDto(EmployeeDTO employeeDto){
        Employee employee = new Employee();

        BeanUtils.copyProperties(employeeDto, employee);

        return employee;
    }
    public static EmployeeDTO convertEmployeeEntity(Employee employee){
        EmployeeDTO employeeDto = new EmployeeDTO();

        BeanUtils.copyProperties(employee, employeeDto);

        return employeeDto;
    }
    public static List<EmployeeDTO> convertListOfEmployeeEntities(List<Employee> employees){
        List<EmployeeDTO> employeeDtos = new ArrayList<>();
        for(Employee emp: employees){
            employeeDtos.add(convertEmployeeEntity(emp));
        }

        return employeeDtos;
    }

    /*
    Customer Conversion Methods
        convertCustomerDto: Converts CustomerDto to Customer entity
        convertCustomerEntity: Converts Customer entity to CustomerDto
        convertListOfCustomerEntities: Converts a list of Customer to a list of CustomerDto
     */
    public static Customer convertCustomerDto(CustomerDTO customerDto){
        Customer customer = new Customer();

        BeanUtils.copyProperties(customerDto, customer);

        return customer;
    }
    public static CustomerDTO convertCustomerEntity(Customer customer){
        CustomerDTO customerDto = new CustomerDTO();

        BeanUtils.copyProperties(customer, customerDto);

        for(Pet pet: customer.getPets()){
            customerDto.getPetIds().add(pet.getId());
        }

        return customerDto;
    }
    public static List<CustomerDTO> convertListOfCustomerEntities(List<Customer> customers){
        List<CustomerDTO> customerDtos = new ArrayList<>();
        for(Customer cust: customers){
            customerDtos.add(convertCustomerEntity(cust));
        }

        return customerDtos;
    }
}
