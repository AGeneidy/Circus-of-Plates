package objectGenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.sql.PooledConnection;
import javax.swing.LayoutFocusTraversalPolicy;
import javax.swing.text.Position;

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
	private static Player player1;

	public static Player getPlayer() {
		if (player1 == null)
			return player1 = new Player();
		else
			return player1;
	}

	private Player() {
		height = 141;
		// TODO Auto-generated constructor stub
		RightHandPlates = new ArrayList<Plate>();
		leftHandPlates = new ArrayList<Plate>();
		center = new Point(60, height);
		rightCenter = new Point(center.x + 40, height);
		leftCenter = new Point(center.x - 40, height);
	}

	public int getHight() {
		return height;
	}

	public void setCenter(Point center) {
		this.center = center;

	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.RED);
		// g2.drawLine(center.x, center.y, center.x, center.y+50);
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
			RH.x-=15;
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
		t.position.x = LH.x;
		t.position.y = LH.y - 10;
		leftHandPlates.add(t);
		LH = t.getPosition();
		t.setOnPlayer(true);
		if (leftHandPlates.size() >= 3) {
			Plate[] w = { leftHandPlates.get(leftHandPlates.size() - 1),
					leftHandPlates.get(leftHandPlates.size() - 2),
					leftHandPlates.get(leftHandPlates.size() - 3) };
			check(w);
		}
	}

	public void addAtRight(Plate t) {
		// TODO Auto-generated method stub
		t.position.x = RH.x;
		t.position.y = RH.y - 10;
		RightHandPlates.add(t);

		RH = t.getPosition();
		t.setOnPlayer(true);

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

		if (b[0].plateColor == b[1].plateColor
				&& b[0].plateColor == b[2].plateColor) {

			PlatePool q = PlatePool.getPlatePool();
			for (Plate a : b) {
				a.setOnPlayer(false);
				if (RightHandPlates.contains(a)) {
					RightHandPlates.remove(a);
				} else {
					leftHandPlates.remove(a);

				}
				q.releasePlate(a);
			}
			if (!RightHandPlates.isEmpty())
				RH = RightHandPlates.get(RightHandPlates.size() - 1)
						.getPosition();
			else {
				RH = rightCenter;
			}

			if (!leftHandPlates.isEmpty())
				LH = leftHandPlates.get(leftHandPlates.size() - 1)
						.getPosition();
			else
				LH = leftCenter;

		}
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
