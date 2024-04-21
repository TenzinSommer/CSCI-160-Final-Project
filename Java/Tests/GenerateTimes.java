import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class GenerateTimes {
    static final int NUM_DATA_POINTS = 1000;
    public static void main(String[] args) {

        // ElGamal
        Random randomGen = new Random();
        for (int i = 0; i < NUM_DATA_POINTS; ++i) {
            ElGamal elGamal = new ElGamal();
            BigInteger e_plainText = BigInteger.valueOf(i + 3);
            int randomKey = randomGen.nextInt();

            try {
                File output_encrypt = new File("ElGamal_encryptTimes.txt");
                Pair<BigInteger> e_encryptedText = elGamal.timedEncrypt(e_plainText, BigInteger.valueOf(randomKey), output_encrypt, ',');

                File output_decrypt = new File("ElGamal_decryptTimes.txt");
                BigInteger e_decryptedText = elGamal.timedDecrypt(e_encryptedText, output_decrypt, ',');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // RSA
        for (int i = 0; i < NUM_DATA_POINTS; ++i) {
            RSA rsa = new RSA();
            BigInteger r_plainText = BigInteger.valueOf(i + 3);

            try {
                File output_encrypt = new File("RSA_encryptTimes.txt");
                BigInteger r_encryptedText = rsa.timedEncrypt(r_plainText, output_encrypt, ',');

                File output_decrypt = new File("RSA_decryptTimes.txt");
                BigInteger r_decryptedText = rsa.timedDecrypt(r_encryptedText, output_decrypt, ',');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
