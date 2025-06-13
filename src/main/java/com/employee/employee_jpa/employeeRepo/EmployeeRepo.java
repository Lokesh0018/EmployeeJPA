package com.employee.employee_jpa.employeeRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.employee_jpa.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Long>{

}
