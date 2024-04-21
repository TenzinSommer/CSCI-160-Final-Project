import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

public class TestPublicKeyCryptosystem {
    public static void main(String[] args) {
        // ElGamal
        System.out.println("ElGamal");
        ElGamal elGamal = new ElGamal();
        BigInteger e_plainText = BigInteger.valueOf(331);
        System.out.println("Plain Text: " + e_plainText);
        BigInteger randomKey = BigInteger.valueOf(197);

        try {
            File output_encrypt = new File("ElGamal_encryptTimes.txt");
            Pair<BigInteger> e_encryptedText = elGamal.timedEncrypt(e_plainText, randomKey, output_encrypt, ',');
            System.out.println("Encrypted Text: " + e_encryptedText);

            File output_decrypt = new File("ElGamal_decryptTimes.txt");
            BigInteger e_decryptedText = elGamal.timedDecrypt(e_encryptedText, output_decrypt, ',');
            System.out.println("Decrypted Text: " + e_decryptedText);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        // RSA
        System.out.println("RSA");
        RSA rsa = new RSA();
        System.out.println("Public Key: " + rsa.getPublicKey());
        BigInteger r_plainText = BigInteger.valueOf(1070777);
        System.out.println("Plaintext: " + r_plainText);

        try {
            File output_encrypt = new File("RSA_encryptTimes.txt");
            BigInteger r_encryptedText = rsa.timedEncrypt(r_plainText, output_encrypt, ',');
            System.out.println("Encrypted Text: " + r_encryptedText);

            File output_decrypt = new File("RSA_decryptTimes.txt");
            BigInteger r_decryptedText = rsa.timedDecrypt(r_encryptedText, output_decrypt, ',');
            System.out.println("Decrypted Text: " + r_decryptedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
