package test;
//测试程序Rectangle矩形
public class tset {
	public static void main(String[] args) {
		Rectangle rectangle = new Rectangle(5,50);
		Rectangle rectangle2 = new Rectangle(4.5,45.5);
		System.out.println("rectangle矩形：");
		System.out.println("     宽为："+rectangle.getWidth());
		System.out.println("     高为："+rectangle.getHeight());
		System.out.println("     面积为："+rectangle.getArea());
		System.out.println("     周长为："+rectangle.getPerimeter());
		System.out.println("rectangle2矩形：");
		System.out.println("     宽为："+rectangle2.getWidth());
		System.out.println("     高为："+rectangle2.getHeight());
		System.out.println("     面积为："+rectangle2.getArea());
		System.out.println("     周长为："+rectangle2.getPerimeter());
	}
}
