package objectGenerator;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class Button {

	private boolean mouseOnButton, clicked, moving;
	private int hight, width;
	String Name;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getHight() {
		return hight;
	}

	public void setHight(int hight) {
		this.hight = hight;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	private String type;
	private int x, y;

	public Button() {
		x = 0;
		y = 0;
		mouseOnButton = false;
		clicked = false;
		moving = false;
		hight = 130;
		width = 300;
	}

	public boolean isMouseOnButton() {
		return mouseOnButton;
	}

	public void setMouseOnButton(boolean mouseOnButton) {
		this.mouseOnButton = mouseOnButton;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getHeight() {
		return hight;
	}

	public int getWidth() {
		return width;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void paint(Graphics g, Applet view, URL url) {
		Image buttonImg = view.getImage(url, getImagePath());
		if (mouseOnButton)
			g.drawImage(buttonImg, x - 1, y - 2, view);
		else
			g.drawImage(buttonImg, x, y, view);
	}

	private String getImagePath() {
		
		switch(type){
		
		case "newGame":
			if (mouseOnButton)
				return "images/newGameButton2.png";
			return "images/newGameButton1.png";
		
		case "loadGame":
			if (mouseOnButton)
				return "images/loadGameButton2.png";
			return "images/loadGameButton1.png";
		case "exit":
			if (mouseOnButton)
				return "images/exit2.png";
			return "images/exit1.png";
			
		case "import":
			if (mouseOnButton)
				return "images/importButton2.png";
			return "images/importButton1.png";
		}
		
		
//		if (type == 1) { // onePlayerButton
//			if (mouseOnButton)
//				return "images/onePlayerButton2.png";
//			return "images/onePlayerButton1.png";
//		} else if (type == 2) { // twoPlayersButton
//			if (mouseOnButton)
//				return "images/twoPlayersButton2.png";
//			return "images/twoPlayersButton1.png";
//		} else if (type == 3) { // exitButton
//			if (mouseOnButton)
//				return "images/exit2.png";
//			return "images/exit1.png";
//
//		} else if (type == 4) { // saveButton
//			if (mouseOnButton)
//				return "images/button.png";
//			return "images/button.png";
//
//		} else if (type == 5) { // loadButton
//			if (mouseOnButton)
//				return "images/button.png";
//			return "images/button.png";
//		}
		return null;
	}
}
