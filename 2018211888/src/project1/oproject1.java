package project1;


public class oproject1 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Triangle triangle=new Triangle(1,1.5,1,"Yellow",true);
		System.out.println(triangle.toString());
		System.out.println("The area is:"+triangle.getArea());
		System.out.println("The perimeter is:"+triangle.getPerimeter());
		System.out.println("The color is:"+triangle.getColor());
		System.out.println("filled:"+triangle.isFill());
	}

}
class GeometricObject{
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
}
class Triangle extends GeometricObject{
	private double side1,side2,side3;
	public Triangle() {
		side1=1.0;
		side2=1.0;
		side3=1.0;
	}
	public Triangle(double a,double b,double c, String s,boolean f) {
		super(s,f);
		side1=a;
		side2=b;
		side3=c;
	}
	public double getArea() {
		double area=0,p=0;
		p=(side1+side2+side3)/2;
		area=Math.sqrt(p*(p-side1)*(p-side2)*(p-side3));
		return area;
	}
	public double getPerimeter() {
		return side1+side2+side3;
	}
	public String toString() {
		return "Triangle:side1="+side1+" side2="+side2+" side3="+side3;
	}
	public double getSide1() {
		return side1;
	}

	public void setSide1(double side1) {
		this.side1 = side1;
	}

	public double getSide2() {
		return side2;
	}

	public void setSide2(double side2) {
		this.side2 = side2;
	}

	public double getSide3() {
		return side3;
	}

	public void setSide3(double side3) {
		this.side3 = side3;
	}
}
