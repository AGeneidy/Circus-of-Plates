import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;

public class Controler {

	protected PlatePool platePool;
	private PlateIterator plateIterator;
	private double gravity = 1;
	private double dt = .2;
	private int x, y, initialDx, initialDy;
	private double dy;
	private double dx;
	private Plate plate;
	private int rowsNo = 3;
	View view;
	long timer, gameTimer, gameStartTime;

	public Controler(View boda) {
		view = boda;
		initialDx = 1;
		initialDy = 0;
		rowsNo = 3;
		// TODO Auto-generated constructor stub

		platePool = PlatePool.getPlatePool();
	}

	void excuteFrame() {
		timer = (int) System.currentTimeMillis();

		plateIterator = PlateIterator.getPlateIterator();

		while (plateIterator.hasnext()) {

			plate = plateIterator.next();
			x = plate.getPosition().x;
			y = plate.getPosition().y;
			dx = plate.getDx();
			dy = plate.getDy();

			dealWithPlate();
		}

		if (Math.abs(view.time - System.currentTimeMillis()) > 1200) {
			insertNewPlates(rowsNo); // insert new 4 plates
			view.time = (System.currentTimeMillis());
		}

	}

	private void insertNewPlates(int rowsNo) {
		for (int i = 0; i < rowsNo; i++) {
			plate = platePool.getPlate();
			plate.setPosition(new Point(0, i * 50));
			plate.setDx(initialDx);
			plate.setDy(0);
			plate = platePool.getPlate();
			plate.setPosition(new Point(view.gameWidth, i * 50));
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

		if (!plate.getState().equalsIgnoreCase("OnPlayer")) { // move free
																// plates
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
		if (y > view.gameHeight || x > view.gameWidth) { // plate gets out of
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
		for (Player a : view.Players) {
			Point RH = a.getRightHand();
			Point LH = a.getLeftHand();
			int widthL = a.LeftHandWidth();
			int widthR = a.RightHandWidth();
			if ((x < (RH.x + widthR - 2)) && x > (RH.x - plate.getWidth() + 2)) {
				if (Math.abs(y + plate.getHeight() - RH.y) < 7) {
					a.addAtRight(plate);
				}
			} else if ((x < (LH.x + widthL - 2))
					&& x > (LH.x - plate.getWidth() + 2)) {
				if (Math.abs(y + plate.getHeight() - LH.y) < 7) {
					a.addAtLeft(plate);
				}
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
		if (x + plate.getWidth() < (view.gameWidth / (rowsNo * 2 + 3))
				* ((rowsNo * 50 - y) / 50)) { // Still not fall
			x += dx;
		} else
			// Fall now
			falling();
	}

	private void moveRightSide() {

		if (x > view.gameWidth
				- ((view.gameWidth / (rowsNo * 2 + 3)) * ((rowsNo * 50 - y) / 50)))
			// still not fall
			x += dx;
		else
			// Fall Now
			falling();
	}

	public void addNewShape() {
		// TODO Auto-generated method stub
		platePool.getNewSape();
		
	}

	public void generate() {
		// TODO Auto-generated method stub
		platePool.genreate();
		
	}

//	public ArrayList<Plate> getRightHandPlatesOne() {
//		// TODO Auto-generated method stub
//
//		return view.Players.get(0).getRightHandPlates();
//	}
//
//	public ArrayList<Plate> getLeftHandPlatesOne() {
//		// TODO Auto-generated method stub
//		return view.Players.get(0).getLeftHandPlates();
//	}
//
//	public ArrayList<Plate> getRightHandPlatesTwo() {
//		// TODO Auto-generated method stub
//
//		return view.Players.get(1).getRightHandPlates();
//	}
//
//	public ArrayList<Plate> getLeftHandPlatesTwo() {
//		// TODO Auto-generated method stub
//		return view.Players.get(1).getLeftHandPlates();
//	}

}
