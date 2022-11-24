package model;

import java.io.Serializable;

import model.Company.ePreferences;
import model.Company.eRolesTypes;

public class Role implements ChangeHourable, Serializable{
	private String name;
	private Employee theEmployee;
	private Department theDepartment;
	public static int ID_NUM = 1000;
	private int idRole;
	private Preference workHours;
	private eRolesTypes theType;
	public static final Double EFFICIENCY = 0.2;
	private final Double HOME_EFFICIENCY = 0.1;
	private final int HOME_BINGO = 8; //8 hours of 0.1 efficiency
	private final int HOME_NOT_BINGO = -8; // 8 hours of minus 0.1 efficiency

	public Role(String name, Preference workHours , eRolesTypes theType) {
		this.name = name;
		this.workHours = workHours;
		idRole = ID_NUM++;
		this.theType = theType;
		this.theEmployee = null;
	}

	public Department getTheDepartment() {
		return theDepartment;
	}


	public void setTheDepartment(Department theDepartment) {
		this.theDepartment = theDepartment;
	}


	public String getName() {
		return name;
	}

	public Employee getTheEmployee() {
		return theEmployee;
	}

	public int getIdRole() {
		return idRole;
	}

	public Preference getWorkHours() {
		return workHours;
	}

	public eRolesTypes getTheType() {
		return theType;
	}

	//##########################################################################################//
	public void setEmployee(Employee theEmployee) {
		this.theEmployee = theEmployee;
	}

	public int diffHour() { // if the employee is less efficient --> then the function return negative value
		if (this.theEmployee == null)
			return 0;
		int diffBetweenStartHourToPref = 0;
		int diffBetweenCurrentHourToPref = 0;
		int efficiencyHours = 0;
		ePreferences employeePreference = theEmployee.thePreference.getThePreference();
		ePreferences roleDemand = workHours.getThePreference();

		if (employeePreference == ePreferences.Home && roleDemand == ePreferences.Home)// If the preference of the employee and the role is home
			return HOME_BINGO;
		else if (employeePreference == ePreferences.Home ^ roleDemand == ePreferences.Home)// one preference is home
			return HOME_NOT_BINGO;
		else if (roleDemand == ePreferences.Earlier && employeePreference == ePreferences.Earlier) {
			diffBetweenCurrentHourToPref = Math.abs(workHours.startHourPreference.getHour()
					- theEmployee.thePreference.startHourPreference.getHour());
			diffBetweenCurrentHourToPref = -1 * diffBetweenCurrentHourToPref;
			if(workHours.startHourPreference.getHour() < theEmployee.thePreference.startHourPreference.getHour())		//8-who is bigger
				diffBetweenStartHourToPref = Math.abs(Company.REGULAR_START_HOUR.getHour()
						- theEmployee.thePreference.startHourPreference.getHour());
			else
				diffBetweenStartHourToPref = Math.abs(Company.REGULAR_START_HOUR.getHour()
						- workHours.startHourPreference.getHour());
			efficiencyHours = diffBetweenStartHourToPref + diffBetweenCurrentHourToPref;
			return efficiencyHours;
		} else if (roleDemand == ePreferences.Later && employeePreference == ePreferences.Later) {
			diffBetweenCurrentHourToPref = Math.abs(theEmployee.thePreference.startHourPreference.getHour()
					- workHours.startHourPreference.getHour());
			diffBetweenCurrentHourToPref = -1 * diffBetweenCurrentHourToPref;
			if(workHours.startHourPreference.getHour() > theEmployee.thePreference.startHourPreference.getHour()) //8-who is bigger
				diffBetweenStartHourToPref = Math.abs(Company.REGULAR_START_HOUR.getHour()
						- theEmployee.thePreference.startHourPreference.getHour());
			else
				diffBetweenStartHourToPref = Math.abs(Company.REGULAR_START_HOUR.getHour()
						- workHours.startHourPreference.getHour());
			efficiencyHours = diffBetweenStartHourToPref + diffBetweenCurrentHourToPref;
			return efficiencyHours;
		} else if (employeePreference == ePreferences.Original && roleDemand == ePreferences.Original)
			return 0;
		else {
			diffBetweenStartHourToPref = Math.abs(this.workHours.startHourPreference.getHour()
					- theEmployee.getEmployeePreference().startHourPreference.getHour());
			diffBetweenStartHourToPref = 0 - diffBetweenStartHourToPref;
		}
		return diffBetweenStartHourToPref;
	}

	public double calcEfficient() {
		int diffHour = diffHour();
		if (diffHour == HOME_BINGO ) {
			return HOME_BINGO * HOME_EFFICIENCY;
		}
		else if (diffHour == HOME_NOT_BINGO)
			return HOME_NOT_BINGO * HOME_EFFICIENCY;
		return diffHour * EFFICIENCY;
	}

	public double calcRoleProfit() {
		if(theEmployee != null) {
			if(theEmployee.getClass().getSimpleName().equals("HourEmployee")) {
				return calcEfficient() * ((HourEmployee)theEmployee).getSalaryPerHour();
			}
			else
				return calcEfficient() * (((BaseEmployee)theEmployee).getBaseSalary() / BaseEmployee.HOURS_PER_MONTH);
		}
		else
			return 0;
	}

	protected void setCurrentWorkHours(Preference currentWorkHours) {
		this.workHours = currentWorkHours;
	}

	@Override
	public boolean isHoursChangeable() {
		return (this.theType == eRolesTypes.Dynamic);
	}

	@Override
	public String toString() {
		return idRole + " " + name;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Role))
			return false;

		Role temp = (Role) other;
		return this.name.equals(temp.name) && this.idRole == temp.idRole;
	}
}
