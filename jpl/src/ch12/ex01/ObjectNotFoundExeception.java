package ch12.ex01;

/**
 * nullを返すよりスローするほうがなぜ好まれるのか？
 * ＞nullを返すとしらずにクライアント側で予期せぬところでヌルぽを吐くよりも
 * ＞明示的に意味の分かる例外をスローするほうが、ユーザフレンドリーだから。
 * @author Sato Kodai
 */
public class ObjectNotFoundExeception extends Exception {

	public ObjectNotFoundExeception(Object target, String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public ObjectNotFoundExeception(Object target, Throwable throwable) {
		this(target, "object [" + target + "] not found", throwable);
	}

	public ObjectNotFoundExeception(Object target) {
		this(target, "object [" + target + "] not found", null);
	}

}
