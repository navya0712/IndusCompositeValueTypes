package com.indus.training.persist.dao;

import com.indus.training.persist.entity.Employee;
import com.indus.training.persist.entity.Name;
import com.indus.training.persist.exceptions.EmployeeDaoException;
import com.indus.training.persist.impl.EmployeeDaoImpl;

import junit.framework.TestCase;

public class TestEmployeeDaoImpl extends TestCase {

	private EmployeeDaoImpl empDaoObj = null;

	protected void setUp() throws Exception {
		empDaoObj = new EmployeeDaoImpl();
	}

	protected void tearDown() throws Exception {
		empDaoObj = null;
	}

	public void testInsertEmployee() {
		try {
			// Create and set up Employee and Name objects
			Employee empObj = new Employee();
			empObj.setEmployeeId(123458); // Ensure this matches the data type in your entity
			Name name = new Name();
			name.setFirstName("Navya");
			name.setLastName("Bade");
			empObj.setName(name);

			// Insert employee using DAO
			Boolean isInserted = empDaoObj.insertEmployee(empObj);
			assertTrue("Employee should be inserted successfully", isInserted);
			// Retrieve employee to verify insertion
			Employee retrievedEmployee = empDaoObj.fetchEmployeeById(123456);
			assertNotNull("Employee should not be null", retrievedEmployee);
			assertEquals("Navya", retrievedEmployee.getName().getFirstName());
			assertEquals("Bade", retrievedEmployee.getName().getLastName());

		} catch (EmployeeDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());
		}
	}

	public void testFetchEmployeeById() {
		try {
			Employee retrievedEmployee = empDaoObj.fetchEmployeeById(123456);
			assertNotNull("Employee should not be null", retrievedEmployee);
			assertEquals("Navya", retrievedEmployee.getName().getFirstName());
			assertEquals("Bade", retrievedEmployee.getName().getLastName());
		} catch (EmployeeDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());
		}
	}

	public void testUpdateEmployeeById() {
		try {
			Employee empObj = new Employee();
			empObj.setEmployeeId(123455); // Ensure this matches the data type in your entity
			Name name = new Name();
			name.setFirstName("Navya");
			name.setLastName("Bade");
			empObj.setName(name);

			// Insert employee using DAO
			boolean isInserted = empDaoObj.insertEmployee(empObj);
			assertTrue("Employee should be inserted successfully", isInserted);

			// Step 2: Update the employee details
			Name updName = new Name();
			updName.setFirstName("Teja");
			updName.setLastName("Bade");
			empObj.setName(updName);
			boolean isUpdated = empDaoObj.updateEmployeeById(123455, empObj);
			assertTrue("Employee should be updated successfully", isUpdated);

			// Step 3: Retrieve the updated employee to verify the update
			Employee updatedEmployee = empDaoObj.fetchEmployeeById(123455);
			assertNotNull("Employee should not be null after update", updatedEmployee);
			assertEquals(updName.getFirstName(), updatedEmployee.getName().getFirstName());
			assertEquals(updName.getLastName(), updatedEmployee.getName().getLastName());
		} catch (EmployeeDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());
		}
	}

	public void testDeleteEmployeeById() {
		try {
			// Step 1: Create and insert an employee
			Employee empObj = new Employee();
			empObj.setEmployeeId(123489);
			Name name = new Name();
			name.setFirstName("Navya");
			name.setLastName("Bade");
			empObj.setName(name);
			empDaoObj.insertEmployee(empObj);

			// Step 2: Delete the employee
			boolean isDeleted = empDaoObj.deleteEmployeeById(123489);

			// Step 3: Verify that the deletion was successful
			assertTrue("Employee should be deleted successfully", isDeleted);

			// Attempt to retrieve the deleted employee
			Employee deletedEmployee = empDaoObj.fetchEmployeeById(123489);
			assertNull("Employee should be null after deletion", deletedEmployee);

		} catch (EmployeeDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());
		}
	}

}
