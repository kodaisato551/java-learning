package ch01.ex15;

public interface MutableLookup extends Lookup {
	void add(String key, Object value);

	void remove(String key);
}
