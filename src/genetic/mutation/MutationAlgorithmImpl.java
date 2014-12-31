package genetic.mutation;

import java.util.Random;

import cipher.CipherKey;

public class MutationAlgorithmImpl implements MutationAlgorithm {

	private double mutationProbability;
	private Random r;
	
	public MutationAlgorithmImpl(double mutationProbability) {
		r = new Random();
		this.mutationProbability = mutationProbability;
	}
	
	@Override
	public CipherKey mutate(CipherKey key) {
		for (int i = 0; i < key.getSize(); i++) {
			if (r.nextDouble() <= mutationProbability) {
				int idx = r.nextInt(key.getSize());
				
				int p1 = key.get(i);
				int p2 = key.get(idx);
				
				key.set(i, p2);
				key.set(idx, p1);
			}
		}
		
		return key;
	}

}
