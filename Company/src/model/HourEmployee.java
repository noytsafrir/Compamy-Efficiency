package model;

public class HourEmployee extends Employee {
	private int workingHours;
	private int salaryPerHour;

	public HourEmployee(String name, Preference employeePreference, int salaryPerHour) {
		super(name, employeePreference);
		this.workingHours = 0;
		this.salaryPerHour = salaryPerHour;
	}

	public int getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(int workingHours) {
		this.workingHours = workingHours;
	}

	public void addWorkingHours(int workingHoursToAdd) {
		this.workingHours += workingHoursToAdd;
	}

	public void resetWorkingHoursPerMonth() {
		this.workingHours = 0;
	}

	public int getSalaryPerHour() {
		return salaryPerHour;
	}

	public void setSalaryPerHour(int salaryPerHour) {
		this.salaryPerHour = salaryPerHour;
	}

}
