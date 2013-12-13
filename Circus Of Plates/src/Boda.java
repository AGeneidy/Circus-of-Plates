import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import objectGenerator.*;

public class Boda extends Applet implements Runnable {

	int x = 0;
	int y = 0;
	double dx = 3;
	PlatePool q;
	double dy = 0;
	int radius = 20;
	// private Image i;
	// private Graphics doubleG;
	double gravity = 10;
	double energyloss = .80;
	double dt = .2;
	private PlateIterator a;
	Plate f;

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
		while(a.hasnext()){
			f = a.next();
			System.out.println(a.index());
		if (x + dx > this.getWidth() - radius - 1) {
			x = this.getWidth() - radius - 1;
			dx *= -1;
		} else if (x + dx < 0 + radius) {
			x = 0 + radius;
			dx *= -1;
		} else {
			x += dx;
		}

		if (y > this.getHeight() - radius - 1) {
			y = this.getHeight() - radius - 1;
			dy *= -energyloss;
		} else {
			// velocity formula
			dy += gravity * dt;
			// posistion formula
			y += dy * dt + .5 * gravity * dt * dt;
		}
		f.setPosition(new Point(x, y));
		}
		
		repaint();
		q.getPlate();
		
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
