package genetic;

import genetic.crossover.CrossoverAlgorithm;
import genetic.crossover.CrossoverAlgorithmImpl;
import genetic.fitness.DictionaryFrequencyFitnessFunction;
import genetic.fitness.FitnessFunction;
import genetic.mutation.MutationAlgorithm;
import genetic.mutation.MutationAlgorithmImpl;
import genetic.selection.SelectionAlgorithm;
import genetic.selection.TournamentSelection;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import cipher.CipherKey;
import cipher.Decryption;

public class GeneticAlgorithm {
	// If elitism is set then evolution keeps the best individual from the previous generation
    private boolean elitism;

	// Genetic Algorithm tries to find the best CipherKey
	// with the given keySize
	private int keySize;
	
	// Maximum number of iterations per key size
	private int maximumIterations;

	// Initial size of the population
	private int initialPoolSize;

	// Number of iterations the best solution didn't change after
	// which we consider the best solution so far to be the final solution
	private int maximumConvergeIterations;
	
	// Crossover probability
	private double crossoverProbability;

	// Probability that one individual (chromosome) mutates
	private double mutationProbability;

	private SelectionAlgorithm selectionAlgorithm;
	private MutationAlgorithm mutationAlgorithm;
	private CrossoverAlgorithm crossoverAlgorithm;
	private FitnessFunction fitnessFunction;

	public GeneticAlgorithm(int keySize, int maximumIterations, int initialPoolSize,
			int maximumConvergeIterations, double mutationProbability, double crossoverProbability, boolean elitism) {
		this.keySize = keySize;
		this.maximumIterations = maximumIterations;
		this.initialPoolSize = initialPoolSize;
		this.maximumConvergeIterations = maximumConvergeIterations;
		this.mutationProbability = mutationProbability;
		this.crossoverProbability = crossoverProbability;
		this.elitism = elitism;
		
		selectionAlgorithm = new TournamentSelection();
		mutationAlgorithm = new MutationAlgorithmImpl(this.mutationProbability);
		crossoverAlgorithm = new CrossoverAlgorithmImpl();
		fitnessFunction = new DictionaryFrequencyFitnessFunction();
	}

	public CipherKey attack(String cipherText) {
		cipherText = cipherText.toLowerCase();
		
		// Initialize population
		Population population = new Population(getInitialPopulation());
		
		CipherKey fittest = null;
		int convergeIterations = 0;
		
		// Evolve population many times
		for (int i = 0; i < maximumIterations; i++) {
			population = evolvePopulation(population, cipherText);
			// First in the population is the fittest from the last generation
			CipherKey newFittest = population.get(0);
			if (fittest != null && fittest.equals(newFittest)) {
				convergeIterations++;
			} else {
				convergeIterations = 0;
			}
			fittest = newFittest;
			
			// Check convergence
			if (convergeIterations >= maximumConvergeIterations) {
				return fitnessFunction.getFittest(population, cipherText);
			}
		}
		
		// Return the best match from the most evolved generation
		return fitnessFunction.getFittest(population, cipherText);
	}

	private Population evolvePopulation(Population oldGeneration, String cipherText) {
		List<CipherKey> newGeneration = new LinkedList<CipherKey>();
		
		int elitismOffset = 0;
		if (elitism) {
			// Keep the best individual
			CipherKey fittest = fitnessFunction.getFittest(oldGeneration, cipherText);
			Decryption decryption = new Decryption(fittest);
			System.out.println(decryption.decrypt(cipherText) + "   Fitness: " + fitnessFunction.calculateFitness(fittest, cipherText) + "   Key: " + fittest + "  PermutationPos: " + Arrays.toString(fittest.getPermutationPositions()));
			newGeneration.add(fittest);
			elitismOffset = 1;
		}
		
		// Loop over the old generation size and create individuals with crossover
		for (int i = elitismOffset; i < oldGeneration.size(); i++) {
			CipherKey key1 = selectionAlgorithm.select(oldGeneration, cipherText);
			CipherKey key2 = selectionAlgorithm.select(oldGeneration, cipherText);
			CipherKey newIndividual = crossoverAlgorithm.crossover(key1, key2, crossoverProbability);
			newGeneration.add(newIndividual);
		}
		
		// Mutate population
		for (int i = elitismOffset; i < newGeneration.size(); i++) {
			CipherKey key = newGeneration.get(i);
			key = mutationAlgorithm.mutate(key);
		}
		
		// Create and return population
		return new Population(newGeneration);
	}

	private Collection<CipherKey> getInitialPopulation() {
		Collection<CipherKey> keys = new LinkedList<CipherKey>();
		for (int i = 0; i < initialPoolSize; i++) {
			keys.add(CipherKey.generate(keySize));
		}		
		return keys;
	}
}
