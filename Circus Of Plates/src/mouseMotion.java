import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class mouseMotion implements MouseMotionListener, MouseListener {
	View view;
	SerializeDemo fileLoader;

	public mouseMotion(View view) {
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
			
		} else if(view.now == 2){
			checkOnButton(view.onePlayerButton, e);
			checkOnButton(view.twoPlayersButton, e);
			checkOnButton(view.mainMenuButton, e);
			
		}else if(view.now == 3){
			view.Players.get(0).mouseMove(e.getX());
			checkOnButton(view.pauseButton, e);
		}else if(view.now == 4){
			checkOnButton(view.saveButton, e);
			checkOnButton(view.mainMenuButton, e);
		}else if(view.now == 5){
			checkOnButton(view.mainMenuButton, e);
		}
	}
	
	
	private void checkOnButton(Button b, MouseEvent e){
		if (e.getX() > b.getX() && e.getX() < b.getX() + b.getWidth() && e.getY() > b.getY() && e.getY() < b.getY() + b.getHeight()) {
			b.setMouseOnButton(true);
		} else
			b.setMouseOnButton(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(view.now == 1){
			checkClicked(view.newGameButton,e); //add one player
			checkClicked(view.loadGameButton,e); // add 2 players
			checkClicked(view.importButton,e); // add 2 players
			checkClicked(view.exitButton,e); // exit
		}else if(view.now == 2){
			checkClicked(view.onePlayerButton,e); // add 2 players
			checkClicked(view.twoPlayersButton,e); // exit
			checkClicked(view.mainMenuButton,e); // exit
		}else if(view.now == 3){
			checkClicked(view.pauseButton,e); // exit
		}else if(view.now == 4){
			checkClicked(view.saveButton,e); // exit
			checkClicked(view.mainMenuButton,e); // exit
		}else if(view.now == 5){
			checkClicked(view.mainMenuButton,e); // exit
		}
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
