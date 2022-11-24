package model;

public class BaseNBonusEmployee extends BaseEmployee  {
	private int monthSalesPercent;

	public BaseNBonusEmployee(String name, Preference employeePreference, int baseSalary) {
		super(name ,employeePreference, baseSalary); 
	}
	
	public int getMonthSalesPercent() {
		return monthSalesPercent;
	}

	public void setMonthSalesPercent(int monthSalesPercent) {
		this.monthSalesPercent = monthSalesPercent;
	}

}
