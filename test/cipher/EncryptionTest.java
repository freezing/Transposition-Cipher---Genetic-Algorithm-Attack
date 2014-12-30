package cipher;

import org.junit.Assert;
import org.junit.Test;

public class EncryptionTest {

	@Test
	public void test() {
		// Input
		String plainText = "Ovo je neki plaintext koji ce biti enkriptovan! Test";
		int[] permutationKey = {5, 3, 7, 1, 2, 12, 0, 4, 9, 6, 8, 11, 10};
		
		// Output
		String expected = " XTN N OJTBVVACPNTI!OL IKKETOIETIONEE   PIRT JKSEEIA";
		
		// Processing
		CipherKey key = new CipherKey(permutationKey);
		Encryption encryption = new Encryption(key);
		String actualCipherText = encryption.encrypt(plainText);
		
		// Verification
		Assert.assertEquals("Cipher text is not as expected!", expected, actualCipherText);
	}

}
