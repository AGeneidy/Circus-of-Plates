package objectGenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.Point;

public class Plate {
	private int id;
	protected Color plateColor;
	protected Point position;
	protected int height, width;

	public Plate() {
		// TODO Auto-generated constructor stub
		position = new Point(0, 0);
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
}
