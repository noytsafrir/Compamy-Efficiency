package model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import exceptions.AlreadyExistException;
import exceptions.EntityIsNotExist;
import exceptions.WorkHoursTimeException;
import model.Company.eDepTypes;

public class Department implements Synchronizable, ChangeHourable, Serializable{
	private String name;
	private int idDep;
	public static int ID_NUM = 100;
	public Set<Role> allRoles;
	private Preference workHours;
	private eDepTypes theType;

	public Department(String name,eDepTypes theType, Preference workHours) {
		this.name = name;
		idDep = ID_NUM++;
		this.allRoles = new LinkedHashSet<Role>();
		this.theType = theType;
		this.workHours = workHours;
	}

	public void addRole(Role newRole) throws AlreadyExistException {
		boolean isExist = this.allRoles.add(newRole);
		if(!isExist) {
			throw new AlreadyExistException("The Role is already exist");
		}
	}

	public String getName() {
		return name;
	}

	public Set<Role> getAllRoles(){
		return allRoles;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdDep() {
		return idDep;
	}

	public eDepTypes getTheType() {
		return theType;
	}
	
	public Preference getWorkHours() {
		return workHours;
	}
	
	public void removeRoll(Role theRole) {
		allRoles.remove(theRole);
	}
	
	public Role getRoleByname(String name) throws EntityIsNotExist{
		for( Role r: allRoles) {
			if(r.getName().equals(name)) {
				return r;
			}
			else {
				throw new EntityIsNotExist("This role is not exist in this department");
			}
		}
		return null;
	}
	
	public void setDepartmentHours(Preference thepref)	throws WorkHoursTimeException {
		if (this.theType == eDepTypes.Syncronic) {
			if (thepref == null) {
				throw new WorkHoursTimeException("Work hours for synchronized department is missing");
			}
			this.workHours = thepref;
			for (Role r : allRoles) {
				r.setCurrentWorkHours(thepref);
			}
		} else {
			this.workHours = null;
		}
	}
	
	public double calcDepartmentEfficient() {
		double sum = 0 ; 
		for (Role theRole : allRoles) { // calc method // add to sum  
			sum+=theRole.calcEfficient(); 
		}
		 return sum;  
	}
	
	public String toString() {
		return  idDep + " " + name;
	}

	@Override
	public boolean isHoursSynchronizable() {
		return (this.theType == eDepTypes.Syncronic);
	}

	@Override
	public boolean isHoursChangeable() {
		return (this.theType == eDepTypes.Dynamic);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Department))
			return false;
		Department temp = (Department) other;
		return this.name.equals(temp.name) && this.idDep == temp.idDep;
	}
}
