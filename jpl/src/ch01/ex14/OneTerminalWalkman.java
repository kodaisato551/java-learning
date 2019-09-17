package ch01.ex14;

import java.util.Objects;

/**
 * 一端子用Walkman
 *
 *端子につながっていないと音が出ない仕様
 * @author Sato Kodai
 *
 */
public class OneTerminalWalkman {

	private Tape tape = null;
	protected boolean isTerminalOneIsConnected;

	public void playWithOneTerminal() {
		//tapeを聞く
		play(tape, isTerminalOneIsConnected);
		isTerminalOneIsConnected = true;
	}

	/**
	 * Tapeを入力としTapeの内容を再生するメソッド.
	 * TODO本来だったらNullpointerExceptionではなく気の利いたExceptionを吐いたほうがいい
	 * @param tape
	 * @param isConnected
	 * @return
	 */
	protected boolean play(Tape tape, boolean isConnected) {
		Objects.requireNonNull(tape);
		if (isConnected || tape.isRemoved()) {
			return false;
		}
		/*
		 * ここでplayの処理をします
		 */
		return true;
	}

	/**
	 * 端子1が接続されていればTrueを返す
	 * @return
	 */
	public boolean isTerminalOneIsConnected() {
		return isTerminalOneIsConnected;
	}

	/**
	 * tapeをセットする
	 */
	public void setTape(String tapeName, int id) {
		tape = new Tape(tapeName, id);
	}

	/**
	 * tapeをはずす.
	 * tapeがそもそもsetされてない場合にNullpointerExceptionが発生します
	 * @throws NullpointerException
	 */
	public void removeTape() throws NullPointerException {
		Objects.requireNonNull(tape).setRemoved(true);
	}

}
