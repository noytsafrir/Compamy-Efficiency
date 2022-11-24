package listeners;

import model.Department;
import model.Employee;
import model.Preference;
import model.Role;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import exceptions.AlreadyExistException;
import exceptions.EntityIsNotExist;
import exceptions.WorkHoursTimeException;
import model.Company.eDepTypes;
import model.Company.eRolesTypes;
import model.Company.eSalaryType;

public interface ViewListenable {
	public void viewUpdateAddDepartment(String name, eDepTypes theType, Preference workHours)
			throws AlreadyExistException;

	public void viewUpdateAddRole(Department theDep, String name, Preference workHours, eRolesTypes theType)
			throws AlreadyExistException;

	public void viewUpdateAddEmployee(eSalaryType salaryType, String name, Preference employeePreference,
			int salaryPerHour, int baseSalary) throws AlreadyExistException;

	public Set<Department> viewUpdateGetPermanentDepartmemts();

	public Set<Department> viewUpdateGetDynamicDepartmemts();

	public Set<Department> viewUpdateGetSynchronicDepartments();

	public Set<Department> viewUpdateGetAllDepartments();

	public Set<Role> viewUpdateGetDynamicRolls();

	public void viewRemoveDepartment(Department d) throws EntityIsNotExist;

	public void viewRemoveRoll(Department d, Role r) ;

	public void viewRemoveEmployee(Employee e) throws EntityIsNotExist;

	public Set<Role> viewUpdatGetAllRolles(Department theDep);

	public Set<Role> viewUpdatGetAllEmptyRoles(Department theDep);

	public Set<Employee> viewUpdateGetAllAvailableEmployees();

	public Set<Employee> viewUpdateGetAllEmployees();

	public void updateModelAboutLinkEmployeeAndRoll(Employee theEmployee, Role theRole);

	public void updateModelAboutRemoveEmployeeFromRoll(Employee theEmployee, Role theRole);

	public void clearEmployeeFromRole(Role theRole);

	public void viewUpdatechangeDepartmentHours(Department theDep, Preference thePref) throws WorkHoursTimeException;

	public void viewUpdatechangeRollHours(Role theRole, Preference thePref);

	public double viewUpdateGetCompanyEfficiency();
	
	public double viewUpdateGetCompanyProfit();

	public Set<Employee> viewUpdateGetAllEmployeesWhoHaveRole();

	public void readFromFile() throws FileNotFoundException, IOException, ClassNotFoundException;
	
	public void saveToFile() throws FileNotFoundException, IOException ; 

	public String getNameFromModel();
}
