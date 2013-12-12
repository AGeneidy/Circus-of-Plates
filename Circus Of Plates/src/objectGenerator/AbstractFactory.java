package objectGenerator;

public abstract class AbstractFactory {
	abstract Plate getPlate(String shapeType);
	abstract Player getPlayer();
}