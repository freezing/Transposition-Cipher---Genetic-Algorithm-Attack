package genetic;

import org.junit.Assert;
import org.junit.Test;

import cipher.CipherKey;
import cipher.Decryption;
import cipher.Encryption;

public class GeneticAlgorithmTest {

	private static final int maximumIterations = 1000;
	private static final int initialPoolSize = 500;
	private static final int maximumConvergeIterations = 50;
	private static final double mutationProbability = 0.35;
	private static final double crossoverProbability = 0.5;
	private static final boolean elitism = true;
	
	@Test
	public void test() {
		CipherKey key = new CipherKey(new int[]{1, 5, 4, 3, 2, 0, 6});
		String plainText = "This baby likes crying a lot";
		Encryption encryption = new Encryption(key);
		String cipherText = encryption.encrypt(plainText);
		
		GeneticAlgorithm ga = new GeneticAlgorithm(
				key.getSize(),
				maximumIterations,
				initialPoolSize,
				maximumConvergeIterations,
				mutationProbability,
				crossoverProbability,
				elitism);
		
		CipherKey foundKey = ga.attack(cipherText);
		Decryption decryption = new Decryption(foundKey);
		String decryptedText = decryption.decrypt(cipherText);
		
		Assert.assertTrue("Expected: \"" + plainText + "\" but got \"" + decryptedText + "\"",
				decryptedText.toLowerCase().startsWith(plainText.toLowerCase()));
	}
	
	@Test
	public void test2() {
		CipherKey key = new CipherKey(new int[]{1, 5, 4, 3, 2, 0, 6});
		String plainText = "Jimmy Wales and Larry Sanger launched Wikipedia on January 15, 2001. Sanger[10] coined its name,[11] a portmanteau of wiki (from the Hawaiian word for \"quick\")[12] and encyclopedia. Although Wikipedia's content was initially only in English, it quickly became multilingual, through the launch of versions in different languages. All versions of Wikipedia are similar, but differences exist in content and in editing practices. The English Wikipedia is now one of more than 200 Wikipedias and is the largest with over 4.6 million articles. As of February 2014, it had 18 billion page views and nearly 500 million unique visitors each month.[13] Wikipedia has more than 22 million accounts, out of which there were over 73,000 active editors globally as of May 2014.[2]";
		Encryption encryption = new Encryption(key);
		String cipherText = encryption.encrypt(plainText);
		
		GeneticAlgorithm ga = new GeneticAlgorithm(
				key.getSize(),
				maximumIterations,
				initialPoolSize,
				maximumConvergeIterations,
				mutationProbability,
				crossoverProbability,
				elitism);
		
		CipherKey foundKey = ga.attack(cipherText);
		Decryption decryption = new Decryption(foundKey);
		String decryptedText = decryption.decrypt(cipherText);
		Assert.assertTrue("Expected: \"" + plainText + "\" but got \"" + decryptedText + "\"",
				decryptedText.toLowerCase().startsWith(plainText.toLowerCase()));
	}
	
	@Test
	public void test3() {
		CipherKey key = new CipherKey(new int[]{1, 5, 4, 3, 2, 0, 6, 8, 15, 10, 9, 11, 13, 7, 14, 12});
		System.out.println(key);
		String plainText = "Jimmy Wales and Larry Sanger launched Wikipedia on January 15, 2001. Sanger[10] coined its name,[11] a portmanteau of wiki (from the Hawaiian word for \"quick\")[12] and encyclopedia. Although Wikipedia's content was initially only in English, it quickly became multilingual, through the launch of versions in different languages. All versions of Wikipedia are similar, but differences exist in content and in editing practices. The English Wikipedia is now one of more than 200 Wikipedias and is the largest with over 4.6 million articles. As of February 2014, it had 18 billion page views and nearly 500 million unique visitors each month.[13] Wikipedia has more than 22 million accounts, out of which there were over 73,000 active editors globally as of May 2014.[2]";
		Encryption encryption = new Encryption(key);
		String cipherText = encryption.encrypt(plainText);
		System.out.println(cipherText);
		
		GeneticAlgorithm ga = new GeneticAlgorithm(
				key.getSize(),
				maximumIterations,
				initialPoolSize,
				maximumConvergeIterations,
				mutationProbability,
				crossoverProbability,
				elitism);
		
		CipherKey foundKey = ga.attack(cipherText);
		Decryption decryption = new Decryption(foundKey);
		String decryptedText = decryption.decrypt(cipherText);
		System.out.println(decryptedText);
		Assert.assertTrue("Expected: \"" + plainText + "\" but got \"" + decryptedText + "\"",
				decryptedText.toLowerCase().startsWith(plainText.toLowerCase()));
	}
}
