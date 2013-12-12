package objectGenerator;

public class PlayerFactory extends AbstractFactory {
	private static PlayerFactory playerFactory;
	
	protected static PlayerFactory getPlayerFactory() {
		if (playerFactory == null)
			return playerFactory = new PlayerFactory();
		else
			return playerFactory;
	}
	
	private PlayerFactory() {
	}

	@Override
	Plate getRandomPlate() {
		return null;
	}

	@Override
	Player getPlayer() {
		return new Player();
	}

}
