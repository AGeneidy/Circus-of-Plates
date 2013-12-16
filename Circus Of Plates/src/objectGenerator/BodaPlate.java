package objectGenerator;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class BodaPlate extends Plate {
	public BodaPlate() {
		// TODO Auto-generated constructor stub
		super();
		height = 50;
		width = 100;
		plateColor = chooseColor[1];
		State.setType("BodaPlate");

	}
	
	@Override
	public String getImagePath(){
			return "images/plategreen50-251.png";
	}

	@Override
	public void Paint(Graphics g,Applet view,URL url) {
//		Graphics2D g2 = (Graphics2D) g;
//		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//				RenderingHints.VALUE_ANTIALIAS_ON);
//		g2.setColor(plateColor);
//		g2.fillOval(position.x, position.y, width, height);
		Image plateImg = view.getImage(url, getImagePath());
//		Image img = plateImg.getScaledInstance(50, 50,
//				 java.awt.Image.SCALE_SMOOTH);
		g.drawImage(plateImg, getPosition().x, getPosition().y,view);
	}
}
