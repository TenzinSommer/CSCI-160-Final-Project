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
        /* STUB-- not yet implemented!
           Future implementation will involve getting a random value from the prime database
        */

        modulus = BigInteger.valueOf(3);
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
        if (primElement != null) this.publicKey = primElement.modPow(privateKey, modulus);
    }

    public void setPrimElement() {
        /* STUB-- not yet implemented!
           Future implementation will involve getting a random value from the prime database
           that is less than the modulus
        */

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
    public BigInteger decrypt(Pair<BigInteger> cipherText) {
        BigInteger x = cipherText.getFirst();
        x = x.modPow(privateKey, modulus);
        x = x.modInverse(modulus);
        x = x.multiply(cipherText.getSecond());
        return x.mod(modulus);
    }
}
