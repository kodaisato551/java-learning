package ch06.ex05;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

import ch06.ex04.SignalColor;

public class SignalColorTest {

	/**
	 * ほしいColorオブジェクトがとってこれるかテスト
	 */
	@Test
	public void testGet_blue() {
		assertThat(SignalColor.BLUE.getColor().getName(), is("blue"));
	}

	@Test
	public void testGet_yellow() {
		assertThat(SignalColor.YELLOW.getColor().getName(), is("yellow"));
	}

	@Test
	public void testGet_red() {
		assertThat(SignalColor.RED.getColor().getName(), is("red"));
	}

}
