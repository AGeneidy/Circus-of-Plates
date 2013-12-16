package objectGenerator;

public class PlayerFactory extends AbstractFactory {
	private static PlayerFactory playerFactory;

	public static PlayerFactory getPlayerFactory() {
		if (playerFactory == null)
			return playerFactory = new PlayerFactory();
		else
			return playerFactory;
	}

	private PlayerFactory() {
	}

	@Override
	public Plate getRandomPlate() {
		return null;
	}

	@Override
	public Player getPlayer() {
		return new Player();
	}

	@Override
	public Button getButton() {
		return null;
	}

}
