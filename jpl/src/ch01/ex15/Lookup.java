package ch01.ex15;

public interface Lookup {

	/**
	 * nameと関連付けた値を返す
	 * そのような値がなければnullを返す
	 * @param name
	 * @return
	 */
	Object find(String name);

}
