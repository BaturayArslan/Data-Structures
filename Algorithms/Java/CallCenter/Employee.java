package CallCenter;

public abstract class Employee {
	protected String name;
	protected Dispatcher dispatcher;

	public void answerCall(Call call) {
		// Do someting

		// And than be available again
		beAvailable();
	}

	public void setDispather(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public void beAvailable() {
		if (dispatcher != null) {
			dispatcher.addEmployee(this);
		}
	}
}
