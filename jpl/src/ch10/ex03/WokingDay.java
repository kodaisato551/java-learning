package ch10.ex03;

/**
 * 曜日のEnumをうけとる
 * Switchのほうがわかりやすい
 */
public class WokingDay {

	public boolean isWorkingDaySwitch(Week week) {
		switch (week) {
		case SATURDAY:
		case SUNDAY:
			return false;

		default:
			return true;
		}
	}

	public boolean isWorkingDayIfElse(Week week) {
		if (week.equals(Week.SATURDAY) || week.equals(Week.SUNDAY)) {
			return false;
		} else {
			return true;
		}
	}
}
