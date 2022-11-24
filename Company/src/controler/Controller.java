package controler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import exceptions.AlreadyExistException;
import exceptions.EntityIsNotExist;
import exceptions.PreferenceTimeException;
import exceptions.WorkHoursTimeException;
import listeners.CompanyModelListenable;
import listeners.ViewListenable;
import model.BaseEmployee;
import model.BaseNBonusEmployee;
import model.Company;
import model.Department;
import model.Employee;
import model.HourEmployee;
import model.Company.eDepTypes;
import model.Company.ePreferences;
import model.Company.eRolesTypes;
import model.Company.eSalaryType;
import model.Preference;
import model.Role;
import view.CompanyView;

public class Controller implements CompanyModelListenable, ViewListenable {
	private CompanyView theView;
	private Company theModel;
	public static final String FILENAME = "Company.bin";

	public Controller(CompanyView theView, Company theModel) {
		this.theView = theView;
		this.theModel = theModel;
		
		this.theView.registerListener(this);
		this.theModel.registerListener(this);
		theModel.setName("N & A company");
		theModel.setName(JOptionPane.showInputDialog(null, "Please enter your company name:"));

		try {
			hardCoded(theModel);
		} catch (AlreadyExistException | PreferenceTimeException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void viewUpdateAddDepartment(String name, eDepTypes theType, Preference workHours)
			throws AlreadyExistException {
		Department theDep = new Department(name, theType, workHours);
		theModel.addDepartment(theDep);
	}

	@Override
	public void viewUpdateAddRole(Department theDep, String name, Preference workHours, eRolesTypes theType)
			throws AlreadyExistException {
		Role theRole = new Role(name, workHours, theType);
		theModel.addRole(theRole, theDep);
	}

	@Override
	public void viewUpdateAddEmployee(eSalaryType salaryType, String name, Preference employeePreference,
			int salaryPerHour, int baseSalary) throws AlreadyExistException {
		Employee theEmployee;
		if (salaryType == eSalaryType.HoursSalary) {
			theEmployee = new HourEmployee(name, employeePreference, salaryPerHour);
		} else if (salaryType == eSalaryType.Base) {
			theEmployee = new BaseEmployee(name, employeePreference, baseSalary);
		} else {
			theEmployee = new BaseNBonusEmployee(name, employeePreference, baseSalary);
		}
		theModel.addEmployee(theEmployee);
	}

	@Override
	public Set<Department> viewUpdateGetPermanentDepartmemts() {
		Set<Department> permanentDepartments = new LinkedHashSet<Department>();
		for (Department dep : theModel.getAllDepartment()) {
			if (dep.getTheType() == eDepTypes.Permanent) {
				permanentDepartments.add(dep);
			}
		}
		return permanentDepartments;
	}

	@Override
	public Set<Department> viewUpdateGetDynamicDepartmemts() {
		Set<Department> dynamicDepartments = new LinkedHashSet<Department>();
		for (Department dep : theModel.getAllDepartment()) {
			if (dep.getTheType() != eDepTypes.Permanent) {
				dynamicDepartments.add(dep);
			}
		}
		return dynamicDepartments;
	}

	@Override
	public Set<Department> viewUpdateGetSynchronicDepartments() {
		Set<Department> synchronicDepartments = new LinkedHashSet<Department>();
		for (Department dep : theModel.getAllDepartment()) {
			if (dep.getTheType() == eDepTypes.Syncronic) {
				synchronicDepartments.add(dep);
			}
		}
		return synchronicDepartments;
	}

	@Override
	public Set<Role> viewUpdateGetDynamicRolls() {
		Set<Role> dynamicRolls = new LinkedHashSet<Role>();
		if (theModel.getAllRoles() != null) {
			for (Role r : theModel.getAllRoles()) {
				if (r.getTheType() == eRolesTypes.Dynamic) {
					dynamicRolls.add(r);
				}
			}
			return dynamicRolls;
		}
		return null;
	}

	@Override
	public Set<Department> viewUpdateGetAllDepartments() {
		return theModel.getAllDepartment();
	}

	public void viewRemoveDepartment(Department theDep) throws EntityIsNotExist {
		theModel.removeDepartment(theDep);
	}

	public void viewRemoveRoll(Department theDep, Role theRole) {
		theModel.removeRoll(theDep, theRole);
	}

	public void viewRemoveEmployee(Employee theEmp) throws EntityIsNotExist {
		theModel.removeEmployee(theEmp);
	}

	public Set<Role> viewUpdatGetAllRolles(Department theDep) {
		return theModel.getAllRollsFromDep(theDep);
	}

	public Set<Role> viewUpdatGetAllEmptyRoles(Department theDep) {
		return theModel.getAllEmptyRoles(theDep);
	}

	public static void hardCoded(Company company) throws AlreadyExistException, PreferenceTimeException {
		Department dep1 = new Department("Customer Service", eDepTypes.Permanent, null);
		Department dep2 = new Department("Manufacturing", eDepTypes.Permanent, null);
		Department dep3 = new Department("Engineering", eDepTypes.Dynamic, null);
		Department dep4 = new Department("Research", eDepTypes.Dynamic, null);
		Department dep5 = new Department("Marketing", eDepTypes.Syncronic, new Preference(ePreferences.Original));
		Department dep6 = new Department("Human Resources", eDepTypes.Syncronic,new Preference(ePreferences.Later, LocalTime.of(9, 0)));
		Department dep7 = new Department("Sales", eDepTypes.Permanent, null);
		Department dep8 = new Department("Kitchen", eDepTypes.Dynamic, null);
		Department dep9 = new Department("Waiters", eDepTypes.Dynamic, null);
		Department dep10 = new Department("Bar", eDepTypes.Dynamic, null);
		Department dep11 = new Department("cleaning", eDepTypes.Syncronic, new Preference(ePreferences.Earlier,LocalTime.of(5, 0)));
		Department dep12 = new Department("Algoritem", eDepTypes.Syncronic,new Preference(ePreferences.Later, LocalTime.of(8, 0)));

		company.addDepartment(dep1);
		company.addDepartment(dep2);
		company.addDepartment(dep3);
		company.addDepartment(dep4);
		company.addDepartment(dep5);
		company.addDepartment(dep6);
		company.addDepartment(dep7);
		company.addDepartment(dep8);
		company.addDepartment(dep9);
		company.addDepartment(dep10);
		company.addDepartment(dep11);
		company.addDepartment(dep12);

		Role role1 = new Role("Manager", new Preference(ePreferences.Original), eRolesTypes.Permanent);
		Role role2 = new Role("Operators", new Preference(ePreferences.Original), eRolesTypes.Permanent);
		Role role3 = new Role("Computer Engineer", new Preference(ePreferences.Home), eRolesTypes.Dynamic);
		Role role4 = new Role("Scientist", new Preference(ePreferences.Home), eRolesTypes.Dynamic);
		Role role5 = new Role("Seller", new Preference(ePreferences.Original), eRolesTypes.Dynamic);
		Role role6 = new Role("secretary", new Preference(ePreferences.Later, LocalTime.of(9, 0)), eRolesTypes.Dynamic);
		Role role7 = new Role("Teacher", new Preference(ePreferences.Later,LocalTime.of(8, 0)), eRolesTypes.Dynamic);
		Role role8 = new Role("Cyber", new Preference(ePreferences.Later, LocalTime.of(10, 0)), eRolesTypes.Dynamic);
		Role role9 = new Role("Barista", new Preference(ePreferences.Original), eRolesTypes.Dynamic);
		Role role10 = new Role("Barmen", new Preference(ePreferences.Earlier, LocalTime.of(5, 0)), eRolesTypes.Dynamic);
		Role role11 = new Role("Dish washer", new Preference(ePreferences.Later,LocalTime.of(8, 0)), eRolesTypes.Dynamic);
		Role role12 = new Role("Shef", new Preference(ePreferences.Later ,LocalTime.of(11, 0)), eRolesTypes.Dynamic);
		Role role13 = new Role("cleaner", new Preference(ePreferences.Earlier, LocalTime.of(6, 0)), eRolesTypes.Dynamic);
		Role role14 = new Role("Full stack", new Preference(ePreferences.Later, LocalTime.of(8, 0)), eRolesTypes.Dynamic);
		Role role15 = new Role("Toilet cleaner", new Preference(ePreferences.Earlier , LocalTime.of(5, 0)), eRolesTypes.Dynamic);
		Role role16 = new Role("QA", new Preference(ePreferences.Later, LocalTime.of(8, 0)), eRolesTypes.Dynamic);

		company.addRole(role1, dep1);
		company.addRole(role2, dep2);
		company.addRole(role3, dep3);
		company.addRole(role4, dep4);
		company.addRole(role5, dep5);
		company.addRole(role6, dep6);
		company.addRole(role7, dep12);
		company.addRole(role8, dep3);
		company.addRole(role9, dep10);
		company.addRole(role10, dep10);
		company.addRole(role14, dep12);
		company.addRole(role12, dep8);
		company.addRole(role13, dep8);
		company.addRole(role11, dep8);
		company.addRole(role15, dep11);
		company.addRole(role16, dep12);

		Employee E1 = new BaseEmployee("Adam", new Preference(ePreferences.Later,  LocalTime.of(10, 0)), 25000);
		Employee E2 = new BaseEmployee("Beni", new Preference(ePreferences.Original), 7000);
		Employee E3 = new BaseNBonusEmployee("Sapir", new Preference(ePreferences.Home), 17000);
		Employee E4 = new BaseNBonusEmployee("Amit", new Preference(ePreferences.Home), 10000);
		Employee E5 = new HourEmployee("Ron", new Preference(ePreferences.Later, LocalTime.of(9, 0)), 50);
		Employee E6 = new HourEmployee("Shani", new Preference(ePreferences.Earlier, LocalTime.of(6, 0)), 70);
		Employee E7 = new BaseEmployee("Koby", new Preference(ePreferences.Later, LocalTime.of(11, 0)), 25000);
		Employee E8 = new BaseEmployee("Ronaldo", new Preference(ePreferences.Later, LocalTime.of(10, 0)), 7000);
		Employee E9 = new BaseNBonusEmployee("James", new Preference(ePreferences.Earlier, LocalTime.of(7, 0)), 17000);
		Employee E10 = new BaseNBonusEmployee("Curry", new Preference(ePreferences.Home), 10000);
		Employee E11 = new HourEmployee("Damian", new Preference(ePreferences.Later, LocalTime.of(9, 0)), 50);
		Employee E12 = new HourEmployee("Tim", new Preference(ePreferences.Earlier, LocalTime.of(6, 0)), 70);
		Employee E13 = new BaseNBonusEmployee("Tamar", new Preference(ePreferences.Original), 10000);
		Employee E14 = new HourEmployee("Dori", new Preference(ePreferences.Later, LocalTime.of(11, 0)), 50);
		Employee E15 = new HourEmployee("Boaz", new Preference(ePreferences.Earlier, LocalTime.of(5, 0)), 70);

		company.addEmployee(E1);
		company.addEmployee(E2);
		company.addEmployee(E3);
		company.addEmployee(E5);
		company.addEmployee(E6);
		company.addEmployee(E7);
		company.addEmployee(E4);
		company.addEmployee(E8);
		company.addEmployee(E9);
		company.addEmployee(E10);
		company.addEmployee(E11);
		company.addEmployee(E12);
		company.addEmployee(E13);
		company.addEmployee(E14);
		company.addEmployee(E15);
		
		company.setRollAndEployee(E1, role1);
		company.setRollAndEployee(E2, role2);
		company.setRollAndEployee(E3, role7);
		company.setRollAndEployee(E4, role4);
		company.setRollAndEployee(E5, role5);
		company.setRollAndEployee(E6, role15);
		company.setRollAndEployee(E7, role3);
		company.setRollAndEployee(E8, role9);
		company.setRollAndEployee(E9, role6);
		company.setRollAndEployee(E10, role10);
		company.setRollAndEployee(E11, role16);
	}
	
	public void readFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(FILENAME));
		theModel = (Company) inFile.readObject();
		inFile.close();
		this.theModel.registerListener(this);
	}

	public void saveToFile() throws FileNotFoundException, IOException {
		ObjectOutputStream OutFile = new ObjectOutputStream(new FileOutputStream(Controller.FILENAME));
		OutFile.writeObject(theModel);
		OutFile.close();
	}

	@Override
	public Set<Employee> viewUpdateGetAllAvailableEmployees() {
		return theModel.getAllAvailableEmployees();
	}

	@Override
	public Set<Employee> viewUpdateGetAllEmployees() {
		return theModel.getAllEmployees();
	}

	@Override
	public void updateModelAboutLinkEmployeeAndRoll(Employee theEmployee, Role theRole) {
		theModel.setRollAndEployee(theEmployee, theRole);
	}

	@Override
	public void modelUpdateLinkRollAndEmployee(Employee theEmployee, Role theRole) {
		theView.LinkEmployeeAndRoll(theEmployee, theRole);
	}

	@Override
	public void updateModelAboutRemoveEmployeeFromRoll(Employee theEmployee, Role theRole) {
		theModel.setEmployeeRoll(theEmployee, theRole);
	}

	@Override
	public void modelUpdateRemoveEmployeeFromRoll(Employee theEmployee, Role theRole) {
		theView.setEmployeeRoll(theEmployee, theRole);
	}

	@Override
	public void clearEmployeeFromRole(Role theRole) {
		theModel.setRollNoEmployee(theRole);
	}

	@Override
	public void modelUpdateClearEmployeeFromTheRoll(Role theRole) {
		theView.cleanEmployeeFromARoll(theRole);
	}

	@Override
	public void viewUpdatechangeDepartmentHours(Department theDep, Preference thePref) throws WorkHoursTimeException {
		theModel.setDepHours(theDep, thePref);
	}

	@Override
	public void viewUpdatechangeRollHours(Role theRole, Preference thePref) {
		theModel.setRollHours(theRole, thePref);
	}

	@Override
	public double viewUpdateGetCompanyEfficiency() {
		return theModel.calcCompanyEfficient();
	}
	
	@Override
	public double viewUpdateGetCompanyProfit() {
		return theModel.calcCompanyProfit();
	}

	public Set<Employee> viewUpdateGetAllEmployeesWhoHaveRole() {
		return theModel.getAllEmployeeWithRole();
	}
	
	public void modelUpdateNameHasChanged() {
		theView.setHeadLineDesign();
	}

	public String getNameFromModel() {
		return theModel.getName();
	}

}