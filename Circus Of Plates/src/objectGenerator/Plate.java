package objectGenerator;

import java.awt.Color;
import java.awt.Point;

public class Plate {
	private int id;
	private Color plateColor;
	private Point position;
	private int height, width;

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

	public Plate() {
		// TODO Auto-generated constructor stub
	}

	public void setID(int i) {
		// TODO Auto-generated method stub
		id = i;
	}
}
