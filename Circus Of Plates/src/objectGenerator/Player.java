package objectGenerator;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RenderingHints;
import java.net.URL;
import java.util.ArrayList;

public class Player {
	ArrayList<Plate> RightHandPlates, leftHandPlates;
	private int height, rightHeight, leftHeight;
	private Point center, rightCenter, leftCenter;

	public Point getLeftCenter() {
		return leftCenter;
	}

	private Point LH, RH;
	private int windowWidth;
	private int windowHeight;
	private int Score;

	protected Player() {
		height = 141;
		// TODO Auto-generated constructor stub
		RightHandPlates = new ArrayList<Plate>();
		leftHandPlates = new ArrayList<Plate>();
		center = new Point(60, height);
		rightCenter = new Point(center.x + 40, height);
		leftCenter = new Point(center.x - 40, height);
		Score = 0;
	}

	public int getHight() {
		return height;
	}

	public void setCenter(Point center) {
		this.center = center;

	}

	public void paint(Graphics g, Applet view, URL url) {
		Image plateImg = view.getImage(url, "images/clown.png");
		g.drawImage(plateImg, getLeftCenter().x + 5, getLeftCenter().y + 2,
				view);
		for (Plate p : RightHandPlates) {
			p.Paint(g, view, url);
		}
		for (Plate p : leftHandPlates) {
			p.Paint(g, view, url);
		}

	}

	public void setattributes(int width, int height2) {
		// TODO Auto-generated method stub
		height = height2;
		center.x = width;
		rightHeight = leftHeight = height;
		justify();
	}

	private void justify() {
		// TODO Auto-generated method stub
		center.y = height;
		rightCenter = new Point(center.x + 35, height);
		leftCenter = new Point(center.x - 65, height);
		if (RightHandPlates.isEmpty()) {
			RH = rightCenter;
			RH.x -= 15;
		} else {
			RH = RightHandPlates.get(RightHandPlates.size() - 1).getPosition();
		}
		if (leftHandPlates.isEmpty())
			LH = leftCenter;
		else
			LH = leftHandPlates.get(leftHandPlates.size() - 1).getPosition();
	}

	public int LeftHandWidth() {
		if (!leftHandPlates.isEmpty())
			return leftHandPlates.get(leftHandPlates.size() - 1).getWidth();
		return 30;
	}

	public int RightHandWidth() {
		if (!RightHandPlates.isEmpty())
			return RightHandPlates.get(RightHandPlates.size() - 1).getWidth();
		return 30;
	}

	public void addAtLeft(Plate t) {
		// TODO Auto-generated method stub
		if (t.getState().equalsIgnoreCase("OnPLayer"))
			return;
		t.position.x = LH.x;
		t.position.y = LH.y - 10;
		leftHandPlates.add(t);
		LH = t.getPosition();
		t.setState("OnPLayer");
		if (leftHandPlates.size() >= 3) {
			Plate[] w = { leftHandPlates.get(leftHandPlates.size() - 1),
					leftHandPlates.get(leftHandPlates.size() - 2),
					leftHandPlates.get(leftHandPlates.size() - 3) };
			check(w);
		}
	}

	public void addAtRight(Plate t) {
		// TODO Auto-generated method stub
		if (t.getState().equalsIgnoreCase("OnPLayer"))
			return;

		t.position.x = RH.x;
		t.position.y = RH.y - 10;
		RightHandPlates.add(t);

		RH = t.getPosition();
		t.setState("OnPLayer");

		if (RightHandPlates.size() >= 3) {

			Plate[] w = { RightHandPlates.get(RightHandPlates.size() - 1),
					RightHandPlates.get(RightHandPlates.size() - 2),
					RightHandPlates.get(RightHandPlates.size() - 3) };
			check(w);
		}
	}

	private void check(Plate[] b) {
		// TODO Auto-generated method stub
		// System.out.println("a77a");

		if (b[0].getColor() != b[1].getColor()
				|| b[0].getColor() != b[2].getColor())
			return;
		if (!(b[0].getType().equalsIgnoreCase(b[1].getType()) && b[0].getType()
				.equalsIgnoreCase(b[2].getType())))
			return;

		PlatePool q = PlatePool.getPlatePool();
		for (Plate a : b) {
			a.setState("Free");
			if (RightHandPlates.contains(a)) {
				RightHandPlates.remove(a);
			} else {
				leftHandPlates.remove(a);
			}
			q.releasePlate(a);
		}
		if (!RightHandPlates.isEmpty())
			RH = RightHandPlates.get(RightHandPlates.size() - 1).getPosition();
		else {
			RH = rightCenter;
		}

		if (!leftHandPlates.isEmpty())
			LH = leftHandPlates.get(leftHandPlates.size() - 1).getPosition();
		else
			LH = leftCenter;
		Score++;
		System.out.println(Score);

		playerOut();
	}

	private void playerOut() {
		// TODO Auto-generated method stub
		if (RH.y < 80 || LH.y < 80)
			System.exit(0);
	}

	public Point getLeftHand() {
		// TODO Auto-generated method stub
		return LH;
	}

	public Point getRightHand() {
		// TODO Auto-generated method stub
		return RH;
	}

	public void move(int u) {
		// TODO Auto-generated method stub
		for (Plate b : RightHandPlates)
			b.position.x = (b.position.x + u + windowWidth) % windowWidth;
		for (Plate b : leftHandPlates)
			b.position.x = (b.position.x + u + windowWidth) % windowWidth;
		setattributes((center.x + u + windowWidth) % windowWidth, height);

	}

	public void setWindowattri(int width, int height) {
		// TODO Auto-generated method stub
		windowWidth = width;
		windowHeight = height - 60;
	}

	public void mouseMove(int x) {
		// TODO Auto-generated method stub
		move(x - center.x);
	}

	public ArrayList<Plate> getRightHandPlates() {
		// TODO Auto-generated method stub
		return RightHandPlates;
	}

	public ArrayList<Plate> getLeftHandPlates() {
		// TODO Auto-generated method stub
		return leftHandPlates;
	}
}
