import java.math.*;
public class RSA {
    public static BigInteger genMod(BigInteger prime1, BigInteger prime2) {
        return prime1.multiply(prime2);
    }

    public static BigInteger encrypt(BigInteger plainText, BigInteger publicKey, BigInteger mod) {
        BigInteger cipherText = plainText.modPow(publicKey, mod);
        return cipherText;
    }

    public static BigInteger decrypt(BigInteger cipherText, BigInteger publicKey, BigInteger prime1, BigInteger prime2, BigInteger mod) {
        prime1 = prime1.subtract(BigInteger.ONE);
        prime2 = prime2.subtract(BigInteger.ONE);
        BigInteger decryptionMod = prime1.multiply(prime2);
        BigInteger decryptionKey = publicKey.modInverse(decryptionMod);
        return cipherText.modPow(decryptionKey, mod);
    }

    public static void main(String[] args) {
        BigInteger prime1 = new BigInteger("1223");
        BigInteger prime2 = new BigInteger("1987");

        BigInteger mod = genMod(prime1, prime2);
        System.out.println("Mod:" + mod);

        BigInteger publicKey = new BigInteger("948047");

        BigInteger plainText = new BigInteger("1070777");
        System.out.println("Plain Text: " + plainText);

        BigInteger encryptedText = encrypt(plainText, publicKey, mod);
        System.out.println("Cipher Text: " + encryptedText);

        BigInteger decryptedText = decrypt(encryptedText, publicKey, prime1, prime2, mod);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
