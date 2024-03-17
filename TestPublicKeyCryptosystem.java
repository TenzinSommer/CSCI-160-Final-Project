import java.math.BigInteger;

public class TestPublicKeyCryptosystem {
    public static void main(String[] args) {
        // ElGamal
        System.out.println("ElGamal");
        ElGamal elGamal = new ElGamal(467, 153, 2);
        BigInteger e_plainText = BigInteger.valueOf(331);
        System.out.println("Plain Text: " + e_plainText);
        BigInteger randomKey = BigInteger.valueOf(197);

        Pair<BigInteger> e_encryptedText = elGamal.encrypt(e_plainText, randomKey);
        System.out.println("Encrypted Text: " + e_encryptedText);

        BigInteger e_decryptedText = elGamal.decrypt(e_encryptedText);
        System.out.println("Decrypted Text: " + e_decryptedText);

        System.out.println();

        // RSA
        System.out.println("RSA");
        RSA rsa = new RSA(1223, 1987);
        rsa.setPublicKey(948047);
        BigInteger r_plainText = BigInteger.valueOf(1070777);
        System.out.println("Plaintext: " + r_plainText);

        BigInteger r_encryptedText = rsa.encrypt(r_plainText);
        System.out.println("Encrypted Text: " + r_encryptedText);

        BigInteger r_decryptedText = rsa.decrypt(r_encryptedText);
        System.out.println("Decrypted Text: " + r_decryptedText);
    }
}
