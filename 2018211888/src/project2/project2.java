package project2;


public class project2 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		GeometricObject[] g=new GeometricObject[5];
		for(int i=0;i<5;i++) {
			g[i]=new Square();
			if(g[i] instanceof Square) {
				((Square)g[i]).howtoColor();
			}
		}
		Octagon octagon=new Octagon(5);
		Octagon o;
		try {
			o = (Octagon) octagon.clone();
			System.out.println("The Octagan area is:"+octagon.getArea());
			System.out.println("The Octagan perimeter is:"+octagon.getPerimeter());
			System.out.println("Compar with o:"+octagon.compareTo(o));
		} catch (CloneNotSupportedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		}

}
interface Colorable{
	void howtoColor();
}
class Square extends GeometricObject implements Colorable{
	
	@Override
	public void howtoColor() {
		// TODO �Զ����ɵķ������
		System.out.println("a");
	}

	@Override
	public double getArea() {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public double getPerimeter() {
		// TODO �Զ����ɵķ������
		return 0;
	}
	
}
class Octagon extends GeometricObject implements Comparable<Octagon>,Cloneable{
	private double side;
	public Octagon() {
		// TODO �Զ����ɵĹ��캯�����
	}
	public Octagon(double a) {
		// TODO �Զ����ɵĹ��캯�����
		side=a;
	}

	@Override
	public double getArea() {
		// TODO �Զ����ɵķ������
		double area=0;
		area=(2+4/Math.sqrt(2))*side*side;
		return area;
	}

	@Override
	public double getPerimeter() {
		// TODO �Զ����ɵķ������
		return 8*side;
	}
	
	@Override
	public int compareTo(Octagon o) {
		// TODO �Զ����ɵķ������
		if(side>o.side) {
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
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public double getSide() {
		return side;
	}

	public void setSide(double side) {
		this.side = side;
	}


	
}
