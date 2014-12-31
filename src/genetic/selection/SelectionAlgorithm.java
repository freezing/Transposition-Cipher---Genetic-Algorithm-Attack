package genetic.selection;

import java.util.Collection;

import cipher.CipherKey;

public interface SelectionAlgorithm {
	public CipherKey select(Collection<CipherKey> keys);
}
