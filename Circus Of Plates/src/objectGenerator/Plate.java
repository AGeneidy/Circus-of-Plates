package objectGenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.Point;
import java.util.Random;

public class Plate {
	private int id;
	private Color[] chooseColor = { Color.RED, Color.GREEN, Color.BLUE };
	protected Color plateColor;
	protected Point position;
	protected int height, width;
	protected double dy;

	public Plate() {
		// TODO Auto-generated constructor stub
		plateColor = chooseColor[new Random().nextInt(500) % 3];
		position = new Point(0, 0);
		dy = 0;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Color getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(Color plateColor) {
		this.plateColor = plateColor;
	}

	public void setID(int i) {
		// TODO Auto-generated method stub
		id = i;
	}

	public void Paint(Graphics g) {
		// TODO Auto-generated method stub
	}

	public double getDy() {
		// TODO Auto-generated method stub
		return dy;
	}

	public void setDy(double i) {
		// TODO Auto-generated method stub
		dy = i;
	}
}
