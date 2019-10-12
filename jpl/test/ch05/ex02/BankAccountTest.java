package ch05.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch05.ex02.BankAccount.Action;
import ch05.ex02.BankAccount.History;

public class BankAccountTest {

	BankAccount target;
	BankAccount.History targetHistory;

	@Before
	public void setup() {
		target = new BankAccount();
		targetHistory = new History(10);
	}

	@Test
	public void testHistoryNextNull() {
		assertNull(targetHistory.next());
	}

	@Test
	public void testHistoryAdd() {
		Action action = target.new Action("a", 10);
		targetHistory.add(action);
		assertThat(targetHistory.next(), is(action));
	}

}
