package project6;

import java.util.Scanner;

public class project6 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		double a = 0,b = 0,c = 0;
		boolean jud=true;
		Scanner scanner=new Scanner(System.in);
		while (jud) {
		System.out.println("���������߱߳���");
		try {
			a=scanner.nextDouble();
			b=scanner.nextDouble();
			c=scanner.nextDouble();
			if(a+b>c&&b+c>a&&a+c>b) {  //�ж����������Ƿ������������
				jud=false;
			}
			else { //�޷����
				System.out.println("�޷�����������");
			}
		} catch (Exception e) {   //���벻Ϊdouble����
			// TODO: handle exception
			System.out.println("���벻����Ҫ������������");
			scanner.nextLine();
		}
		}
		System.out.println("��������ɫ��");
		String string=scanner.next();
		System.out.println("�������Ƿ����");
		boolean filled=false;
		try {
			filled=scanner.nextBoolean();
		} catch (Exception e) {  //���벻Ϊboolen����
			// TODO: handle exception
			System.out.println("�������Ĭ��fill=false");
		}
		Triangle triangle=new Triangle(a,b,c,string,filled); //����������
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
	public GeometricObject() {   //Ĭ������color,fill
		// TODO �Զ����ɵĹ��캯�����
		setColor("White");
		setFill(true);
	}
	public GeometricObject(String str,boolean f) {  //�����ض���color fill
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
	public Triangle() {  //Ĭ�Ϲ���߳�,color,fill
		//Ĭ�ϵ��ø���Ĭ�Ϲ��캯��
		side1=1.0;
		side2=1.0;
		side3=1.0;
	}
	public Triangle(double a,double b,double c, String s,boolean f) {//�����ض��ı߳�
		super(s,f);  //���ø��๹�캯�����趨color,fill
		side1=a;
		side2=b;
		side3=c;
	}
	public double getArea() {//�������
		double area=0,p=0;
		p=(side1+side2+side3)/2;
		area=Math.sqrt(p*(p-side1)*(p-side2)*(p-side3));
		return area;
	}
	public double getPerimeter() {//�����ܳ�
		return side1+side2+side3;
	}
	public String toString() {//��ӡ��Ϣ
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
