import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;

import objectGenerator.AbstractFactory;
import objectGenerator.Button;
import objectGenerator.FactoryProducer;
import objectGenerator.Plate;
import objectGenerator.PlateIterator;
import objectGenerator.Player;

public class Boda extends Applet implements Runnable {

	protected long time;
	private PlateIterator plateIterator;
	private Image i;
	private Graphics doubleG;
	protected int x, y, initialDx, initialDy;
	protected double dy;
	protected double dx;
	private AbstractFactory abstractfactory;
	private Plate plate;
	private int Height, Width;
	int gameWidth, gameHeight;
	private double backX, backDx;
	URL url;
	Image back1, back;
	private Controler Control;
	boolean mainMenu = true;
	Button onePlayerButton, twoPlayersButton, exitButton;
	ArrayList<Player> Players;

	@Override
	public void init() {

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Height = dim.height - 100;
		Width = dim.width - 100;
		setSize(Width, Height);
		gameWidth = Width * 930 / 1250;
		gameHeight = Height;
		Players = new ArrayList<Player>();
		this.addKeyListener(new handleKeyBoard(this));
		this.addMouseMotionListener(new mouseMotion(this));
		this.addMouseListener(new mouseMotion(this));

		try {
			url = getDocumentBase();
		} catch (Exception e) {
		}

		back = getImage(url, "images/background.png");
		back1 = getImage(url, "images/back1.jpg");

		// getPlayers();
		abstractfactory = FactoryProducer.getFactory("BUTTON");
		getButtons();

		// setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		// GraphicsEnvironment ge =
		// GraphicsEnvironment.getLocalGraphicsEnvironment();
		// GraphicsDevice[] devices = ge.getScreenDevices();
		// devices[0].setFullScreenWindow(this.Boda);
	}

	public void addOnePlayer() {
		// TODO Auto-generated method stub
		abstractfactory = FactoryProducer.getFactory("PLAYER");

		Player player = abstractfactory.getPlayerOne();
		player.setWindowattri(gameWidth, gameHeight); // <<<<<<<<<<<<<<<<<<<<<<
		Players.add(player);

	}

	public void addTwoPlayers() {
		abstractfactory = FactoryProducer.getFactory("PLAYER");

		Player player = abstractfactory.getPlayerOne();
		player.setWindowattri(gameWidth, gameHeight); // <<<<<<<<<<<<<<<<<<<<<<
		Players.add(player);
		player = abstractfactory.getPlayerTwo();
		player.setWindowattri(gameWidth, gameHeight); // <<<<<<<<<<<<<<<<<<<<<<
		Players.add(player);
	}

	private void getButtons() {
		onePlayerButton = abstractfactory.getButton();
		onePlayerButton.setType(1);

		twoPlayersButton = abstractfactory.getButton();
		twoPlayersButton.setType(2);

		exitButton = abstractfactory.getButton();
		exitButton.setType(3);
		exitButton.setWidth(200);
		exitButton.setHight(86);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////

	@Override
	public void start() {
		Control = new Controler(this);

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		time = System.currentTimeMillis();

		while (mainMenu) {
			setSize(Width, Height);
			mainMenu();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
		for (int i = 0; i < Players.size(); i++) {
			Players.get(i).setattributes(
					(1 + 2 * i) * gameWidth / (2 * Players.size()),
					gameHeight - Players.get(i).getHight());
		}
		// if (twoPlayers) {
		// player1.setattributes(gameWidth / 4,
		// gameHeight - player1.getHight());
		// player2.setattributes(gameWidth * 3 / 4,
		// gameHeight - player2.getHight());
		//
		// } else {
		// player1.setattributes(gameWidth / 2,
		// gameHeight - player1.getHight());
		// }

		while (true) {
			setSize(Width, Height);
			Control.excuteFrame();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

	private void mainMenu() {
		if (exitButton.getY() == 0) // buttons position were not set
			setButtons();
		else if (onePlayerButton.isMoving() || twoPlayersButton.isMoving()
				|| exitButton.isMoving()) { // move buttons
			moveButtons();
		} else {
		}
	}

	private void moveButtons() {
		int buttonsSpeed = 20;

		if (onePlayerButton.isMoving()) {
			onePlayerButton.setPosition(onePlayerButton.getX() + buttonsSpeed,
					onePlayerButton.getY());
			if (onePlayerButton.getX() >= Width / 2
					- onePlayerButton.getWidth() / 2)
				onePlayerButton.setMoving(false);
		}
		if (twoPlayersButton.isMoving()) {
			twoPlayersButton.setPosition(
					twoPlayersButton.getX() - buttonsSpeed,
					twoPlayersButton.getY());
			if (twoPlayersButton.getX() <= Width / 2
					- twoPlayersButton.getWidth() / 2)
				twoPlayersButton.setMoving(false);
		}
		if (exitButton.isMoving()) {
			exitButton.setPosition(exitButton.getX() + buttonsSpeed * 2,
					exitButton.getY());
			if (exitButton.getX() >= Width - exitButton.getWidth() - 60)
				exitButton.setMoving(false);
		}
	}

	private void setButtons() {
		onePlayerButton.setMoving(true);
		onePlayerButton.setPosition(0,
				Height / 2 - (onePlayerButton.getHeight() + 30));

		twoPlayersButton.setMoving(true);
		twoPlayersButton.setPosition(Width - twoPlayersButton.getWidth(),
				Height / 2 + 30);

		exitButton.setMoving(true);
		exitButton.setPosition(0, Height - (exitButton.getHeight() + 30));

	}

	// ////////////////////////////////////////////////////////////////////////////
	// ////////Painting
	// Methods////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////

	private void paintMainMenu(Graphics g) {
		g.drawImage(back1, (int) backX, 0, Width, Height, this);

		onePlayerButton.paint(g, this, url);
		twoPlayersButton.paint(g, this, url);
		exitButton.paint(g, this, url);
	}

	private void paintGame(Graphics g) {
		g.drawImage(back, (int) backX, 0, Width, Height, this);

		plateIterator = PlateIterator.getPlateIterator();
		while (plateIterator.hasnext()) {
			plate = plateIterator.next();
			if (plate.getState().equalsIgnoreCase("OnPlayer"))
				continue;
			plate.Paint(g, this, url);
		}
		for(Player a : Players)
			a.paint(g, this, url);
//		if (twoPlayers)
//			player2.paint(g, this, url);
		exitButton.paint(g, this, url);

	}

	@Override
	public void paint(Graphics g) {
		if (mainMenu) {
			paintMainMenu(g);
		} else
			paintGame(g);

	}

	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////

	@Override
	public void update(Graphics g) {
		if (i == null) {
			i = createImage(Width, Height);
			doubleG = i.getGraphics();
		}

		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, Width, Height);

		doubleG.setColor(getForeground());
		paint(doubleG);

		g.drawImage(i, 0, 0, this);
	}

	@Override
	public void stop() {
	}

	@Override
	public void destroy() {
	}

}
