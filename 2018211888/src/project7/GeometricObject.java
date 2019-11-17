package project7;

public abstract class GeometricObject {
	private String color;
	private boolean fill;
	public GeometricObject() {  //默认构造函数，为color fill赋初值
		// TODO 自动生成的构造函数存根
		setColor("White");
		setFill(false);
		
	}
	public GeometricObject(String str,boolean f) {    //含参构造函数，color fill赋特定值
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
	public abstract double getArea();  //计算面积
	public abstract double getPerimeter();  //计算周长
}
