package ch01.ex15;

import java.util.HashMap;
import java.util.Map;

public class PracticeLookup implements MutableLookup {

	private Map<String, Object> mMap = new HashMap<String, Object>();

	public PracticeLookup() {
		mMap = new HashMap<String, Object>();
	}

	/**
	 * test用コンストラクタ
	 * @param key
	 * @param value
	 */
	PracticeLookup(String key, Object value) {
		mMap = new HashMap<String, Object>();
		mMap.put(key, value);
	}

	@Override
	public Object find(String name) {
		return mMap.get(name);
	}

	@Override
	public void add(String key, Object value) {
		mMap.put(key, value);
	}

	@Override
	public void remove(String key) {
		mMap.remove(key);
	}

}
