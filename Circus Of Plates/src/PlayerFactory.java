

public class PlayerFactory extends AbstractFactory {
	private static PlayerFactory playerFactory;
	private static Player playerOne;
	private static Player playerTwo;

	public static PlayerFactory getPlayerFactory() {
		if (playerFactory == null)
			return playerFactory = new PlayerFactory();
		else
			return playerFactory;
	}

	private PlayerFactory() {
		playerOne = new Player();
		playerTwo = new Player();
	}

	@Override
	public Plate getRandomPlate() {
		return null;
	}

	@Override
	public Player getPlayerOne() {
		return playerOne;
	}

	@Override
	public Player getPlayerTwo() {
		return playerTwo;
	}

	@Override
	public Button getButton() {
		return null;
	}

}
