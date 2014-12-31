package genetic.selection;

import genetic.Population;
import genetic.fitness.DictionaryFrequencyFitnessFunction;
import genetic.fitness.FitnessFunction;

import java.util.Random;

import cipher.CipherKey;

public class TournamentSelection implements SelectionAlgorithm {
	private static final int DEFAULT_TOURNAMENT_SIZE = 3;
	
	private int tournamentSize;
	private Random r;
	private FitnessFunction fitnessFunction;
	
	public TournamentSelection() {
		this.tournamentSize = DEFAULT_TOURNAMENT_SIZE;
		init();
	}
	
	public TournamentSelection(int tournamentSize) {
		this.tournamentSize = tournamentSize;
		init();
	}
	
	private void init() {
		r = new Random();
		fitnessFunction = new DictionaryFrequencyFitnessFunction();
	}
	
	@Override
	public CipherKey select(Population population, String cipherText) {
		CipherKey best = null;
		double bestFitness = Double.NEGATIVE_INFINITY;
		
		for (int i = 0; i < tournamentSize; i++) {
			int idx = r.nextInt(population.size());
			CipherKey tmp = population.get(idx);
			double tmpFitness = fitnessFunction.calculateFitness(tmp, cipherText);
			if (best == null || tmpFitness > bestFitness) {
				tmpFitness = bestFitness;
				best = tmp;
			}
		}
		return best;
	}

}
