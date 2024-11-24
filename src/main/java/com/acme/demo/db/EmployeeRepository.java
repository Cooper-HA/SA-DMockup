package com.acme.demo.db;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import com.acme.demo.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Serializable>{

}
