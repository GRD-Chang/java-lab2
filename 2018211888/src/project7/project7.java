package project7;

import java.util.Scanner;

public class project7 {

	public static void main(String[] args) {
		GeometricObject[] g=new GeometricObject[5];  //5��GeometricObject����
		for(int i=0;i<5;i++) {
			g[i]=new Square(); //ʵ������ָ��Square
			if(g[i] instanceof Square) {
				((Square)g[i]).howtoColor();  //����ת��������howtoColor
			}
		}
		System.out.println("������˱��α߳���>0����");
		double length=0;
		Scanner scanner=new Scanner(System.in);
		try {
			length=scanner.nextDouble();
			if(length<0) {
				System.out.println("�������ݲ�����Ҫ��Ĭ������Ϊ0");
				length=0;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�������ݲ�����Ҫ��Ĭ������Ϊ0");
		}
		Octagon octagon=new Octagon(length);   //Octagon���Ϊ5
		Octagon o=null;
		try {
			o = (Octagon) octagon.clone();  //oΪoctagon�ĸ���
			System.out.println("The Octagan area is:"+octagon.getArea());  //�������
			System.out.println("The Octagan perimeter is:"+octagon.getPerimeter());  //�����ܳ�
			System.out.println("Compar with o:"+octagon.compareTo(o));  //�Ƚϴ�С 1,0,-1����octagon>o,=o,<o
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
	public Square() {  //Ĭ�Ϲ��캯��
		length=0;
		width=0;
	}	
	public Square(double a,double b) { //���ι��캯��
		length=a;
		width=b;
	}
	@Override
	public void howtoColor() {  //ʵ��howtoColor
		System.out.println("Square:�����ɫ");
	}
	@Override
	public double getArea() {  //ʵ�ָ���ĳ��������������
		// TODO �Զ����ɵķ������
		return length*width;
	}

	@Override
	public double getPerimeter() {//ʵ�ָ���ĳ������������ܳ�
		// TODO �Զ����ɵķ������
		return 2*(length+width);
	}
	
}
class Octagon extends GeometricObject implements Comparable<Octagon>,Cloneable{
	private double side;
	public Octagon() {  //Ĭ�Ϲ��캯��
		side=0;
	}
	public Octagon(double a) {  //���ι��캯��
		side=a;
	}

	@Override
	public double getArea() { //ʵ�ָ���ĳ��������������
		double area=0;
		area=(2+4/Math.sqrt(2))*side*side;
		return area;
	}

	@Override
	public double getPerimeter() {//ʵ�ָ���ĳ������������ܳ�
		return 8*side;
	}
	
	@Override
	public int compareTo(Octagon o) {  //ʵ�ֽӿڣ�compareTo
		if(side>o.side) {   //1,0,-1����this>o,=o,<o
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
	public Object clone() throws CloneNotSupportedException {  //ʵ�ֽӿ�,clone
		return super.clone();
	}

	public double getSide() {
		return side;
	}

	public void setSide(double side) {
		this.side = side;
	}


	
}
