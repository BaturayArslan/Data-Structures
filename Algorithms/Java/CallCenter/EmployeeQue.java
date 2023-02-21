package CallCenter;

import java.util.*;

public class EmployeeQue {
	Queue<Respondent> respondentQue;
	Queue<Director> directorQue;
	Queue<Manager> managerQue;

	public EmployeeQue() {
		respondentQue = new LinkedList<Respondent>();
		directorQue = new LinkedList<Director>();
		managerQue = new LinkedList<Manager>();
	}

	public void add(Employee employee) {
		if (employee instanceof Respondent) {
			respondentQue.add((Respondent) employee);
		} else if (employee instanceof Director) {
			directorQue.add((Director) employee);
		} else {
			managerQue.add((Manager) employee);
		}
	}

	public Employee pop() {
		if (!respondentQue.isEmpty()) {
			return respondentQue.remove();
		} else if (!directorQue.isEmpty()) {
			return directorQue.remove();
		} else {
			return managerQue.remove();
		}
	}
}
