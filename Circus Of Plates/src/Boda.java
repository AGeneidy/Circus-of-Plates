import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

import objectGenerator.AbstractFactory;
import objectGenerator.FactoryProducer;
import objectGenerator.Plate;
import objectGenerator.PlateIterator;
import objectGenerator.PlatePool;
import objectGenerator.Player;

public class Boda extends Applet implements Runnable {

	protected long time1;
	private PlateIterator plateIterator;
	private Image i;
	private Graphics doubleG;
	protected int x, y, initialDx, initialDy;
	protected double dy;
	protected double dx;
	private AbstractFactory abstractfactory;
	protected Player player1;
	private Plate plate;
	private int Height = 1000, Width = 600;
	private double backX, backDx;
	URL url;
	Image back, plateImg;
	private Controler Control;

	@Override
	public void init() {

		setSize(Height, Width);
		abstractfactory = FactoryProducer.getFactory("player");

		player1 = abstractfactory.getPlayer();
		player1.setWindowattri(this.getWidth(), this.getHeight());
		player1.setattributes(this.getWidth() / 2, this.getHeight() - 60);

		this.addKeyListener(new handleKeyBoard());
		this.addMouseMotionListener(new mouseMotion());

		try {
			url = getDocumentBase();
		} catch (Exception e) {

		}

		back = getImage(url, "images/background.png");

		// setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		// GraphicsEnvironment ge =
		// GraphicsEnvironment.getLocalGraphicsEnvironment();
		// GraphicsDevice[] devices = ge.getScreenDevices();
		// devices[0].setFullScreenWindow(this.Boda);
	}

	@Override
	public void start() {

		Control = new Controler(this);
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		time1 = System.currentTimeMillis();
		while (true) {
			Control.excuteFrame();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Graphics g) {
		if (i == null) {
			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();
		}

		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);

		doubleG.setColor(getForeground());
		paint(doubleG);

		g.drawImage(i, 0, 0, this);
	}

	@Override
	public void paint(Graphics g) {
		// g.setColor(new Color(15,77,147));
		// g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(back, (int) backX, 0, this);

		plateIterator = PlateIterator.getPlateIterator();

		String path = new String();

		while (plateIterator.hasnext()) {
			plate = plateIterator.next();
			if(plate.isOnPlayer())continue;
			path = plate.getImagePath();
			plateImg = getImage(url, path);
			g.drawImage(plateImg, plate.getPosition().x, plate.getPosition().y,
					this);
		}
		ArrayList<Plate> a = Control.getRightHandPlates();
		for(Plate b : a){
			path = b.getImagePath();
			plateImg = getImage(url, path);
			g.drawImage(plateImg, b.getPosition().x, b.getPosition().y,
					this);
		}
		a = Control.getLeftHandPlates();
		for(Plate b : a){
			path = b.getImagePath();
			plateImg = getImage(url, path);
			g.drawImage(plateImg, b.getPosition().x, b.getPosition().y,
					this);
		}
//		player1.paint(g);
		
//		path = player1.getImagePath();
		path = "images/clown.png";
		plateImg = getImage(url, path);
		g.drawImage(plateImg, player1.getLeftCenter().x-10, player1.getLeftCenter().y-100,
				this);
		
	}

}
