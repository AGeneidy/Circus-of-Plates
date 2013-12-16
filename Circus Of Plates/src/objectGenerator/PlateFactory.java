package objectGenerator;

import java.util.Random;

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
		switch(new Random().nextInt(500) % 2){
		case 0 :
			return new OvalPlate();
		case 1 : 
			return new Ball();
		}
		return new OvalPlate();
	}

	@Override
	public Button getButton() {
		return null;
	}

	@Override
	public Player getPlayerOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayerTwo() {
		// TODO Auto-generated method stub
		return null;
	}
}
