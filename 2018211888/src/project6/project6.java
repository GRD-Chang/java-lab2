package project6;

import java.util.Scanner;

public class project6 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		double a = 0,b = 0,c = 0;
		boolean jud=true;
		Scanner scanner=new Scanner(System.in);
		while (jud) {
		System.out.println("请输入三边边长：");
		try {
			a=scanner.nextDouble();
			b=scanner.nextDouble();
			c=scanner.nextDouble();
			if(a+b>c&&b+c>a&&a+c>b) {  //判断输入数据是否能组成三角形
				jud=false;
			}
			else { //无法组成
				System.out.println("无法构成三角形");
			}
		} catch (Exception e) {   //输入不为double类型
			// TODO: handle exception
			System.out.println("输入不符合要求，请重新输入");
			scanner.nextLine();
		}
		}
		System.out.println("请输入颜色：");
		String string=scanner.next();
		System.out.println("请输入是否填充");
		boolean filled=false;
		try {
			filled=scanner.nextBoolean();
		} catch (Exception e) {  //输入不为boolen类型
			// TODO: handle exception
			System.out.println("输入错误，默认fill=false");
		}
		Triangle triangle=new Triangle(a,b,c,string,filled); //构造三角形
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
	public GeometricObject() {   //默认设置color,fill
		// TODO 自动生成的构造函数存根
		setColor("White");
		setFill(true);
	}
	public GeometricObject(String str,boolean f) {  //生成特定的color fill
		setColor(str);
		setFill(f);
	}
	public String getColor() {  //getter color
		return color;
	}
	public void setColor(String color) {  //setter color
		this.color = color; 
	}
	public boolean isFill() {  //getter fill
		return fill;
	}
	public void setFill(boolean fill) {  //setter fill
		this.fill = fill;
	}
}
class Triangle extends GeometricObject{
	private double side1,side2,side3;
	public Triangle() {  //默认构造边长,color,fill
		//默认调用父类默认构造函数
		side1=1.0;
		side2=1.0;
		side3=1.0;
	}
	public Triangle(double a,double b,double c, String s,boolean f) {//生成特定的边长
		super(s,f);  //调用父类构造函数，设定color,fill
		side1=a;
		side2=b;
		side3=c;
	}
	public double getArea() {//计算面积
		double area=0,p=0;
		p=(side1+side2+side3)/2;
		area=Math.sqrt(p*(p-side1)*(p-side2)*(p-side3));
		return area;
	}
	public double getPerimeter() {//计算周长
		return side1+side2+side3;
	}
	public String toString() {//打印信息
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
