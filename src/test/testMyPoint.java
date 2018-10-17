package test;

public class testMyPoint {
	public static void main(String[] args) {
		MyPoint myPoint = new MyPoint();
		MyPoint myPoint2 = new MyPoint(3,4);
		System.out.println("到指定点的距离：");
		//指定点  自己输入
		System.out.println("    到原点之间的距离："+myPoint2.distance(0, 0));
	}
}
