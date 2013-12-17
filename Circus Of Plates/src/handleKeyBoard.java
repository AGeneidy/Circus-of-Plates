import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class handleKeyBoard implements KeyListener {
	private int moveSpeed = 20;
	private View view;

	public handleKeyBoard(View view) {
		this.view = view;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
		switch (e.getKeyCode()) {
		case 39:
			view.Players.get(0).move(moveSpeed);
			return;
		case 37:
			view.Players.get(0).move(-moveSpeed);
			return;
		case 65:
			if (view.Players.size()==2)
				view.Players.get(1).move(-moveSpeed);
			return;
		case 68:
			if (view.Players.size()==2)
				view.Players.get(1).move(moveSpeed);
			return;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
		// 65 is A
		// 68 is D
		switch (e.getKeyCode()) {
		case 39:
			view.Players.get(0).move(moveSpeed);
			return;
		case 37:
			view.Players.get(0).move(-moveSpeed);
			return;
		case 65:
			if (view.Players.size()==2)
				view.Players.get(1).move(-moveSpeed);
			return;
		case 68:
			if (view.Players.size()==2)
				view.Players.get(1).move(moveSpeed);
			return;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case 39:
			view.Players.get(0).move(moveSpeed);
			return;
		case 37:
			view.Players.get(0).move(-moveSpeed);
			return;
		case 65:
			if (view.Players.size()==2)
				view.Players.get(1).move(-moveSpeed);
			return;
		case 68:
			if (view.Players.size()==2)
				view.Players.get(1).move(moveSpeed);
			return;
		}
	}

}
