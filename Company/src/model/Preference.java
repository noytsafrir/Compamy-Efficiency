package model;

import java.io.Serializable;
import java.time.LocalTime;

import exceptions.PreferenceTimeException;
import model.Company.ePreferences;

public class Preference implements Serializable {
	protected ePreferences thePreference;
	protected LocalTime startHourPreference;

	public Preference(ePreferences thePreference, LocalTime startHourPreference) { // all the pref. except HOME ,
																					// Original
		setThePreference(thePreference);
		setEmployeeStartHourPreference(startHourPreference);
	}

	public Preference(ePreferences thePreference) throws PreferenceTimeException { // only for pref. HOME, Original
		if ((ePreferences.Home != thePreference) && (ePreferences.Original != thePreference)) {
			throw new PreferenceTimeException("Start hour is missing");
		}
		this.thePreference = thePreference;
		startHourPreference = Company.REGULAR_START_HOUR;
	}

	public ePreferences getThePreference() {
		return thePreference;
	}

	public void setThePreference(ePreferences thePreference) {
		this.thePreference = thePreference;
	}

	public LocalTime getStartHourPreference() {
		return startHourPreference;
	}

	public void setEmployeeStartHourPreference(LocalTime employeeStartHourPreference) {
		this.startHourPreference = employeeStartHourPreference;
		if ((ePreferences.Home).equals(thePreference)) {
			employeeStartHourPreference = Company.REGULAR_START_HOUR;
		}
	}

	@Override
	public String toString() {
		if (thePreference == ePreferences.Home) {
			return "w.Home";
		} else if (thePreference == ePreferences.Original) {
			return Company.REGULAR_START_HOUR + " - "
					+ Company.REGULAR_START_HOUR.plusHours(Company.WORKING_HOURS_PER_DAY + 1);
		}
		return startHourPreference + " - " + startHourPreference.plusHours(Company.WORKING_HOURS_PER_DAY + 1);
	}

}
