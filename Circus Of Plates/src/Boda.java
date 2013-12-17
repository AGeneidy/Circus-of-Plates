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

	protected long time, startTime, timeBeforLoading, loadingTime, loadingTextTimer, loadingTextTimer2;
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
	Button newGameButton,loadGameButton,importButton,onePlayerButton,twoPlayersButton,exitButton;
	ArrayList<Player> Players;
	private int logo = 0;
	int now = 0;

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

		abstractfactory = FactoryProducer.getFactory("BUTTON");
		getButtons();
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
		newGameButton = abstractfactory.getButton();
		newGameButton.setType("newGame");

		loadGameButton = abstractfactory.getButton();
		loadGameButton.setType("loadGame");
		
		importButton = abstractfactory.getButton();
		importButton.setType("import");

		exitButton = abstractfactory.getButton();
		exitButton.setType("exit");
		exitButton.setWidth(200);
		exitButton.setHight(86);
	}

	// /////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////

	@Override
	public void start() {
		Control = new Controler(this);

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {

//		time = System.currentTimeMillis();
		
		timeBeforLoading = System.currentTimeMillis();
		loadingTime =loadingTextTimer = System.currentTimeMillis() - timeBeforLoading;
		loading();
		
		mainMenu();
		
		addPlayers();
		
		play();

	}

	private void loading() {
		while (now == 0) {
			loadingTime = System.currentTimeMillis() - timeBeforLoading;
			loadingTextTimer2 = System.currentTimeMillis() - loadingTextTimer;
			setSize(Width, Height);
			Control.excuteFrame();
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

	private void mainMenu() {
		while (now == 1) {
			setSize(Width, Height);
			
			mainMenuButtons();
			
			if(newGameButton.isClicked());
				
			else if(loadGameButton.isClicked())
				loadGame();
			else if(importButton.isClicked())
				importShape();
			else if(exitButton.isClicked());
				
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
	
	private void importShape() {
		// TODO Auto-generated method stub
		
	}

	private void loadGame() {
		// TODO Auto-generated method stub
		
	}

	private void play(){
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

	private void mainMenuButtons() {
		if (exitButton.getY() == 0) // buttons position were not set
			setMainMenuButtons();
		else if (newGameButton.isMoving() || loadGameButton.isMoving() || importButton.isMoving()
				|| exitButton.isMoving()) { // move buttons
			moveMainMenuButtons();
		} else {
		}
	}

	private void moveMainMenuButtons() {
		int buttonsSpeed = 20;

		if (newGameButton.isMoving()) {
			newGameButton.setPosition(newGameButton.getX() + buttonsSpeed, newGameButton.getY());
			if (newGameButton.getX() >= Width / 2 - newGameButton.getWidth() / 2)
				newGameButton.setMoving(false);
		}
		if (loadGameButton.isMoving()) {
			loadGameButton.setPosition(loadGameButton.getX() - buttonsSpeed,
					loadGameButton.getY());
			if (loadGameButton.getX() <= Width / 2 - loadGameButton.getWidth() / 2)
				loadGameButton.setMoving(false);
		}
		
		if (importButton.isMoving()) {
			importButton.setPosition(importButton.getX() + buttonsSpeed, importButton.getY());
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
		newGameButton.setPosition(0, Height / 2 - (newGameButton.getHeight()*3/2 + 50));

		loadGameButton.setMoving(true);
		loadGameButton.setPosition(Width - loadGameButton.getWidth(), Height / 2 - loadGameButton.getHight()/2);
		
		importButton.setMoving(true);
		importButton.setPosition(0, Height / 2 + loadGameButton.getHeight()/2 + 50);

		exitButton.setMoving(true);
		exitButton.setPosition(0, Height - (exitButton.getHeight()/2 + 50));

	}

	private void addPlayers() {
		// TODO Auto-generated method stub
		for (int i = 0; i < Players.size(); i++) {
			Players.get(i).setattributes(
					(1 + 2 * i) * gameWidth / (2 * Players.size()),
					gameHeight - Players.get(i).getHight());
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////
	// //////////////Painting
	// Methods///////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////

	@Override
	public void paint(Graphics g) {
		if(now == 0)
			paintLoading(g);
		else if(now == 1)
			paintMainMenu(g);
		// paintGame(g);
	}
	
	private void paintLoading(Graphics g){
		g.drawImage(back1, (int) backX, 0, Width, Height, this);
		paintLogo(g);
		if(loadingTextTimer2<=500)
			paintLoadingText(g,1);
		else if (loadingTextTimer2<=1000)
			paintLoadingText(g, 2);
		else if (loadingTextTimer2<=1500)
			paintLoadingText(g, 3);
		else if (loadingTextTimer2<=2000)
			paintLoadingText(g, 4);
		else if (loadingTextTimer2>=2000)
			loadingTextTimer = System.currentTimeMillis();
	}
	
	private void paintMainMenu(Graphics g) {
		g.drawImage(back1, (int) backX, 0, Width, Height, this);
		newGameButton.paint(g, this, url);
		loadGameButton.paint(g, this, url);
		importButton.paint(g, this, url);
		exitButton.paint(g, this, url);
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

	private void paintGame(Graphics g) {
		g.drawImage(back, (int) backX, 0, Width, Height, this);

		plateIterator = PlateIterator.getPlateIterator();
		while (plateIterator.hasnext()) {
			plate = plateIterator.next();
			if (plate.getState().equalsIgnoreCase("OnPlayer"))
				continue;
			plate.Paint(g, this, url);
		}
		for (Player a : Players)
			a.paint(g, this, url);

		exitButton.paint(g, this, url);
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
