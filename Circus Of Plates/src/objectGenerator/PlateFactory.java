package objectGenerator;

public class PlateFactory extends AbstractFactory {
	private static PlateFactory plateFactory;

	private PlateFactory() {
	}

	protected static PlateFactory getPlateFactory() {
		// TODO Auto-generated method stub
		if (plateFactory == null)
			return plateFactory = new PlateFactory();
		else
			return plateFactory;
	}

	@Override
	public Plate getRandomPlate() {
		return new OvalPlate();
	}

	@Override
	public Player getPlayer() {
		return null;
	}

	@Override
	public Button getButton() {
		return null;
	}
}
