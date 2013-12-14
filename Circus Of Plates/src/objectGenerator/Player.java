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

public class Player {
	ArrayList<Plate> RightHandPlates, leftHandPlates;
	private int height, rightHeight, leftHeight;
	private Point center, rightCenter, leftCenter;
	private Point LH, RH;

	public Player() {
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

	private Point getCeneter() {
		// TODO Auto-generated method stub
		return center;
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
		height = height2 - 60;
		center.x = width / 2;
		rightHeight = leftHeight = height;
		justify();
	}

	private void justify() {
		// TODO Auto-generated method stub
		center.y = height;
		rightCenter = new Point(center.x + 35, height);
		leftCenter = new Point(center.x - 65, height);
		if (RightHandPlates.isEmpty() && leftHandPlates.isEmpty()) {
			RH = rightCenter;
			LH = leftCenter;
		}
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
//		if(RightHandPlates.size()>=3)
//			checkRight();
	}

	private void checkRight() {
		// TODO Auto-generated method stub
		Plate a = RightHandPlates.get(RightHandPlates.size()-1);
		Plate b = RightHandPlates.get(RightHandPlates.size()-2);
		Plate n = RightHandPlates.get(RightHandPlates.size()-3);
		if(a.getChooseColor()==b.getChooseColor()&&a.getChooseColor()==n.getChooseColor())
		{
			PlatePool q = PlatePool.getPlatePool();
			RightHandPlates.remove(a);
			RightHandPlates.remove(b);
			RightHandPlates.remove(n);
			q.releasePlate(a);
			q.releasePlate(b);
			q.releasePlate(n);
		}
		}

	public void addAtLeft(Plate t) {
		// TODO Auto-generated method stub
		leftHandPlates.add(t);
		RH = t.getPosition();
		t.setOnPlayer(true);
	}

	public Point getLeftHand() {
		// TODO Auto-generated method stub
		return LH;
	}

	public Point getRightHand() {
		// TODO Auto-generated method stub
		return RH;
	}

}
