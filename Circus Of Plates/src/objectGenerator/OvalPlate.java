package objectGenerator;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class OvalPlate extends Plate {
	public OvalPlate() {
		// TODO Auto-generated constructor stub
		super();
		height = 25;
		width = 50;
	}
	
	@Override
	public String getImagePath(){
		
		if(plateColor==Color.RED)
			return "images/platered50-25.png";
		else if(plateColor==Color.BLUE)
			return "images/plateblue50-25.png";
		else if(plateColor==Color.GREEN)
			return "images/plategreen50-25.png";
		
		return null;
	}

	@Override
	public void Paint(Graphics g,Applet view,URL url) {
//		Graphics2D g2 = (Graphics2D) g;
//		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//				RenderingHints.VALUE_ANTIALIAS_ON);
//		g2.setColor(plateColor);
//		g2.fillOval(position.x, position.y, width, height);
		Image plateImg = view.getImage(url, getImagePath());
		g.drawImage(plateImg, getPosition().x, getPosition().y,view);
	}
}
