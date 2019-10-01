package ch03.ex07;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class ColorAttrTest {

	@Test
	public void testEquals() {
		ColorAttr target = new ColorAttr("red");
		ColorAttr target2 = new ColorAttr("red");
		assertThat(target.equals(target2), is(true));
	}

}
