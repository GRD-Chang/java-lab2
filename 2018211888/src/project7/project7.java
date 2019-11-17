package project7;

import java.util.Scanner;

public class project7 {

	public static void main(String[] args) {
		GeometricObject[] g=new GeometricObject[5];  //5个GeometricObject类型
		for(int i=0;i<5;i++) {
			g[i]=new Square(); //实际类型指向Square
			if(g[i] instanceof Square) {
				((Square)g[i]).howtoColor();  //向下转换，调用howtoColor
			}
		}
		System.out.println("请输入八边形边长（>0）：");
		double length=0;
		Scanner scanner=new Scanner(System.in);
		try {
			length=scanner.nextDouble();
			if(length<0) {
				System.out.println("输入数据不符合要求，默认输入为0");
				length=0;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("输入数据不符合要求，默认输入为0");
		}
		Octagon octagon=new Octagon(length);   //Octagon变成为5
		Octagon o=null;
		try {
			o = (Octagon) octagon.clone();  //o为octagon的复制
			System.out.println("The Octagan area is:"+octagon.getArea());  //计算面积
			System.out.println("The Octagan perimeter is:"+octagon.getPerimeter());  //计算周长
			System.out.println("Compar with o:"+octagon.compareTo(o));  //比较大小 1,0,-1代表octagon>o,=o,<o
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		}

}


interface Colorable{
	void howtoColor();
}


class Square extends GeometricObject implements Colorable{
	double length,width;
	public Square() {  //默认构造函数
		length=0;
		width=0;
	}	
	public Square(double a,double b) { //含参构造函数
		length=a;
		width=b;
	}
	@Override
	public void howtoColor() {  //实现howtoColor
		System.out.println("Square:填充颜色");
	}
	@Override
	public double getArea() {  //实现父类的抽象函数，计算面积
		// TODO 自动生成的方法存根
		return length*width;
	}

	@Override
	public double getPerimeter() {//实现父类的抽象函数，计算周长
		// TODO 自动生成的方法存根
		return 2*(length+width);
	}
	
}
class Octagon extends GeometricObject implements Comparable<Octagon>,Cloneable{
	private double side;
	public Octagon() {  //默认构造函数
		side=0;
	}
	public Octagon(double a) {  //含参构造函数
		side=a;
	}

	@Override
	public double getArea() { //实现父类的抽象函数，计算面积
		double area=0;
		area=(2+4/Math.sqrt(2))*side*side;
		return area;
	}

	@Override
	public double getPerimeter() {//实现父类的抽象函数，计算周长
		return 8*side;
	}
	
	@Override
	public int compareTo(Octagon o) {  //实现接口，compareTo
		if(side>o.side) {   //1,0,-1代表this>o,=o,<o
			return 1;
		}
		else if (side<o.side) {
			return -1;
		}
		else {
			return 0;
		}
	}
	@Override
	public Object clone() throws CloneNotSupportedException {  //实现接口,clone
		return super.clone();
	}

	public double getSide() {
		return side;
	}

	public void setSide(double side) {
		this.side = side;
	}


	
}
