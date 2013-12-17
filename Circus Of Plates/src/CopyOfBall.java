

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;

public class CopyOfBall extends Plate {
	public CopyOfBall() {
		// TODO Auto-generated constructor stub
		super();
		plateColor = new Random().nextInt(500) % 3;
		height = 30;
		width = 30;
		State.setType("Ball");

	}

	@Override
	public String getImagePath() {
		switch (plateColor) {
		case 0:
			return "images/blueBall2.png";
		case 1:
			return "images/redBall2.png";
		case 2:
			return "images/greenBall2.png";
		default:
			return "images/redBall2.png";
		}
	}

	@Override
	public void Paint(Graphics g, Applet view, URL url) {
		Image plateImg = view.getImage(url, getImagePath());
		g.drawImage(plateImg, getPosition().x, getPosition().y, view);
	}
}
