package model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Vector;

import exceptions.AlreadyExistException;
import exceptions.WorkHoursTimeException;
import listeners.CompanyModelListenable;

public class Company implements Serializable {
	private transient Vector<CompanyModelListenable> allListeners;

	private String name;
	public enum ePreferences {
		Home, Earlier, Later, Original 
	};

	public enum eRolesTypes {
		Permanent, Dynamic
	};

	public enum eDepTypes {
		Permanent, Dynamic, Syncronic
	};

	public enum eSalaryType {
		BaseNBonus, Base, HoursSalary
	};

	public final static LocalTime REGULAR_START_HOUR = LocalTime.of(8, 0);
	public final static int WORKING_HOURS_PER_DAY = 8; // 1 hour of break
	private Set<Department> allDepartment;
	public Set<Role> allRoles;
	private Set<Employee> allEmployees;

	public Company(String name) {
		this.allListeners = new Vector<CompanyModelListenable>();
		this.name = name;
		this.allDepartment = new LinkedHashSet<Department>();
		this.allEmployees = new LinkedHashSet<Employee>();
		this.allRoles = new LinkedHashSet<Role>();
	}

	public void addDepartment(Department newDep) throws AlreadyExistException {
		boolean isExist = allDepartment.add(newDep);
		if (!isExist) {
			throw new AlreadyExistException("The Department is already exist");
		}
	}

	public void addRole(Role newRole, Department theDep) throws AlreadyExistException {
		theDep.addRole(newRole);
		allRoles.add(newRole);
		newRole.setTheDepartment(theDep);
	}

	public void addEmployee(Employee theEmployee) throws AlreadyExistException {
		boolean isExist = allEmployees.add(theEmployee);
		if (!isExist) {
			throw new AlreadyExistException("The Employee is already exist in the System");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		allListeners.get(0).modelUpdateNameHasChanged();
	}

	public Set<Role> getAllRoles() {
		return allRoles;
	}

	public Set<Employee> getAllEmployees() {
		return allEmployees;
	}

	public Set<Department> getAllDepartment() {
		return allDepartment;
	}

	public Set<Role> getAllRollsFromDep(Department theDep) {
		return theDep.getAllRoles();
	}

	public Set<Role> getAllEmptyRoles(Department theDep) {
		Set<Role> emptyRoles = new LinkedHashSet<Role>();
		for (Role r : getAllRollsFromDep(theDep)) {
			if (r.getTheEmployee() == null)
				emptyRoles.add(r);
		}
		return emptyRoles;
	}

	public Set<Employee> getAllAvailableEmployees() {
		Set<Employee> allAvailableEmployees = new LinkedHashSet<Employee>();
		for (Employee e : allEmployees) {
			if (e.theRole == null) {
				allAvailableEmployees.add(e);
			}
		}
		return allAvailableEmployees;
	}

	public void registerListener(CompanyModelListenable l) {
		if(allListeners == null) {
			allListeners = new Vector<CompanyModelListenable>();
		}
		allListeners.add(l);
	}

	public void setDepHours(Department theDep, Preference thePref) throws WorkHoursTimeException {
		theDep.setDepartmentHours(thePref);
	}

	public void setRollHours(Role theRole, Preference thePref) {
		theRole.setCurrentWorkHours(thePref);
	}

	public void setRollAndEployee(Employee theEmployee, Role theRoll) {
		for (CompanyModelListenable l : allListeners) {
			l.modelUpdateLinkRollAndEmployee(theEmployee, theRoll);
		}
	}

	public void setEmployeeRoll(Employee theEmployee, Role theRoll) {
		for (CompanyModelListenable l : allListeners) {
			l.modelUpdateRemoveEmployeeFromRoll(theEmployee, theRoll);
		}
	}

	public void setRollNoEmployee(Role theRoll) {
		for (CompanyModelListenable l : allListeners) {
			l.modelUpdateClearEmployeeFromTheRoll(theRoll);
		}
	}

	public double calcCompanyEfficient() {
		double sum = 0;
		for (Department theDep : allDepartment) { // calc method + add to sum
			sum += theDep.calcDepartmentEfficient();
		}
		return sum;
	}
	
	public double calcCompanyProfit() {
		double profit = 0;
		for (Role theRole : allRoles) { // calc method + add to sum
			profit += theRole.calcRoleProfit();
		}
		return profit;
	}
	
	
	public Set<Employee> getAllEmployeeWithRole() {
		Set<Employee> allEmployeesWithRole = new LinkedHashSet<Employee>();
		for (Employee emp : getAllEmployees()) {
			if (emp.theRole != null)
				allEmployeesWithRole.add(emp);
		}
		return allEmployeesWithRole;
	}
	public void removeDepartment(Department theDep) {
		allDepartment.remove(theDep);
	}

	public void removeRoll(Department theDep, Role theRole) {
		if(theRole.getTheEmployee() != null) {
			theRole.getTheEmployee().removeRole();
			getAllRollsFromDep(theDep).remove(theRole);
		}
	}

	public void removeEmployee(Employee theEmp) {
		if(theEmp.getTheRole() != null) {
			theEmp.getTheRole().setEmployee(null);
		}
		allEmployees.remove(theEmp);
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Company))
			return false;
		Company temp = (Company) other;
		return this.name.equals(temp.name);
	}
}