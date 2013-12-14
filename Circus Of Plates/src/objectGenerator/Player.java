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
		height = 200;
		// TODO Auto-generated constructor stub
		RightHandPlates = new ArrayList<Plate>();
		leftHandPlates = new ArrayList<Plate>();
		center = new Point(60, height);
		rightCenter = new Point(center.x + 50, height);
		leftCenter = new Point(center.x - 50, height);
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
		g2.fillRect(rightCenter.x, height, 30, 60);
		g2.fillRect(leftCenter.x, height, 30, 60);
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

	public void addAtRight(Plate t) {
		// TODO Auto-generated method stub
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
			else
				RH = rightCenter;

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

	public void addAtLeft(Plate t) {
		// TODO Auto-generated method stub
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
}
