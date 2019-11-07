package project2;

public abstract class GeometricObject {
	private String color;
	private boolean fill;
	public GeometricObject() {
		// TODO 自动生成的构造函数存根
		setColor("White");
		setFill(true);
		
	}
	public GeometricObject(String str,boolean f) {
		setColor(str);
		setFill(f);
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public boolean isFill() {
		return fill;
	}
	public void setFill(boolean fill) {
		this.fill = fill;
	}
	public abstract double getArea();
	public abstract double getPerimeter();
}
