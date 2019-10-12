package ch05.ex02;

import java.util.ArrayList;
import java.util.List;

/**
 * 処理履歴を記録するBankAccount
 *
 * １０個の処理を記録する
 * history メソッドでHistoryオブジェクトを返す
 * HistoryオブジェクトはnextメソッドでActionオブジェクト１つ返し、リストの最後ではnullを返す
 *
 * Historyはネストクラスにするべきかどうか？ネストしたクラスにするべきならStaticにするべきか？
 *
 * TODO w:TEST
 *
 */
public class BankAccount {
	private long number;
	private long balance;
	private History history;

	public BankAccount() {
		history = new History(10);
	}

	public class Action {
		private String act;
		private long amount;

		Action(String act, long amount) {
			this.act = act;
			this.amount = amount;
		}

		public String toString() {
			// identify our enclosing account
			return number + ": " + act + " " + amount;
		}
	}

	public static class History {
		private List<Action> actions;
		private int historyCapacity;

		private int index = 0;

		public History(int historyCapacity) {
			actions = new ArrayList<Action>();
			this.historyCapacity = historyCapacity;
		}

		/**
		 *ヒストリーリストにAdd
		 */
		public void add(Action e) {
			if (actions.size() == historyCapacity) {
				actions.remove(0);
			}
			actions.add(e);
		}

		/**
		 *ネクスト:
		 *アクションをとってくることで履歴は削除されます
		 */
		public Action next() {
			if (actions.isEmpty() || index == historyCapacity) {
				return null;
			}
			return actions.get(index++);
		}

	}

	public void deposit(long amount) {
		balance += amount;
		history.add(new Action("deposit", amount));
	}

	public void withdraw(long amount) {
		balance -= amount;
		history.add(new Action("withdraw", amount));
	}

}
