package ch03.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

public class YTest {
	Y target;

	@Before
	public void setup() {
		target = new Y();
	}

	/**
	 * コンストラクタ実行時にfullmaskが変更されているかどうか
	 */
	@Test
	public void testY() {
		assertThat(target.fullMask, is(0xffff));
	}
}
