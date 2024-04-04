import java.io.*;
import java.math.BigInteger;

public class ElGamal extends PublicKeyCryptosystem {
    private BigInteger primElement = new BigInteger("1");

    public ElGamal() {
        super();
        setPrimElement();
    }
    public ElGamal(BigInteger privateKey) {
        super(privateKey);
        setPrimElement();
    }
    public ElGamal(int privateKey) {
        super(privateKey);
        setPrimElement();
    }
    public ElGamal(BigInteger modulus, BigInteger privateKey, BigInteger primElement) {
        super(modulus, privateKey);
        setPrimElement(primElement);
    }
    public ElGamal(int modulus, int privateKey, int primElement) {
        super(modulus, privateKey);
        setPrimElement(primElement);
    }

    public void setModulus() {
        try {
            modulus = super.genRandPrime();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setPrimElement();
        setPublicKey();
    }
    public void setPrivateKey(BigInteger privateKey) {
        this.privateKey = privateKey;
        setPublicKey();
    }
    public void setPrivateKey(int privateKey) {
        this.privateKey = BigInteger.valueOf(privateKey);
        setPublicKey();
    }
    public void setPublicKey() {
        if (primElement != null && modulus != null) this.publicKey = primElement.modPow(privateKey, modulus);
    }

    public void setPrimElement() {
        primElement = BigInteger.valueOf(2);
        setPublicKey();
    }
    public void setPrimElement(BigInteger primElement) {
        this.primElement = primElement;
        setPublicKey();
    }
    public void setPrimElement(int primElement) {
        this.primElement = BigInteger.valueOf(primElement);
        setPublicKey();
    }

    public BigInteger getPrimElement() {
        return primElement;
    }

    public Pair<BigInteger> encrypt(BigInteger plainText, BigInteger randomKey) {
        BigInteger cipherText1 = primElement.modPow(randomKey, modulus);
        BigInteger cipherText2 = publicKey.modPow(randomKey, modulus);
        cipherText2 = cipherText2.multiply(plainText);
        cipherText2 = cipherText2.mod(modulus);

        Pair<BigInteger> encryptedText = new Pair<BigInteger>(cipherText1, cipherText2);

        return encryptedText;
    }
    public Pair<BigInteger> timedEncrypt(BigInteger plainText, BigInteger randomKey, File output, Character delim) throws IOException {
        int bitLength = plainText.bitLength();

        long startTime = System.nanoTime();
        Pair<BigInteger> encryptedText = encrypt(plainText, randomKey);
        long endTime = System.nanoTime();

        output.createNewFile();
        FileWriter writer = new FileWriter(output.getName(), true);
        PrintWriter printer = new PrintWriter(writer);
        printer.println(bitLength + delim.toString() + (endTime - startTime));
        writer.close();
        printer.close();

        return encryptedText;
    }
    public BigInteger decrypt(Pair<BigInteger> cipherText) {
        BigInteger x = cipherText.getFirst();
        x = x.modPow(privateKey, modulus);
        x = x.modInverse(modulus);
        x = x.multiply(cipherText.getSecond());
        return x.mod(modulus);
    }
    public BigInteger timedDecrypt(Pair<BigInteger> cipherText, File output, Character delim) throws IOException {
        int bitLength1 = cipherText.getFirst().bitLength();
        int bitLength2 = cipherText.getSecond().bitLength();
        double avgBitLength = bitLength1 + bitLength2;
        avgBitLength = avgBitLength / 2;

        long startTime = System.nanoTime();
        BigInteger decryptedText = decrypt(cipherText);
        long endTime = System.nanoTime();

        output.createNewFile();
        FileWriter writer = new FileWriter(output.getName(), true);
        PrintWriter printer = new PrintWriter(writer);
        printer.println(avgBitLength + delim.toString() + (endTime - startTime));
        writer.close();
        printer.close();

        return decryptedText;
    }
}
