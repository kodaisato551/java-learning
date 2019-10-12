package ch01.ex08;

/**
 *
 *
 * Pointクラスにメソッドを追加して、引数として渡されたオブジェクトの座標を自分の座標に設定するメソッドを定義しなさい
 * @author Sato Kodai
 *
 */
public class Point {
	public double x, y;

	public void clear() {
		this.x = 0.0;
		this.y = 0.0;
	}

	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void copy(Point other) {
		x = other.x;
		y = other.y;
	}

}
