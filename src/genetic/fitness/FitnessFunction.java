package genetic.fitness;

import cipher.CipherKey;

public interface FitnessFunction {
	public double calculateFitness(String plainText, CipherKey key);
}
