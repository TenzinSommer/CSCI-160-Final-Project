import java.math.*;
public class ElGamal {
    public static BigInteger genPublicKey(BigInteger privateKey, BigInteger mod, BigInteger primElement) {
        return primElement.modPow(privateKey, mod);
    }

    public static Pair<BigInteger> encrypt(BigInteger plainText, BigInteger randomKey, BigInteger publicKey, BigInteger mod, BigInteger primElement) {
        BigInteger cipherText1 = primElement.modPow(randomKey, mod);
        BigInteger cipherText2 = publicKey.modPow(randomKey, mod);
        cipherText2 = cipherText2.multiply(plainText);
        cipherText2 = cipherText2.mod(mod);

        Pair<BigInteger> encryptedText = new Pair<BigInteger>(cipherText1, cipherText2);

        return encryptedText;
    }

    public static BigInteger decrypt(BigInteger privateKey, Pair<BigInteger> cipherText, BigInteger mod) {
        BigInteger x = cipherText.getFirst();
        x = x.modPow(privateKey, mod);
        x = x.modInverse(mod);
        x = x.multiply(cipherText.getSecond());
        return x.mod(mod);
    }

    public static void main(String[] args) {
        BigInteger privateKey = new BigInteger("153");
        BigInteger mod = new BigInteger("467");
        BigInteger primElement = new BigInteger("2");

        BigInteger publicKey = genPublicKey(privateKey, mod, primElement);
        System.out.println("Public Key: " + publicKey);

        BigInteger plainText = new BigInteger("331");
        System.out.println("Plain Text: " + plainText);
        BigInteger randomKey = new BigInteger("197");

        Pair<BigInteger> encryptedText = encrypt(plainText, randomKey, publicKey, mod, primElement);
        System.out.println("Cipher Text: " + encryptedText);
        BigInteger decryptedText = decrypt(privateKey, encryptedText, mod);
        System.out.println("Decrypted text: " + decryptedText.toString());
    }
}
