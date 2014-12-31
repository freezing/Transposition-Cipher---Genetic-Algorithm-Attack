package genetic;

import genetic.crossover.CrossoverAlgorithm;
import genetic.mutation.MutationAlgorithm;
import genetic.selection.SelectionAlgorithm;
import cipher.CipherKey;

public class GeneticAlgorithm {
	
	// Maximum number of iterations per key size
	private int maximumIterations;
	
	// Initial size of the population
	private int initialPoolSize;
	
	// Number of iterations the best solution didn't change after
	// which we consider the best solution so far to be the final solution
	private int maximumConvergeIterations;
	
	// Probability that one individual (chromosome) mutates
	private double mutationProbability;
	
	private SelectionAlgorithm selectionAlgorithm;
	private MutationAlgorithm mutationAlgorithm;
	private CrossoverAlgorithm crossoverAlgorithm;
	
	public GeneticAlgorithm() {
		// TODO: Initialize algorithms
	}
	
	public CipherKey attack(String cipherText) {
		// TODO: Implement attack algorithm
		return null;
	}
}
