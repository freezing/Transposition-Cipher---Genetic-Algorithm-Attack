package genetic.crossover;

import java.util.Random;

import cipher.CipherKey;

public class CrossoverAlgorithmImpl implements CrossoverAlgorithm {

	private Random r;
	
	public CrossoverAlgorithmImpl() {
		r = new Random();
	}
	
	@Override
	public CipherKey crossover(CipherKey a, CipherKey b,
			double crossoverProbability) {
		int[] permutation = new int[a.getSize()];
		
		// Acquire random segment
		int left = r.nextInt(a.getSize());
		int right = r.nextInt(b.getSize());
		
		// If right is less than left swap them
		if (right < left) {
			int tmp = left;
			left = right;
			right = tmp;
		}
		
		// Mark[i] is true if value i is copied from the CipherKey a,
		// false otherwise
		boolean mark[] = new boolean[permutation.length];
		
		// Copy all from the CipherKey a that belong to the segment [left, right]
		for (int i = left; i <= right; i++) {
			permutation[i] = a.get(i);
			mark[permutation[i]] = true;
		}
		
		// Go through CipherKey b and insert gene value if it is not already in permutation
		int idx = 0;
		for (int i = 0; i < b.getSize(); i++) {
			int gene = b.get(i);
			if (!mark[gene]) {
				idx = fill(gene, permutation, idx, left, right);
			}
		}
		
		// Create and return CipherKey for the crossovered permutation
		return new CipherKey(permutation);
	}

	private int fill(int gene, int[] permutation, int idx, int left, int right) {
		if (idx >= left && idx <= right) {
			idx = right + 1;
		}
		permutation[idx] = gene;
		return idx + 1;
	}
}
