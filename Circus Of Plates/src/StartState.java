public class StartState implements State {

	public void doAction(Shape shape1) {
		System.out.println("Player is in start state");
		shape1.setState(this);
	}

	public String toString() {
		return "Start State";
	}

}
