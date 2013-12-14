import java.applet.Applet;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import objectGenerator.*;

public class Boda extends Applet implements Runnable {

	/**
	 * @author Ayman Geneidy
	 * 
	 */

	private long time1;
	private PlatePool platePool;
	private PlateIterator plateIterator;
	private Image i;
	private Graphics doubleG;
	private double gravity = 1;
	private double energyloss = .80;
	private double dt = .2;
	private int x, y, initialDx, initialDy;
	private double dy;
	private double dx;
	private AbstractFactory abstractfactory;
	private Player player1;
	private Plate plate;
	private int Height = 1000, Width = 600;
	private int rowsNo;

	@Override
	public void init() {

		setSize(Height, Width);
		abstractfactory = FactoryProducer.getFactory("player");

		player1 = abstractfactory.getPlayer();
		player1.setWindowattri(this.getWidth(), this.getHeight());
		player1.setattributes(this.getWidth() / 2, this.getHeight() - 60);

		this.addKeyListener(new handleKeyBoard());
		this.addMouseMotionListener(new mouseMotion());

		// setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		// GraphicsEnvironment ge =
		// GraphicsEnvironment.getLocalGraphicsEnvironment();
		// GraphicsDevice[] devices = ge.getScreenDevices();
		// devices[0].setFullScreenWindow(this.Boda);
	}

	@Override
	public void start() {
		platePool = PlatePool.getPlatePool();
		initialDx = 1;
		initialDy = 0;
		rowsNo = 3;

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
				e.printStackTrace();
			}
		}
	}

	private void excuteFrame() {
		plateIterator = PlateIterator.getPlateIterator();

		while (plateIterator.hasnext()) {

			plate = plateIterator.next();
			x = plate.getPosition().x;
			y = plate.getPosition().y;
			dx = plate.getDx();
			dy = plate.getDy();

			dealWithPlate();
		}
		repaint();
		if (Math.abs(time1 - System.currentTimeMillis()) > 1200) {
			insertNewPlates(rowsNo); // insert new 4 plates
			time1 = System.currentTimeMillis();
		}
	}

	private void insertNewPlates(int rowsNo) {
		for(int i=0; i<rowsNo; i++){
			plate = platePool.getPlate();
			plate.setPosition(new Point(0, i*50));
			plate.setDx(initialDx);
			plate.setDy(0);

			plate = platePool.getPlate();
			plate.setPosition(new Point(this.getWidth(), i*50));
			plate.setDx(-initialDx);
			plate.setDy(0);
		}
	}

	// ////////////////////////////////////////////////////////////////////////
	// ////////////Methods Handle the Movement of Plates///////////////////////
	// ////////////////////////////////////////////////////////////////////////
	private void dealWithPlate() {

		// make player try to catch plates
		catchPlate();

		if (!plate.isOnPlayer()) { // move free plates
			if (dy > 0) // falling
				falling();
			else if (dx > 0) // move from left to right
				moveLeftSide();
			else
				// move from right to left
				moveRightSide();
			setPlate();
		}
	}

	private void setPlate() {
		if (y > this.getHeight() || x > this.getWidth()) { // plate gets out of
															// screen
			platePool.releasePlate(plate);
			plateIterator.justifyIndex();
		} else { // set plate
			plate.setPosition(new Point(x, y));
			plate.setDx(dx);
			plate.setDy(dy);
		}
	}

	private void catchPlate() {
		// TODO Auto-generated method stub
		Point RH = player1.getRightHand();
		Point LH = player1.getLeftHand();
		int widthL = player1.LeftHandWidth();
		int widthR = player1.RightHandWidth();
		if ((x < (RH.x + widthR - 2)) && x > (RH.x - plate.getWidth() + 2)) {
			if (Math.abs(y + plate.getHeight() - RH.y) < 7) {
				player1.addAtRight(plate);
			}
		} else if ((x < (LH.x + widthL - 2))
				&& x > (LH.x - plate.getWidth() + 2)) {
			if (Math.abs(y + plate.getHeight() - LH.y) < 7) {
				player1.addAtLeft(plate);
			}
		}
	}

	private void falling() {
		// Velocity of falling formula
		dy += gravity * dt;
		// Position formula
		y += dy * dt + .5 * gravity * dt * dt;
		x += dx;
	}

	private void moveLeftSide() {
		if (x + plate.getWidth() < (this.getWidth() / (rowsNo * 2 + 3)) * ( (rowsNo * 50-y)/ 50)) { // Still not fall
			x += dx;
		} else
			// Fall now
			falling();
	}

	private void moveRightSide() {

		if (x > this.getWidth()
				- ( (this.getWidth() / (rowsNo * 2 + 3)) * ( (rowsNo * 50 -y)/ 50)))
			// still not fall
			x += dx;
		else
			// Fall Now
			falling();
	}

	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
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
	public void paint(Graphics g) {
		plateIterator = PlateIterator.getPlateIterator();
		while (plateIterator.hasnext()) {
			plateIterator.next().Paint(g);
		}
		player1.paint(g);
	}

}
