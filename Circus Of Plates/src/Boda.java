import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.sql.Time;
import java.awt.event.*;

import javax.swing.JLabel;

import objectGenerator.*;

public class Boda extends Applet implements Runnable {

	/**
	 * @author Ayman Geneidy
	 * 
	 */

	long time1;
	PlatePool q;
	private PlateIterator a;
	private Image i;
	private Graphics doubleG;
	double gravity = 2;
	double energyloss = .80;
	double dt = .2;
	private int x, y, dx = 3;
	private double dy;
	private AbstractFactory p;
	protected Player player1;
	private Plate f;
	private int  Wheight = 1000, Width = 800;
	@Override
	public void init() {
		setSize(Wheight, Width);
		p = FactoryProducer.getFactory("player");
		player1 = p.getPlayer();
		player1.setWindowattri(this.getWidth(),this.getHeight());
		player1.setattributes(this.getWidth()/2, this.getHeight()-60);
		this.addKeyListener(new handleKeyBoard());
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

		while (a.hasnext()) {
			f = a.next();
			y = f.getPosition().y;
			x = f.getPosition().x;
			dy = f.getDy();
			// velocity formula
			checkPlate();
			if (f.isOnPlayer())
				continue;
			if (f.getPosition().x < this.getWidth() / 2) {
				moveLeftSide();
			} else {
				moveRightSide();
			}

			if (y > this.getHeight() || x > this.getWidth()) {
				f.setDy(0);
				q.releasePlate(f);
				a.justifyIndex();
			} else {
				f.setPosition(new Point(x, y));
				f.setDy(dy);
			}
		}
		repaint();
		if (Math.abs(time1 - System.currentTimeMillis()) > 600) {
			q.getPlate().setPosition(new Point(0, 0));
			q.getPlate().setPosition(new Point(0, 50));
			q.getPlate().setPosition(new Point(this.getWidth(), 50));
			q.getPlate().setPosition(new Point(this.getWidth(), 0));
			time1 = System.currentTimeMillis();
		}

	}

	private void checkPlate() {
		// TODO Auto-generated method stub
		Point RH = player1.getRightHand();
		Point LH = player1.getLeftHand();
		int widthL = player1.LeftHandWidth();
		int widthR = player1.RightHandWidth();
		if ((x < (RH.x + widthR - 2)) && x > (RH.x - f.getWidth() + 2)) {
			if (Math.abs(y + f.getHeight() - RH.y) < 7) {
				player1.addAtRight(f);
			}
		}else if ((x < (LH.x + widthL - 2)) && x > (LH.x - f.getWidth() + 2)) {
			if (Math.abs(y + f.getHeight() - LH.y) < 7) {
				player1.addAtLeft(f);
			}
		}

	}

	private void moveLeftSide() {

		// TODO Auto-generated method stub

		if (x + dx < this.getWidth() / (3 + y / 50)) {
			x += dx;
		} else {
			dy += gravity * dt;
			// posistion formula
			// y += dy * dt + .5 * gravity * dt * dt;
			y += 6;
			x += 1;
		}
	}

	private void moveRightSide() {
		// TODO Auto-generated method stub
		if ((y == 0 || y == 50)
				&& x > this.getWidth() - this.getWidth() / (3 + y / 50)) {
			x -= dx;
		} else {
			dy += gravity * dt;
			// posistion formula
			// y += dy * dt + .5 * gravity * dt * dt;
			y += 6;
			x -= 1;
		}
	}

	@Override
	public void update(Graphics g) {
		if (i == null) {
			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();
		}

		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);

		doubleG.setColor(getForeground());
		paint(doubleG);

		g.drawImage(i, 0, 0, this);
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
		player1.paint(g);
	}
}
