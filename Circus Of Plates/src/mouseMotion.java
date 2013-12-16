import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import objectGenerator.Player;


public class mouseMotion implements MouseMotionListener, MouseListener {
	Boda view;

	public mouseMotion(Boda view){
		this.view = view;
		int k = 0;
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(view.mainMenu){
			if(e.getX() > view.onePlayerButton.getX() && e.getX() < view.onePlayerButton.getX()+view.onePlayerButton.getWidth() && e.getY() > view.onePlayerButton.getY() && e.getY() < view.onePlayerButton.getY()+view.onePlayerButton.getHeight()){
					view.onePlayerButton.setMouseOnButton(true);
			}else
				view.onePlayerButton.setMouseOnButton(false);
			
			if(e.getX() > view.twoPlayersButton.getX() && e.getX() < view.twoPlayersButton.getX()+view.twoPlayersButton.getWidth()&& e.getY() > view.twoPlayersButton.getY() && e.getY() < view.twoPlayersButton.getY()+view.twoPlayersButton.getHeight()){
					view.twoPlayersButton.setMouseOnButton(true);
			}else
				view.twoPlayersButton.setMouseOnButton(false);		
		
		}else
		view.Players.get(0).mouseMove(e.getX());
		
		if(e.getX() > view.exitButton.getX() && e.getX() < view.exitButton.getX()+view.exitButton.getWidth() && e.getY() > view.exitButton.getY() && e.getY() < view.exitButton.getY()+view.exitButton.getHeight()){
			view.exitButton.setMouseOnButton(true);
		}else
			view.exitButton.setMouseOnButton(false);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(11);
		if(view.onePlayerButton.isMouseOnButton()){
			view.onePlayerButton.setClicked(true);
			view.addOnePlayer();
			view.mainMenu = false;
			System.out.println(view.mainMenu);
		}else
			view.onePlayerButton.setClicked(false);
	
		if(e.getX() > view.twoPlayersButton.getX() && e.getX() < view.twoPlayersButton.getX()+view.twoPlayersButton.getWidth()&& e.getY() > view.twoPlayersButton.getY() && e.getY() < view.twoPlayersButton.getY()+view.twoPlayersButton.getHeight()){
				view.twoPlayersButton.setClicked(true);
				view.addTwoPlayers();
				view.mainMenu = false;
		}else
			view.twoPlayersButton.setClicked(false);
		
		if(e.getX() > view.exitButton.getX() && e.getX() < view.exitButton.getX()+view.exitButton.getWidth() && e.getY() > view.exitButton.getY() && e.getY() < view.exitButton.getY()+view.exitButton.getHeight()){
				view.exitButton.setClicked(true);
		}else
			view.exitButton.setClicked(false);		
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
