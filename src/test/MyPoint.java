package test;

public class MyPoint {
	public double x;
	public double y;
	//无参构造函数
	public MyPoint() {
		super();
		this.x=0;
		this.y=0;
	}
	//有参构造函数
	public MyPoint(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	//get set 方法
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	//返回原点之间的距离
	public double distance() {
		return Math.sqrt(this.getX()*this.getY()+this.getY()*this.getY());
	}
	//返回到指定点之间的距离
	public double distance(double x ,double y) {
		double distance = Math.sqrt((x-this.x)*(x-this.x)+(y-this.y)*(y-this.y));
		return distance;
	}
}
