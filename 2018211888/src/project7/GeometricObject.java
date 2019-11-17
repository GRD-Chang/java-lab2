package project7;

public abstract class GeometricObject {
	private String color;
	private boolean fill;
	public GeometricObject() {  //Ĭ�Ϲ��캯����Ϊcolor fill����ֵ
		// TODO �Զ����ɵĹ��캯�����
		setColor("White");
		setFill(false);
		
	}
	public GeometricObject(String str,boolean f) {    //���ι��캯����color fill���ض�ֵ
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
	public abstract double getArea();  //�������
	public abstract double getPerimeter();  //�����ܳ�
}
