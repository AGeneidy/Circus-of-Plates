package objectGenerator;

public class PlateFactory extends AbstractFactory{
	@Override
	Plate getPlate(String shapeType) {
		if (shapeType == null){
			return null;
		}else if(shapeType.equalsIgnoreCase("PLATE")){
			return new Plate();
		}
		
		return null;
	}

	@Override
	Player getPlayer() {
		return null;
	}
}
