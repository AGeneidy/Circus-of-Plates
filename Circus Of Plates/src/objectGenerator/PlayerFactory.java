package objectGenerator;

public class PlayerFactory extends AbstractFactory {
	private static PlayerFactory playerFactory;

	private PlayerFactory() {
	}

	@Override
	Plate getPlate(String shapeType) {
		return null;
	}

	@Override
	Player getPlayer() {
		return new Player();
	}

	protected static PlayerFactory getPlayerFactory() {
		if (playerFactory == null)
			return playerFactory = new PlayerFactory();
		else
			return playerFactory;
	}

}
