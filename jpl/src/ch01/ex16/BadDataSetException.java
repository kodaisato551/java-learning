package ch01.ex16;

import java.io.IOException;

public class BadDataSetException extends Exception {
	private String setName;

	public BadDataSetException(String name, IOException cause) {
		super(cause);
		setName = name;
	}

	public String getSetName() {
		return setName;
	}

}
