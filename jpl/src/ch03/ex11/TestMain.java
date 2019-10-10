package ch03.ex11;

public class TestMain {

	public static void main(String[] args) {
		SecurityHole securityHole = new SecurityHole();

		//stackoverflow
		double[] data = { 1, 4, 3 };

		SortMetrics smMetrics = securityHole.sort(data);
		System.out.println(smMetrics);

	}

}
