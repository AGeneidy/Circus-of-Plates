package objectGenerator;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class Button {
	
	private boolean mouseOnButton,clicked,moving;
	private int hight, width;
	public int getHight() {
		return hight;
	}

	public void setHight(int hight) {
		this.hight = hight;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	private int type;
	private int x,y;

	public Button(){
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
		
	public void setType(int type) {
		this.type = type;
	}


	public void paint(Graphics g,Applet view,URL url) {		
		Image buttonImg = view.getImage(url, getImagePath());
		g.drawImage(buttonImg, x, y,view);
	}

	private String getImagePath() {
		if(type==1){ //onePlayerButton
			if(mouseOnButton)
				return "images/onePlayerButton2.png";
			return "images/onePlayerButton1.png";
		}
		else if(type == 2){ //twoPlayersButton
			if(mouseOnButton)
				return "images/twoPlayersButton2.png";
			return "images/twoPlayersButton1.png";
		}		
		else if(type == 3){ //exitButton
			if(mouseOnButton)
				return "images/exit2.png";
			return "images/exit1.png";
		}
		return null;
	}
}
