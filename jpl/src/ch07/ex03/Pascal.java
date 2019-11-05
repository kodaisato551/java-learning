package ch07.ex03;

/**
 * TODO M:test
 * @author Sato Kodai
 *
 */
public class Pascal {

	private final int depth;
	private int[][] pascalTriangle;

	public static void main(String[] args) {
		Pascal pascal = new Pascal(12);
		pascal.printPascalTriangle();
	}

	public Pascal(int depth) {
		this.depth = depth;
		create();
	}

	private void create() {
		pascalTriangle = new int[depth][];
		int left = 0;
		int right = 0;
		for (int i = 0; i < depth; i++) {
			pascalTriangle[i] = new int[i + 1];
			if (i == 0) {
				pascalTriangle[0][0] = 1;
				continue;
			}
			for (int j = 0; j < pascalTriangle[i].length; j++) {
				left = 0;
				right = 0;
				if (j - 1 >= 0) {
					left = pascalTriangle[i - 1][j - 1];
				}
				if (j <= pascalTriangle[i - 1].length - 1) {
					right = pascalTriangle[i - 1][j];
				}
				pascalTriangle[i][j] = left + right;
			}
		}
	}

	public void printPascalTriangle() {
		for (int i = 0; i < pascalTriangle.length; i++) {
			for (int j = 0; j < pascalTriangle[i].length; j++) {
				System.out.print(pascalTriangle[i][j] + " ");
			}
			System.out.println();
		}
	}

}
