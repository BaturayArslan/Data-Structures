package CallCenter;

import java.util.*;

public class Dispatcher {
	private EmployeeQue empQue;
	private Queue<Call> callQue;

	public Dispatcher() {
		empQue = new EmployeeQue();
		callQue = new LinkedList<Call>();

	}

	public void dispatchCall() {
		Employee employee = empQue.pop();
		Call call = callQue.remove();
		employee.answerCall(call);
	}

	public void addEmployee(Employee employee) {
		empQue.add(employee);
	}

	public void addCall(Call call) {
		callQue.add(call);
	}
}
