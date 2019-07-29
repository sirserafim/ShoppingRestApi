package shopping.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.exception.ResourceNotFoundException;
import shopping.model.Employee;
import shopping.repository.EmployeeRepository;




@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository; 
	
	
	
	public List <Employee> getALLEmployees(){
		List <Employee> employees = new ArrayList<>();
		employeeRepository.findAll()
		.forEach(employees::add);
		return employees;
	}
	
	public Optional<Employee> getEmployee(long id) 
	{
	//return	employees.stream().filter(emp -> emp.getId().equals(id)).findFirst().get();	
		return	employeeRepository.findById(id);
	}
	
	public List<Employee> getEmployeeName(String name) 
	{
	//return	employees.stream().filter(emp -> emp.getId().equals(id)).findFirst().get();	
		return	employeeRepository.findByName(name);
	}
	
	public String getEmployeeType(long id) 
	{
	//return	employees.stream().filter(emp -> emp.getId().equals(id)).findFirst().get();
		Employee employee =  employeeRepository.findById(id).get();
		return	 employee.getType();
		
	}
	
	public Employee addEmployee (Employee employee) {
	return	employeeRepository.save(employee);
	}
	
	public Employee updateEmployee(long id, Employee emlpoyee) {
	return	employeeRepository.save(emlpoyee);
	}

	public void  deleteEmployee(long id) {
		
		employeeRepository.deleteById(id);
	}
	
}
