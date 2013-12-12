package objectGenerator;

public class PlateFactory extends AbstractFactory{
	private static PlateFactory plateFactory ;
	private PlateFactory(){}
	
	protected static PlateFactory getPlateFactory() {
		// TODO Auto-generated method stub
		if(plateFactory==null)
			return plateFactory = new PlateFactory();
		else return plateFactory;
	}

	@Override
	Plate getPlate(String shapeType) {
		if (shapeType == null){
			return null;
		}else if(shapeType.equalsIgnoreCase("OVAL")){
			return new OvalPlate();
		}
		
		return null;
	}

	@Override
	Player getPlayer() {
		return null;
	}
}
