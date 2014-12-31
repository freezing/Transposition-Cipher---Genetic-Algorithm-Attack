package cipher;

import org.junit.Assert;
import org.junit.Test;

public class DecryptionTest {

	@Test
	public void test() {
		// Input
		String cipherText = " XTN N OJTBVVACPNTI!OL IKKETOIETIONEE   PIRT JKSEEIA".toLowerCase();
		int[] permutationKey = {5, 3, 7, 1, 2, 12, 0, 4, 9, 6, 8, 11, 10};
		
		// Output
		String expectedPlainText = "OVO JE NEKI PLAINTEXT KOJI CE BITI ENKRIPTOVAN! TEST".toLowerCase();
		
		// Processing
		CipherKey key = new CipherKey(permutationKey);
		Decryption decryption = new Decryption(key);
		String actualPlainText = decryption.decrypt(cipherText);
		
		// Verification
		Assert.assertEquals("Plain text is not as expected!", expectedPlainText, actualPlainText);
	}

	@Test
	public void testDecryptEncrypt() {
		// Input
		String plainText = "OVO JE NEKI ENCRYPTION TEXT. VIDECEMO!!!".toLowerCase();
		CipherKey key = CipherKey.generate(10);
		
		// Expected output
		String expected = plainText;
		
		// Processing
		Decryption decryption = new Decryption(key);
		Encryption encryption = new Encryption(key);
		String actual = encryption.encrypt(decryption.decrypt(plainText));
		
		// Verification
		Assert.assertEquals("Plain text is not as expected", expected, actual);
	}
}
