package project2;


public class project2 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
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
			// TODO 自动生成的 catch 块
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
		// TODO 自动生成的方法存根
		System.out.println("a");
	}

	@Override
	public double getArea() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public double getPerimeter() {
		// TODO 自动生成的方法存根
		return 0;
	}
	
}
class Octagon extends GeometricObject implements Comparable<Octagon>,Cloneable{
	private double side;
	public Octagon() {
		// TODO 自动生成的构造函数存根
	}
	public Octagon(double a) {
		// TODO 自动生成的构造函数存根
		side=a;
	}

	@Override
	public double getArea() {
		// TODO 自动生成的方法存根
		double area=0;
		area=(2+4/Math.sqrt(2))*side*side;
		return area;
	}

	@Override
	public double getPerimeter() {
		// TODO 自动生成的方法存根
		return 8*side;
	}
	
	@Override
	public int compareTo(Octagon o) {
		// TODO 自动生成的方法存根
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
