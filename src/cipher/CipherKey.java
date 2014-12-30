package cipher;

import java.util.Random;

public class CipherKey {
	static Random r;
	
	int[] permutation;
	int[] permutationPositions;
	
	public CipherKey(int[] permutation) {
		setPermutation(permutation);
	}
	
	public void setPermutation(int[] permutation) {
		this.permutation = permutation;
		this.permutationPositions = new int[permutation.length];
		
		for (int i = 0; i < permutation.length; i++) {
			permutationPositions[ permutation[i] ] = i;
		}
	}
	
	public int[] getPermutation() {
		return permutation;
	}
	
	public int[] getPermutationPositions() {
		return permutationPositions;
	}
	
	public static CipherKey generate(int length) {
		if (r == null) {
			r = new Random();
		}
		
		int[] permutation = new int[length];
		for (int i = 0; i < length; i++) {
			permutation[i] = i;
		}
		
		// Swap random indices
		for (int i = 0; i < length; i++) {
			int idx = r.nextInt(length);
			int p = permutation[i];
			permutation[i] = permutation[idx];
			permutation[idx] = p;
		}
		
		return new CipherKey(permutation);
	}

	public int getSize() {
		return permutation.length;
	}
}
