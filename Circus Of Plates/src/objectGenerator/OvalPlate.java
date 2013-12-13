package objectGenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.crypto.spec.PSource;

public class OvalPlate extends Plate {
	public OvalPlate() {
		// TODO Auto-generated constructor stub
		super();
		height = 15;
		width = 25;
	}

	@Override
	public void Paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(plateColor);
		g2.fillOval(position.x, position.y, width, height);
	}
}
