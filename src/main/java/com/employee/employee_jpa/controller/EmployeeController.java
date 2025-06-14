package com.employee.employee_jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employee_jpa.employeeRepo.EmployeeRepo;
import com.employee.employee_jpa.entity.Employee;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EmployeeController {
    @Autowired
    EmployeeRepo er;

    @PostMapping("/api/employee")
    public ResponseEntity<Employee> postEmployee(@RequestBody Employee entity) {
        return new ResponseEntity<>(er.save(entity),HttpStatus.CREATED);
    }

    @GetMapping("/api/employee")
    public ResponseEntity<List<Employee>> getEmployees() {
        return new ResponseEntity<>(er.findAll(),HttpStatus.OK);
    }
    
    @GetMapping("/api/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable long id) {
        Optional<Employee> emp = er.findById(id);
        if(emp.isPresent())
            return new ResponseEntity<>(emp.get(),HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/api/employee/{id}")
    public ResponseEntity<Employee> putEmployee(@PathVariable long id,@RequestBody Employee entity) {
        Optional<Employee> emp = er.findById(id);
        if(!emp.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        emp.get().setName(entity.getName());
        emp.get().setBranch(entity.getBranch());
        emp.get().setSalary(entity.getSalary());
        return new ResponseEntity<>(er.save(emp.get()),HttpStatus.OK);
    }   

    @DeleteMapping("/api/employee/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable long id){
        Optional<Employee> emp = er.findById(id);
        if(!emp.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        er.delete(emp.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
