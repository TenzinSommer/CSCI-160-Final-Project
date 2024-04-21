import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

import static java.lang.System.out;


public class PublicKeyCryptosystem_UnitTest {
    private static final int maximumScore = 100;
    private static int totalScoreElGamal;
    private static int totalScoreRSA;

    @BeforeClass
    public static void beforeTesting() throws IOException {
        totalScoreElGamal = 0;
        totalScoreRSA = 0;
    }

    @AfterClass
    public static void afterTesting() {
        out.printf("%d out of %d test cases were correctly encrypted and decrypted using ElGamal.\n", totalScoreElGamal, maximumScore);
        out.printf("%d out of %d test cases were correctly encrypted and decrypted using the RSA.\n\n", totalScoreRSA, maximumScore);

    }

    @Test
    public void testElGamal() throws Exception {
        Random randomGen = new Random();
        BigInteger e_plainText = BigInteger.valueOf(2);

        for (int i = 0; i < maximumScore; ++i) {
            ElGamal elGamal = new ElGamal();
            e_plainText.add(BigInteger.ONE);
            int randomKey = randomGen.nextInt();
            Pair<BigInteger> e_cipherText = elGamal.encrypt(e_plainText, BigInteger.valueOf(randomKey));
            BigInteger e_decryptedText = elGamal.decrypt(e_cipherText);

            if (e_plainText.intValue() == e_decryptedText.intValue()) {
                ++totalScoreElGamal;
            }
            e_plainText.add(BigInteger.ONE);
        }
    }

    @Test
    public void testRSA() throws Exception {
        BigInteger r_plainText = BigInteger.valueOf(2);

        for (int i = 0; i < maximumScore; ++i) {
            RSA rsa = new RSA();
            r_plainText.add(BigInteger.ONE);
            BigInteger r_cipherText = rsa.encrypt(r_plainText);
            BigInteger r_decryptedText = rsa.decrypt(r_cipherText);

            if (r_plainText.intValue() == r_decryptedText.intValue()) {
                ++totalScoreRSA;
            }
            r_plainText.add(BigInteger.ONE);
        }
    }
}
