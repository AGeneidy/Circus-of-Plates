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
		q.getPlate();
		q.getPlate();
		q.getPlate();
		q.getPlate();
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
				Thread.sleep(50);
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
			int x = f.getPosition().x, y = f.getPosition().y, dx = 5, dy = 6;
			// System.out.println(a.index());
			if (x + dx < 2 * this.getWidth() / 3) {
				x += dx;
			} else {
				y += dy;
				x += 1;
			}
			if (y > this.getHeight() || x > this.getWidth()) {
				q.releasePlate(f);
				a.justifyIndex();
			} else {
				f.setPosition(new Point(x, y));
			}
		}
		repaint();
		if (Math.abs(time1 - System.currentTimeMillis()) > 200) {
			q.getPlate();
			time1 = System.currentTimeMillis();
		}

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
