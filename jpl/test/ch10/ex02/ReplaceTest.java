package ch10.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class ReplaceTest {
	@Test
	public void test() {
		String str = "\\\"hoge\"\n\t\r\f\'fuga\'b\b";
		assertThat(Replace.replace(str),
				is("\\\\\\\"hoge\\\"\\n\\t\\r\\f\\'fuga\\'b\\b"));
	}
}
