package model;

import java.io.Serializable;

import exceptions.AlreadyExistException;

public abstract class Employee implements Serializable{
	protected String name;
	public static int ID_NUM = 10000;
	protected int idEmployee;	
	protected Preference thePreference;
	protected double extraEfficiency;
	protected Role theRole;
	
	public Employee(String name, Preference thePreference) {
		this.name = name;
		idEmployee = ID_NUM++;
		this.thePreference = thePreference;
	}

	public Preference getEmployeePreference() {
		return thePreference;
	}

	public void setEmployeePreference(Preference thePreference) {
		this.thePreference = thePreference;
	}

	public double getExtraEfficiency() {
		return extraEfficiency;
	}

	public void setExtraEfficiency(double extraEfficiency) {
		this.extraEfficiency = extraEfficiency;
	}

	public String getName() {
		return name;
	}

	public int getIdEmployee() {
		return idEmployee;
	}
	
	public String toString() {
		return idEmployee + " " + name;
	}
	
	public Role getTheRole() {
		return theRole;
	}

	public void setRole(Role theRole) throws AlreadyExistException {
		if(this.theRole == null) {
			this.theRole = theRole;
		}
		else
			throw new AlreadyExistException("This employee already have a roll");
	}
	
	public void removeRole() {
		this.theRole = null;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Employee))
			return false;
		Employee temp = (Employee) other;
		return this.name.equals(temp.name) && this.idEmployee == temp.idEmployee;
	}
}
