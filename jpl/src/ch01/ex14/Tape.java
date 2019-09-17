package ch01.ex14;

/**
 * Tapeのリソースを管理するクラス
 *
 * @author Sato Kodai
 *
 */
public class Tape {

	private String tapeName;
	private int tapeNameResourceID;
	private boolean isRemoved;

	public Tape(String name, int id) {
		tapeName = name;
		tapeNameResourceID = id;
	}

	public String getTapeName() {
		return tapeName;
	}

	public void setTapeName(String tapeName) {
		this.tapeName = tapeName;
	}

	public int getTapeNameResourceID() {
		return tapeNameResourceID;
	}

	public void setTapeNameResourceID(int id) {
		tapeNameResourceID = id;
	}

	public boolean isRemoved() {
		return isRemoved;
	}

	public void setRemoved(boolean isRemoved) {
		this.isRemoved = isRemoved;
	}

}
