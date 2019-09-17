package ch01.ex14;

public class TwoTerminalWalkman extends OneTerminalWalkman {

	private Tape tape;
	private boolean isTerminalTwoIsConnected;

	public void playWithTwoTerminal() {
		play(tape, isTerminalTwoIsConnected);
		isTerminalOneIsConnected = true;
	}

}
