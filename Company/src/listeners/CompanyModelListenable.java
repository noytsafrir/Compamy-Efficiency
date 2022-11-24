package listeners;

import model.Employee;
import model.Role;

public interface CompanyModelListenable {
	public void modelUpdateLinkRollAndEmployee(Employee theEmployee, Role theRole);
	public void modelUpdateRemoveEmployeeFromRoll(Employee theEmployee , Role theRole);
	public void modelUpdateClearEmployeeFromTheRoll(Role theRole);
	public void modelUpdateNameHasChanged();
}
