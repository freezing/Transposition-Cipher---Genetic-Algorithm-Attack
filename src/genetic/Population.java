package genetic;

import java.util.Collection;

import cipher.CipherKey;

public class Population {
	private Collection<CipherKey> keys;
	
	public Population(Collection<CipherKey> keys) {
		this.keys = keys;
	}
	
	public Collection<CipherKey> getKeys() {
		return keys;
	}

	public int size() {
		return keys.size();
	}
}
