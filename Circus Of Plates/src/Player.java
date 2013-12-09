import java.util.ArrayList;

public class Player {
	ArrayList<Shape> rightHand;
	ArrayList<Shape> leftHand;

	Player() {
		rightHand = new ArrayList<Shape>();
		leftHand = new ArrayList<Shape>();
	}

	void appendRight(Shape p) {
		rightHand.add(p);
	}

	void appendLeft(Shape p) {
		leftHand.add(p);
	}

}
