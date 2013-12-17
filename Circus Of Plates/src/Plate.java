

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.net.URL;
import java.util.Random;

public class Plate implements java.io.Serializable {
	private int id;
	protected int plateColor;
	protected Point position;
	protected int height, width, type;
	protected double dx;
	protected double dy;
	protected state State;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setState(state state) {
		State = state;
	}

	public String getState() {
		return State.getState();
	}

	public void setState(String state) {
		State.setState(state);
	}

	public Plate() {
		// TODO Auto-generated constructor stub
		// plateColor = chooseColor[2];
		position = new Point(0, 0);
		State = new state();
		dy = 0;
		dx = 0;
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

	public int getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(int plateColor) {
		this.plateColor = plateColor;
	}

	public void setID(int i) {
		// TODO Auto-generated method stub
		id = i;
	}

	public void Paint(Graphics g, Applet view, URL url) {
		// TODO Auto-generated method stub
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		// TODO Auto-generated method stub
		return dy;
	}

	public void setDy(double i) {
		// TODO Auto-generated method stub
		dy = i;
	}

	public String getImagePath() {
		return null;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return State.getType();
	}
}
