public class ShapeCreator {
	private static ShapeCreator h;

	private ShapeCreator() {
		// TODO Auto-generated constructor stub
	}

	Shape getRandomShape() {
		return new Shape();
	}

	static ShapeCreator getShapeCreator() {
		if (h == null)
			h = new ShapeCreator();
		return h;
	}
}
