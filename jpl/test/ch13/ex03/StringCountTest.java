package ch13.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class StringCountTest {

	@Test
	public void testDelimitedStrings() {
		String[] result = StringCount.delimitedString("the <test> of <delimited> Strings.", '<', '>');
		String[] expected = { "<test>", "<delimited>" };
		assertThat(result, is(expected));
	}

}
