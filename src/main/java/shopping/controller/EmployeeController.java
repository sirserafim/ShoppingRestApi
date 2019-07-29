package shopping.controller;

import shopping.exception.ResourceNotFoundException;
import shopping.model.Employee;
import shopping.model.Stock;
import shopping.repository.EmployeeRepository;
import shopping.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@CrossOrigin()
@RestController
@RequestMapping("/emp")
public class EmployeeController {

   
    @Autowired
	private EmployeeService employeeService;
    
    

    
   // Get All /employees
    @GetMapping("/allemployees")
    public @ResponseBody ResponseEntity<Object> getallEmployees() {
    List<Employee> entityList = employeeService.getALLEmployees();
    return new ResponseEntity<Object>(entityList, HttpStatus.OK);
    }
  
    
 // login
    /*
    @PostMapping("/{id}/{pass}")
    public String LoginEmployee(@PathVariable(value = "id") int employeeId,
    		@PathVariable(value = "pass")  int passEmployee,
    		@Valid @RequestBody Employee employeeDetails) {
    	
    	
    	return "Everything is fine";
    }*/
    
    //create one employee
   /* @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }*/
    //add an employee
    @PostMapping("/employee")
    public @ResponseBody ResponseEntity<Object> createEmployee(@Valid @RequestBody Employee employee){
    employee = employeeService.addEmployee(employee);
    return new ResponseEntity<Object>(employee, HttpStatus.OK);
    }

 // Get a Single Employee
    @GetMapping("/employee/{id}")
    public @ResponseBody ResponseEntity<Object> getEmployeeById(@PathVariable(value = "id") int employeeId) {
    	Employee employee = employeeService.getEmployee(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
    	 return new ResponseEntity<Object>(employee, HttpStatus.OK);
    }

 // Update a Employee
    @PutMapping("/employee/{id}")
    public @ResponseBody ResponseEntity<Object> updateEmployee(@PathVariable(value = "id") int employeeId,
                                            @Valid @RequestBody Employee employeeDetails) {

        Employee employee = employeeService.getEmployee(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        employee.setName(employeeDetails.getName());
        employee.setSurname(employeeDetails.getSurname());
        employee.setAge(employeeDetails.getAge());
        employee.setPhone(employeeDetails.getPhone());
        employee.setType(employeeDetails.getType());
        employee.setShopid(employeeDetails.getShopid());
        employee.setGender(employeeDetails.getGender());
        employee.setPassword(employeeDetails.getPassword());
        
        Employee updatedEmployee = employeeService.updateEmployee(employeeId,employee);
        return new ResponseEntity<Object>(updatedEmployee, HttpStatus.OK);
    }


 // Delete a Employee
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") int employeeId) {
        Employee employee = employeeService.getEmployee(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        employeeService.deleteEmployee(employeeId);

        return ResponseEntity.ok().build();
    }

}
