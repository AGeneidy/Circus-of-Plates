package objectGenerator;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;

public class Ball extends Plate {
	public Ball() {
		// TODO Auto-generated constructor stub
		super();
		plateColor = chooseColor[new Random().nextInt(500) % 3];
		height = 30;
		width = 30;
		State.setType("Ball");

	}
	
	@Override
	public String getImagePath(){
		if(plateColor==Color.RED)
			return "images/redBall2.png";
		else if(plateColor==Color.BLUE)
			return "images/blueBall2.png";
		else if(plateColor==Color.GREEN)
			return "images/greenBall2.png";
		else{
			return "images/redBall2.png";
		}
	}

	@Override
	public void Paint(Graphics g,Applet view,URL url) {
		Image plateImg = view.getImage(url, getImagePath());
		g.drawImage(plateImg, getPosition().x, getPosition().y,view);
	}
}
