package ch11.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class ColorAttrTest {

	@Test
	public void testEqualsToString() {
		Attr<String> target = new Attr<>("red");
		Attr<String> target2 = new Attr<>("red");
		assertThat(target.toString(), is(target2.toString()));
	}

}
