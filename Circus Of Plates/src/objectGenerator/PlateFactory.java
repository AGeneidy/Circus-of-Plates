package objectGenerator;

public class PlateFactory extends AbstractFactory{
	private static PlateFactory plateFactory ;
	private PlateFactory(){}
	
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
	protected static PlateFactory getPlateFactory() {
		// TODO Auto-generated method stub
		if(plateFactory==null)return plateFactory=new PlateFactory();
		else return plateFactory;

	}
}
