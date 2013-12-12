package objectGenerator;

public class PlayerFactory extends AbstractFactory{

	@Override
	Plate getPlate(String shapeType) {
		return null;
	}

	@Override
	Player getPlayer() {
		return new Player();
	}

}
