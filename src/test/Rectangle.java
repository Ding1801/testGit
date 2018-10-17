package test;
//矩形
public class Rectangle {
	public double width = 1;
	public double height = 1;
	//无参构造函数
	public Rectangle() {
		super();
	}
	//有参构造函数
	public Rectangle(double width, double height) {
		super();
		this.width = width;
		this.height = height;
	}
	//get  set 方法
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	//返回矩形的面积
	public double getArea() {
		return width*height;
	}
	//返回矩形的周长
	public double getPerimeter() {
		return 2*(height+width);
	}
	
}
