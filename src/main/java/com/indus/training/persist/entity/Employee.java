package com.indus.training.persist.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEES")
public class Employee {
	@Id
	@Column(name="EMPLOYEE_ID")
	private Integer employeeId;
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "firstName", column = @Column(name = "FIRST_NAME")),
			@AttributeOverride(name = "lastName", column = @Column(name = "LAST_NAME")) })
	private Name name;

	public Employee() {

	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

}
