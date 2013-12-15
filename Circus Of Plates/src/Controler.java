import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;

import objectGenerator.AbstractFactory;
import objectGenerator.Plate;
import objectGenerator.PlateIterator;
import objectGenerator.PlatePool;
import objectGenerator.Player;

public class Controler {

	private PlatePool platePool;
	private PlateIterator plateIterator;
	private double gravity = 1;
	private double dt = .2;
	private int x, y, initialDx, initialDy;
	private double dy;
	private double dx;
	private Plate plate;
	private int rowsNo = 3;
	Boda view;

	public Controler(Boda boda) {
		view = boda;
		initialDx = 1;
		initialDy = 0;
		rowsNo = 3;
		// TODO Auto-generated constructor stub

		platePool = PlatePool.getPlatePool();
	}

	void excuteFrame() {
		plateIterator = PlateIterator.getPlateIterator();

		while (plateIterator.hasnext()) {

			plate = plateIterator.next();
			x = plate.getPosition().x;
			y = plate.getPosition().y;
			dx = plate.getDx();
			dy = plate.getDy();

			dealWithPlate();
		}
		view.repaint();

		if (Math.abs(view.time1 - System.currentTimeMillis()) > 1200) {
			insertNewPlates(rowsNo); // insert new 4 plates
			view.time1 = (System.currentTimeMillis());
		}
	}

	private void insertNewPlates(int rowsNo) {
		for (int i = 0; i < rowsNo; i++) {
			plate = platePool.getPlate();
			plate.setPosition(new Point(0, i * 50));
			plate.setDx(initialDx);
			plate.setDy(0);
			plate = platePool.getPlate();
			plate.setPosition(new Point(view.getWidth(), i * 50));
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
		if (y > view.getHeight() || x > view.getWidth()) { // plate gets out of
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
		Point RH = view.player1.getRightHand();
		Point LH = view.player1.getLeftHand();
		int widthL = view.player1.LeftHandWidth();
		int widthR = view.player1.RightHandWidth();
		if ((x < (RH.x + widthR - 2)) && x > (RH.x - plate.getWidth() + 2)) {
			if (Math.abs(y + plate.getHeight() - RH.y) < 7) {
				view.player1.addAtRight(plate);
			}
		} else if ((x < (LH.x + widthL - 2))
				&& x > (LH.x - plate.getWidth() + 2)) {
			if (Math.abs(y + plate.getHeight() - LH.y) < 7) {
				view.player1.addAtLeft(plate);
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
		if (x + plate.getWidth() < (view.getWidth() / (rowsNo * 2 + 3))
				* ((rowsNo * 50 - y) / 50)) { // Still not fall
			x += dx;
		} else
			// Fall now
			falling();
	}

	private void moveRightSide() {

		if (x > view.getWidth()
				- ((view.getWidth() / (rowsNo * 2 + 3)) * ((rowsNo * 50 - y) / 50)))
			// still not fall
			x += dx;
		else
			// Fall Now
			falling();
	}

	public ArrayList<Plate> getRightHandPlates() {
		// TODO Auto-generated method stub

		return Player.getPlayer().getRightHandPlates();
	}

	public ArrayList<Plate> getLeftHandPlates() {
		// TODO Auto-generated method stub
		return Player.getPlayer().getLeftHandPlates();
	}

}
