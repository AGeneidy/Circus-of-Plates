import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import objectGenerator.Player;


public class mouseMotion implements MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		Player.getPlayer().mouseMove(e.getX());

	}

}
