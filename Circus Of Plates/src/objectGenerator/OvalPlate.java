package objectGenerator;

import java.awt.Graphics;

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
		g.setColor(plateColor);
		g.fillOval(position.x, position.y, width, height);
	}
}
