package ch03.ex11;

/**
 *
 * ソートアルゴリズムが気づかれることなくメトリクスに関して不正を行える
 * SortDoubleのセキュリティホールを少なくとも１つみつける。そのセキュリティホールをなくすように修正する
 * ソートアルゴリズムの作成者はmainを書かない
 *
 */
public class SecurityHole extends SortDouble {

	@Override
	protected void doSort() {
		for (int i = 0; i < getDataLength(); i++) {
			for (int j = i + 1; j < getDataLength(); j++) {
				if (compare(i, j) > 0) {
					swap(i, j);
				}
			}
		}

		super.probe(0);
	}

}
