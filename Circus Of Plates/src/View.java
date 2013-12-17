import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

public class View extends Applet implements Runnable {

	protected long time, startTime, timeBeforLoading, loadingTime,
			loadingTextTimer, loadingTextTimer2, gameStartTime, timeRemains;
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
	Image backMenu, back1, back2, backPause, backOverOne, backOverTwo;
	protected Controler Control;
	boolean mainMenu = true;
	Button newGameButton, loadGameButton, importButton, onePlayerButton,
			twoPlayersButton, exitButton, mainMenuButton, pauseButton,
			saveButton;
	ArrayList<Player> Players;
	private int logo = 0;
	int now = 0;
	boolean gameOver = false;
	SerializeDemo Serialize;

	private void main() {
		// TODO Auto-generated method stub

	}

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
		Serialize = SerializeDemo.getSerializeDemo(this);
		try {
			url = getDocumentBase();
		} catch (Exception e) {
		}

		back1 = getImage(url, "images/background1.png");
		back2 = getImage(url, "images/background2.png");
		backMenu = getImage(url, "images/back1.jpg");
		backPause = getImage(url, "images/background3.png");
		backOverOne = getImage(url, "images/background4.png");
		backOverTwo = getImage(url, "images/background5.png");

		getButtons();
	}

	public void addOnePlayer() {
		// TODO Auto-generated method stub
		Control.Zero();
		abstractfactory = FactoryProducer.getFactory("PLAYER");
		Player player = abstractfactory.getPlayerOne();
		player.setWindowattri(gameWidth, gameHeight); // <<<<<<<<<<<<<<<<<<<<<<
		Players.add(player);
	}

	public void addTwoPlayers() {
		Control.Zero();
		abstractfactory = FactoryProducer.getFactory("PLAYER");
		Player player = abstractfactory.getPlayerOne();
		player.setWindowattri(gameWidth, gameHeight); // <<<<<<<<<<<<<<<<<<<<<<
		Players.add(player);

		player = abstractfactory.getPlayerTwo();
		player.setWindowattri(gameWidth, gameHeight); // <<<<<<<<<<<<<<<<<<<<<<
		Players.add(player);
	}

	private void getButtons() {
		abstractfactory = FactoryProducer.getFactory("BUTTON");
		newGameButton = abstractfactory.getButton();
		newGameButton.setType("newGame");

		loadGameButton = abstractfactory.getButton();
		loadGameButton.setType("loadGame");

		importButton = abstractfactory.getButton();
		importButton.setType("import");

		onePlayerButton = abstractfactory.getButton();
		onePlayerButton.setType("onePlayer");

		twoPlayersButton = abstractfactory.getButton();
		twoPlayersButton.setType("twoPlayers");

		mainMenuButton = abstractfactory.getButton();
		mainMenuButton.setType("mainMenu");
		mainMenuButton.setWidth(200);
		mainMenuButton.setHight(86);

		exitButton = abstractfactory.getButton();
		exitButton.setType("exit");
		exitButton.setWidth(200);
		exitButton.setHight(86);

		pauseButton = abstractfactory.getButton();
		pauseButton.setType("pause");
		pauseButton.setWidth(200);
		pauseButton.setHight(86);
		pauseButton.setPosition(Width - pauseButton.getWidth() - 30, Height
				- (pauseButton.getHeight() / 2 + 50));

		saveButton = abstractfactory.getButton();
		saveButton.setType("save");
		saveButton.setWidth(200);
		saveButton.setHight(86);
		saveButton.setPosition((Width - saveButton.getWidth()) / 2, Height / 2
				- loadGameButton.getHight() / 2);
	}

	// /////////////////////////////////////////////////////////////////////////////////
	// /////Start///////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////

	@Override
	public void start() {
		Control = new Controler(this);
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {

		// time = System.currentTimeMillis();
		while (true) {
			timeBeforLoading = System.currentTimeMillis();
			loadingTime = loadingTextTimer = System.currentTimeMillis()
					- timeBeforLoading;
			excute();

		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////
	// //////loading/////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////\

	private void excute() {
		// TODO Auto-generated method stub
		switch (now) {
		case 0:
			loading();
			break;
		case 1:
			mainMenu();
			break;
		case 2:
			playersMenu();
			addPlayers();
			break;
		case 3:
			play();
			break;
		case 4:
			pauseMenu();
			break;
		case 5:
			gameOverMenu();
			break;
		}
	}

	private void loading() {
		while (now == 0) {
			loadingTime = System.currentTimeMillis() - timeBeforLoading;
			loadingTextTimer2 = System.currentTimeMillis() - loadingTextTimer;
			setSize(Width, Height);
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (loadingTime >= 2000)
				now = 1;
			repaint();
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////
	// ////mainMenu//////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////

	private void mainMenu() {
		while (now == 1) {
			setSize(Width, Height);

			mainMenuButtons();

			if (newGameButton.isClicked()) {
				now = 2; // players menu
				newGameButton.setClicked(false);
				Control.generate();
			} else if (loadGameButton.isClicked()){
				Control.generate();
				PlateIterator a = PlateIterator.getPlateIterator();
				loadGame();
				now = 3;
			}
			else if (importButton.isClicked())
				importShape();
			else if (exitButton.isClicked())
				System.exit(0);
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

	private void mainMenuButtons() {
		if (exitButton.getY() == 0) // buttons position were not set
			setMainMenuButtons();
		else if (newGameButton.isMoving() || loadGameButton.isMoving()
				|| importButton.isMoving() || exitButton.isMoving()) { // move
																		// buttons
			moveMainMenuButtons();
		}
	}

	private void moveMainMenuButtons() {
		int buttonsSpeed = 20;

		if (newGameButton.isMoving()) {
			newGameButton.setPosition(newGameButton.getX() + buttonsSpeed,
					newGameButton.getY());
			if (newGameButton.getX() >= Width / 2 - newGameButton.getWidth()
					/ 2)
				newGameButton.setMoving(false);
		}
		if (loadGameButton.isMoving()) {
			loadGameButton.setPosition(loadGameButton.getX() - buttonsSpeed,
					loadGameButton.getY());
			if (loadGameButton.getX() <= Width / 2 - loadGameButton.getWidth()
					/ 2)
				loadGameButton.setMoving(false);
		}

		if (importButton.isMoving()) {
			importButton.setPosition(importButton.getX() + buttonsSpeed,
					importButton.getY());
			if (importButton.getX() >= Width / 2 - importButton.getWidth() / 2)
				importButton.setMoving(false);
		}

		if (exitButton.isMoving()) {
			exitButton.setPosition(exitButton.getX() + buttonsSpeed * 2,
					exitButton.getY());
			if (exitButton.getX() >= Width - exitButton.getWidth() - 60)
				exitButton.setMoving(false);
		}
	}

	private void setMainMenuButtons() {
		newGameButton.setMoving(true);
		newGameButton.setPosition(0, Height / 2
				- (newGameButton.getHeight() * 3 / 2 + 50));

		loadGameButton.setMoving(true);
		loadGameButton.setPosition(Width - loadGameButton.getWidth(), Height
				/ 2 - loadGameButton.getHight() / 2);

		importButton.setMoving(true);
		importButton.setPosition(0, Height / 2 + loadGameButton.getHeight() / 2
				+ 50);

		exitButton.setMoving(true);
		exitButton.setPosition(0, Height - (exitButton.getHeight() / 2 + 50));

	}

	private void importShape() {
		// TODO Auto-generated method stub
		PlatePool a = PlatePool.getPlatePool();
		importButton.setClicked(false);
		Control.addNewShape();
	}

	private void loadGame() {
		// TODO Auto-generated method stub
		if(Control.platePool==null)
			Control.platePool = PlatePool.getPlatePool();
		Control.platePool.genreate();
		if(plateIterator!=null)
			plateIterator.reset();
		else
			plateIterator = PlateIterator.getPlateIterator();
		loadGameButton.setClicked(false);
		Serialize.load();
		abstractfactory = FactoryProducer.getFactory("PLAYER");
		Control.excuteFrame();


	}

	// //////////////////////////////////////////////////////////////////////////////////////
	// //////playersMenu/////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////

	private void playersMenu() {
		while (now == 2) {
			setSize(Width, Height);

			playersMenuButtons();

			if (onePlayerButton.isClicked()) {
				addOnePlayer();
				now = 3;
			} else if (twoPlayersButton.isClicked()) {
				addTwoPlayers();
				now = 3;
			} else if (mainMenuButton.isClicked()) {
				now = 1;
				init();
			}
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

	private void playersMenuButtons() {
		if (onePlayerButton.getY() == 0) // buttons position were not set
			setPlayersMenuButtons();
		else if (onePlayerButton.isMoving() || twoPlayersButton.isMoving()
				|| mainMenuButton.isMoving()) { // move buttons
			movePlayersMenuButtons();
		}
	}

	private void setPlayersMenuButtons() {
		onePlayerButton.setMoving(true);
		onePlayerButton.setPosition(0,
				Height / 2 - (onePlayerButton.getHeight() + 30));

		twoPlayersButton.setMoving(true);
		twoPlayersButton.setPosition(Width - twoPlayersButton.getWidth(),
				Height / 2 + 30);

		mainMenuButton.setMoving(true);
		mainMenuButton.setPosition(0, Height
				- (mainMenuButton.getHeight() / 2 + 50));
	}

	private void movePlayersMenuButtons() {
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
		if (mainMenuButton.isMoving()) {
			mainMenuButton.setPosition(
					mainMenuButton.getX() + buttonsSpeed * 2,
					mainMenuButton.getY());
			if (mainMenuButton.getX() >= Width - mainMenuButton.getWidth() - 60)
				mainMenuButton.setMoving(false);
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////
	// ////addPlayers////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////

	private void addPlayers() {
		// TODO Auto-generated method stub
		for (int i = 0; i < Players.size(); i++) {
			Players.get(i).setattributes(
					(1 + 2 * i) * gameWidth / (2 * Players.size()),
					gameHeight - Players.get(i).getHight());
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////
	// //////play////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////

	private void play() {
		gameStartTime = System.currentTimeMillis();
		pauseButton.setClicked(false);
		while (now == 3) {

			setSize(Width, Height);

			if (pauseButton.isClicked())
				now = 4;

			if (System.currentTimeMillis() - gameStartTime >= 120 * 1000) {
				gameOver = true;
				now = 5;
			}

			Control.excuteFrame();

			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			repaint();
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////
	// //////pauseMenu///////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////

	private void pauseMenu() {
		mainMenuButton.setClicked(false);
		mainMenuButton.setPosition(Width / 2 - mainMenuButton.getWidth() / 2,
				Height / 2 + mainMenuButton.getHeight() / 2 + 50);
		pauseButton.setClicked(true);
		while (now == 4) {
			setSize(Width, Height);

			pauseMenuButtons();

			if (saveButton.isClicked())
				save();
			else if (mainMenuButton.isClicked()) {
				now = 1;
//				main();
//				init();
			}

			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

	private void save() {
		// TODO Auto-generated method stub
		saveButton.setClicked(false);
		Serialize.save();
	}

	private void pauseMenuButtons() {
		// TODO Auto-generated method stub

	}

	// //////////////////////////////////////////////////////////////////////////////////////
	// //////gameOverMenu////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////

	private void gameOverMenu() {
		mainMenuButton.setClicked(false);
		mainMenuButton.setPosition(Width / 2 - mainMenuButton.getWidth() / 2,
				Height / 2 + mainMenuButton.getHeight() / 2 + 50);
		while (now == 5) {
			setSize(Width, Height);

			if (mainMenuButton.isClicked()) {
				now = 1;
				main();
				init();
			}

			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////
	// //////////////Painting///////////////////////////////////////////////////////////
	// //////////////Methods////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////

	@Override
	public void paint(Graphics g) {
		if (now == 0)
			paintLoading(g);
		else if (now == 1)
			paintMainMenu(g);
		else if (now == 2)
			paintPlayersMenu(g);
		else if (now == 3)
			paintGame(g);
		else if (now == 4)
			paintPauseMenu(g);
		else if (now == 5)
			paintGameOverMenu(g);
	}

	// /////////////////////////////////////////////////////////////////////////////////
	// /////paintLoading////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////

	private void paintGameOverMenu(Graphics g) {
		if (Players.size() == 1)
			g.drawImage(backOverOne, (int) backX, 0, Width, Height, this);
		else if (Players.size() == 2)
			g.drawImage(backOverTwo, (int) backX, 0, Width, Height, this);

		g.setColor(Color.getHSBColor(11, 11, 11));
		Font font = new Font("Serif", Font.BOLD, 40);
		g.setFont(font);

		String s = Integer.toString(Players.get(0).getScore());
		g.drawString(s, Width / 2 + 50, Height * 160 / 612);
		if (Players.size() == 2) {
			s = Integer.toString(Players.get(1).getScore());
			g.drawString(s, Width / 2 + 50, Height * 320 / 612);
		}

		mainMenuButton.paint(g, this, url);

	}

	private void paintLoading(Graphics g) {
		g.drawImage(backMenu, (int) backX, 0, Width, Height, this);
		paintLogo(g);
		if (loadingTextTimer2 <= 500)
			paintLoadingText(g, 1);
		else if (loadingTextTimer2 <= 1000)
			paintLoadingText(g, 2);
		else if (loadingTextTimer2 <= 1500)
			paintLoadingText(g, 3);
		else if (loadingTextTimer2 <= 2000)
			paintLoadingText(g, 4);
		else if (loadingTextTimer2 >= 2000)
			loadingTextTimer = System.currentTimeMillis();
	}

	private void paintLogo(Graphics g) {
		int size = 400;
		int x = Width / 2 - 200;
		int y = 100;
		if (logo == 0 || logo == 1) {
			Image playerImg = getImage(url, "images/logo1.png");
			g.drawImage(playerImg, x, y, size, size, this);
			logo++;
		} else if (logo == 2 || logo == 3) {
			Image playerImg = getImage(url, "images/logo2.png");
			g.drawImage(playerImg, x, y, size, size, this);
			logo++;
		} else if (logo == 4 || logo == 5) {
			Image playerImg = getImage(url, "images/logo3.png");
			g.drawImage(playerImg, x, y, size, size, this);
			logo++;
		} else if (logo == 6 || logo == 7) {
			Image playerImg = getImage(url, "images/logo4.png");
			g.drawImage(playerImg, x, y, size, size, this);
			logo++;
		} else if (logo == 8 || logo == 9) {
			Image playerImg = getImage(url, "images/logo5.png");
			g.drawImage(playerImg, x, y, size, size, this);
			logo++;
		} else if (logo == 10 || logo == 11) {
			Image playerImg = getImage(url, "images/logo6.png");
			g.drawImage(playerImg, x, y, size, size, this);
			if (logo == 10)
				logo++;
			else
				logo = 0;

		}
	}

	private void paintLoadingText(Graphics g, int image) {
		int sizeX = 150;
		int sizeY = 50;
		int x = Width / 2 - 75;
		int y = 500;
		if (image == 1) {
			Image playerImg = getImage(url, "images/loading1.png");
			g.drawImage(playerImg, x, y, sizeX, sizeY, this);
		} else if (image == 2) {
			Image playerImg = getImage(url, "images/loading2.png");
			g.drawImage(playerImg, x, y, sizeX, sizeY, this);
		} else if (image == 3) {
			Image playerImg = getImage(url, "images/loading3.png");
			g.drawImage(playerImg, x, y, sizeX, sizeY, this);
		} else if (image == 4) {
			Image playerImg = getImage(url, "images/loading4.png");
			g.drawImage(playerImg, x, y, sizeX, sizeY, this);
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////
	// ///////paintMainMenu/////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////

	private void paintMainMenu(Graphics g) {
		g.drawImage(backMenu, (int) backX, 0, Width, Height, this);
		if (newGameButton.getY() != 0) {
			newGameButton.paint(g, this, url);
			loadGameButton.paint(g, this, url);
			importButton.paint(g, this, url);
			exitButton.paint(g, this, url);
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////
	// ////paintPlayersMenu/////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////

	private void paintPlayersMenu(Graphics g) {
		g.drawImage(backMenu, (int) backX, 0, Width, Height, this);
		if (onePlayerButton.getY() != 0) {
			onePlayerButton.paint(g, this, url);
			twoPlayersButton.paint(g, this, url);
			mainMenuButton.paint(g, this, url);
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////
	// /////paintGame///////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////

	private void paintGame(Graphics g) {
		if (Players.size() == 1)
			g.drawImage(back1, (int) backX, 0, Width, Height, this);
		else if (Players.size() == 2)
			g.drawImage(back2, (int) backX, 0, Width, Height, this);

		g.setColor(Color.getHSBColor(11, 11, 11));
		Font font = new Font("Serif", Font.BOLD, 40);
		g.setFont(font);

		String s = Long
				.toString(120 - (System.currentTimeMillis() - gameStartTime) / 1000);
		g.drawString(s, Width - 150, (int) Height * 110 / 612);

		s = Integer.toString(Players.get(0).getScore());
		g.drawString(s, Width - 150, Height * 290 / 612);
		if (Players.size() == 2) {
			s = Integer.toString(Players.get(1).getScore());
			g.drawString(s, Width - 150, Height * 420 / 612);
		}
		plateIterator = PlateIterator.getPlateIterator();
		plateIterator.reset();
		while (plateIterator.hasnext()) {
			plate = plateIterator.next();
			if (plate.getState().equalsIgnoreCase("OnPlayer"))
				continue;
			plate.Paint(g, this, url);
		}
		for (Player a : Players)
			a.paint(g, this, url);

		pauseButton.paint(g, this, url);
	}

	// /////////////////////////////////////////////////////////////////////////////////
	// /////paintPauseMenu//////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////

	private void paintPauseMenu(Graphics g) {
		g.drawImage(backPause, (int) backX, 0, Width, Height, this);
		if (saveButton.getY() != 0) {
			saveButton.paint(g, this, url);
			mainMenuButton.paint(g, this, url);
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////

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
