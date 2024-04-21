import java.io.*;
import java.math.BigInteger;

public class ElGamal extends PublicKeyCryptosystem {
    protected BigInteger primElement;

    public ElGamal() {
        super();
        setModulus();
        setPrimElement();
        setPrivateKey();
        setPublicKey();
    }
    public ElGamal(BigInteger privateKey) {
        super(privateKey);
        setModulus();
        setPrimElement();
        setPublicKey();
    }
    public ElGamal(int privateKey) {
        super(privateKey);
        setModulus();
        setPrimElement();
        setPublicKey();
    }
    public ElGamal(BigInteger modulus, BigInteger privateKey, BigInteger primElement) {
        super(modulus, privateKey);
        setPrimElement(primElement);
        setPublicKey();
    }
    public ElGamal(int modulus, int privateKey, int primElement) {
        super(modulus, privateKey);
        setPrimElement(primElement);
        setPublicKey();
    }

    protected void setModulus() {
        try {
            modulus = BigInteger.valueOf(super.genRandPrime().getFirst());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void setPrivateKey() {
        BigInteger randPrivateKey = new BigInteger(super.genRandValue(2, modulus.longValue()).toString());
        this.privateKey = new BigInteger(randPrivateKey.toString());
    }
    protected void setPrivateKey(BigInteger privateKey) {
        this.privateKey = new BigInteger(privateKey.toString());
    }
    protected void setPrivateKey(int privateKey) {
        this.privateKey = new BigInteger(String.valueOf(privateKey));
    }
    protected void setPublicKey() {
        if (primElement != null && modulus != null && privateKey != null) {
            this.publicKey = new BigInteger(primElement.modPow(privateKey, modulus).toString());
        }
    }

    protected void setPrimElement() {
        primElement = new BigInteger("2");
    }
    protected void setPrimElement(BigInteger primElement) {
        this.primElement = new BigInteger(primElement.toString());
    }
    protected void setPrimElement(int primElement) {
        this.primElement = new BigInteger(String.valueOf(primElement));
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
        int bitLength = privateKey.bitLength();

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
        int bitLength = privateKey.bitLength();

        long startTime = System.nanoTime();
        BigInteger decryptedText = decrypt(cipherText);
        long endTime = System.nanoTime();

        output.createNewFile();
        FileWriter writer = new FileWriter(output.getName(), true);
        PrintWriter printer = new PrintWriter(writer);
        printer.println(bitLength + delim.toString() + (endTime - startTime));
        writer.close();
        printer.close();

        return decryptedText;
    }
}
