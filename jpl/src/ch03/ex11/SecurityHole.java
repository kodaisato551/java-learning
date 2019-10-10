package ch03.ex11;

/**
 *
 * ソートアルゴリズムが気づかれることなくメトリクスに関して不正を行える
 * SortDoubleのセキュリティホールを少なくとも１つみつける。そのセキュリティホールをなくすように修正する
 * ソートアルゴリズムの作成者はmainを書かない
 *
 */
public class SecurityHole extends SortDouble {

	private boolean called;

	@Override
	protected void doSort() {

		if (called) {
			return;
		}

		double[] data = { 1, 4, 3 };
		for (int i = 0; i < getDataLength(); i++) {
			for (int j = i + 1; j < getDataLength(); j++) {
				if (compare(i, j) > 0) {
					swap(i, j);
				}
			}
		}
		called = true;
		super.sort(data);
	}

}
