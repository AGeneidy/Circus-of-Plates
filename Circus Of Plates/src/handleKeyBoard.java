import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import objectGenerator.Player;

public class handleKeyBoard implements KeyListener {
	private int moveSpeed = 20;
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
		switch (e.getKeyCode()) {
		case 39:
			Player.getPlayer().move(moveSpeed);
			return;
		case 37:
			Player.getPlayer().move(-moveSpeed);
			return;
		case 32:
			try {
				wait(50000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case 39:
			Player.getPlayer().move(moveSpeed);
			return;
		case 37:
			Player.getPlayer().move(-moveSpeed);
			return;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case 39:
			Player.getPlayer().move(moveSpeed);
			return;
		case 37:
			Player.getPlayer().move(-moveSpeed);
			return;
		}
	}

}
