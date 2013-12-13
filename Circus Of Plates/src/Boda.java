import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.sql.Time;

import objectGenerator.*;

public class Boda extends Applet implements Runnable {

	long time1;
	PlatePool q;
	private PlateIterator a;

	@Override
	public void init() {
		setSize(800, 600);

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		q = PlatePool.getPlatePool();
		a = PlateIterator.getPlateIterator();

		Thread thread = new Thread(this);
		thread.start();

	}

	@Override
	public void run() {
		time1 = System.currentTimeMillis();
		while (true) {
			excuteFrame();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void excuteFrame() {
		// TODO Auto-generated method stub

		a = PlateIterator.getPlateIterator();
		Plate f;
		while (a.hasnext()) {
			f = a.next();
			if (f.getPosition().x  < this.getWidth() / 2) {
				moveLeftSide(f);
			} else {
				moveRightSide(f);
			}
//			int x = f.getPosition().x, y = f.getPosition().y, dx = 2, dy = 2;
//			if (x + dx < 2 * this.getWidth() / (3 + f.getPosition().y / 50)) {
//				x += dx;
//			} else {
//				y += dy;
////				x += 1;
//			}
//			if (y > this.getHeight() || x > this.getWidth()) {
//				q.releasePlate(f);
//				a.justifyIndex();
//			} else {
//				f.setPosition(new Point(x, y));
//			}
		}
		repaint();
		if (Math.abs(time1 - System.currentTimeMillis()) > 400) {
			q.getPlate().setPosition(new Point(0, 40));
			q.getPlate().setPosition(new Point(0, 80));
			q.getPlate().setPosition(new Point(this.getWidth(), 80));
			q.getPlate().setPosition(new Point(this.getWidth(), 40));

			time1 = System.currentTimeMillis();
		}

	}
	private void moveLeftSide(Plate f) {
		// TODO Auto-generated method stub
		int x = f.getPosition().x, y = f.getPosition().y, dx = 2, dy = 3;
		if (x + dx <  this.getWidth() / (3 + f.getPosition().y / 40)) {
			x += dx;
		} else {
			y += dy;
			x+=1;
		}
		f.setPosition(new Point(x, y));
	}
	private void moveRightSide(Plate f) {
		// TODO Auto-generated method stub
		int x = f.getPosition().x, y = f.getPosition().y, dx = 2, dy = 3;
		if ((y == 40||y==80 )&&x > this.getWidth() -this.getWidth() / (3 + f.getPosition().y / 40)) {
			x -= dx;
		} else {
			y += dy;
			x-=1;
		}
		f.setPosition(new Point(x, y));
	}
	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void paint(Graphics g) {
		a = PlateIterator.getPlateIterator();
		while (a.hasnext()) {
			a.next().Paint(g);
		}
		// g.setColor(Color.BLUE);
		// g.fillOval(x-radius,y-radius,radius*2,radius*2);
	}
}
