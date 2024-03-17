import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class RSA extends PublicKeyCryptosystem {
    private BigInteger privateKey2 = new BigInteger("1");

    public RSA() {
        super();
        setPrivateKey2(5);
    }
    public RSA(BigInteger privateKey, BigInteger privateKey2) {
        super(privateKey);
        setPrivateKey2(privateKey2);
    }
    public RSA(int privateKey, int privateKey2) {
        super(privateKey);
        setPrivateKey2(privateKey2);
    }
    public RSA(BigInteger modulus, BigInteger privateKey, BigInteger privateKey2) {
        super(modulus, privateKey);
        setPrivateKey2(privateKey2);
    }
    public RSA(int modulus, int privateKey, int privateKey2) {
        super(modulus, privateKey);
        setPrivateKey2(privateKey2);
    }

    public void setPrivateKeys() {
        /* STUB-- not yet implemented!
           Future implementation will involve getting random values from the prime database
        */

        privateKey = BigInteger.valueOf(3);
        privateKey2 = BigInteger.valueOf(5);
    }
    public void setPrivateKeys(BigInteger privateKey, BigInteger privateKey2) {
        super.privateKey = privateKey;
        this.privateKey2 = privateKey2;
    }
    public void setPrivateKeys(int privateKey, int privateKey2) {
        super.privateKey = BigInteger.valueOf(privateKey);
        this.privateKey2 = BigInteger.valueOf(privateKey2);
    }
    public void setPrivateKey(BigInteger privateKey) {
        super.privateKey = privateKey;
        setModulus();
    }
    public void setPrivateKey(int privateKey) {
        super.privateKey = BigInteger.valueOf(privateKey);
        setModulus();
    }
    public void setPrivateKey2(BigInteger privateKey) {
        this.privateKey2 = privateKey;
        setModulus();
    }
    public void setPrivateKey2(int privateKey) {
        this.privateKey2 = BigInteger.valueOf(privateKey);
        setModulus();
    }
    public void setModulus() {
        if(privateKey != null && privateKey2 != null) this.modulus = privateKey.multiply(privateKey2);
    }

    public void setPublicKey() {
        /* STUB-- not yet implemented!
           Future implementation will involve getting random values from the prime database
        */

        publicKey = BigInteger.valueOf(7);
    }
    public void setPublicKey(BigInteger publicKey) {
        this.publicKey = publicKey;
    }
    public void setPublicKey(int publicKey) {
        this.publicKey = BigInteger.valueOf(publicKey);
    }

    public BigInteger getPrivateKey2() {
        return privateKey2;
    }

    public BigInteger encrypt(BigInteger plainText) {
        BigInteger cipherText = plainText.modPow(publicKey, modulus);
        return cipherText;
    }
    public BigInteger timedEncrypt(BigInteger plainText, File output, Character delim) throws IOException {
        long startTime = System.nanoTime();
        BigInteger encryptedText = encrypt(plainText);
        long endTime = System.nanoTime();

        int bitLength = encryptedText.bitLength();

        output.createNewFile();
        FileWriter writer = new FileWriter(output.getName(), true);
        PrintWriter printer = new PrintWriter(writer);
        printer.println(bitLength + delim.toString() + (endTime - startTime));
        writer.close();
        printer.close();

        return encryptedText;
    }
    public BigInteger decrypt(BigInteger cipherText) {
        BigInteger prime1 = privateKey.subtract(BigInteger.ONE);
        BigInteger prime2 = privateKey2.subtract(BigInteger.ONE);
        BigInteger decryptionMod = prime1.multiply(prime2);
        BigInteger decryptionKey = publicKey.modInverse(decryptionMod);
        return cipherText.modPow(decryptionKey, modulus);
    }
    public BigInteger timedDecrypt(BigInteger cipherText, File output, Character delim) throws IOException {
        long startTime = System.nanoTime();
        BigInteger decryptedText = decrypt(cipherText);
        long endTime = System.nanoTime();

        int bitLength = decryptedText.bitLength();

        output.createNewFile();
        FileWriter writer = new FileWriter(output.getName(), true);
        PrintWriter printer = new PrintWriter(writer);
        printer.println(bitLength + delim.toString() + (endTime - startTime));
        writer.close();
        printer.close();

        return decryptedText;
    }
}
