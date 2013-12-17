import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import objectGenerator.Button;
import objectGenerator.Player;

public class mouseMotion implements MouseMotionListener, MouseListener {
	Boda view;
	SerializeDemo fileLoader;

	public mouseMotion(Boda view) {
		this.view = view;
		fileLoader = new SerializeDemo(view);
		int k = 0;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (view.now == 1) {//main menu
			checkOnButton(view.newGameButton, e);
			checkOnButton(view.loadGameButton, e);
			checkOnButton(view.importButton, e);
			checkOnButton(view.exitButton, e);
		} else;
//			view.Players.get(0).mouseMove(e.getX());
	}
	
	
	private void checkOnButton(Button b, MouseEvent e){
		if (e.getX() > b.getX() && e.getX() < b.getX() + b.getWidth() && e.getY() > b.getY() && e.getY() < b.getY() + b.getHeight()) {
			b.setMouseOnButton(true);
		} else
			b.setMouseOnButton(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		checkClicked(view.newGameButton,e); //add one player
		checkClicked(view.loadGameButton,e); // add 2 players
		checkClicked(view.importButton,e); // add 2 players
		checkClicked(view.exitButton,e); // exit
	}
	
	private void checkClicked(Button b, MouseEvent e){
		if (b.isMouseOnButton()) {
			b.setClicked(true);
		} else
			b.setClicked(false);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
