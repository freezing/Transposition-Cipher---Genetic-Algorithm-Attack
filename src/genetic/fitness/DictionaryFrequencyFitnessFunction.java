package genetic.fitness;

import genetic.Population;

import java.util.List;

import utility.StringUtils;
import cipher.CipherKey;
import cipher.Decryption;
import dictionary.Dictionary;

public class DictionaryFrequencyFitnessFunction implements FitnessFunction {

	private Dictionary dictionary;
	
	public DictionaryFrequencyFitnessFunction() {
		dictionary = Dictionary.getInstance();
	}
	
	@Override
	public double calculateFitness(CipherKey key, String cipherText) {
		// Decrypt cipher text using the given cipher key
		Decryption decryption = new Decryption(key);
		String plainText = decryption.decrypt(cipherText);
		
		// Calculate fitness
		double fitness = 0.0;
		
		// Extract n-grams from plain text of each length
		for (int ngramLength = 2; ngramLength <= 5; ngramLength++) {
			List<String> ngrams = StringUtils.extractNGrams(plainText, ngramLength);
			for (String ngram : ngrams) {
				// For each ngram calculate log probability for it to be in the dictionary
				// and add the value to the fitness
				double logProbability = dictionary.getLogProbability(ngram, ngramLength);
				fitness += logProbability;
			}
		}
		
		return fitness;
	}

	@Override
	public CipherKey getFittest(Population population, String cipherText) {
		CipherKey fittest = null;
		double bestFitness = Double.NEGATIVE_INFINITY;
		
		for (CipherKey key : population.getKeys()) {
			double fitness = calculateFitness(key, cipherText);
			if (fittest == null || fitness > bestFitness) {
				fittest = key;
				bestFitness = fitness;
			}
		}		
		return fittest;
	}

}
