package model;

public class BaseEmployee extends Employee{
	public final static int HOURS_PER_MONTH = 160;
	protected int baseSalary;

	public BaseEmployee(String name , Preference employeePreference, int baseSalary) {
		super(name ,employeePreference);
		this.baseSalary = baseSalary;
	}

	public int getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(int baseSalary) {
		this.baseSalary = baseSalary;
	}
	
}
