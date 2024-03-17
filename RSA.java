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
        privateKey = privateKey;
        this.privateKey2 = privateKey2;
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

    public BigInteger encrypt(BigInteger plainText) {
        BigInteger cipherText = plainText.modPow(publicKey, modulus);
        return cipherText;
    }
    public BigInteger decrypt(BigInteger cipherText) {
        BigInteger prime1 = privateKey.subtract(BigInteger.ONE);
        BigInteger prime2 = privateKey2.subtract(BigInteger.ONE);
        BigInteger decryptionMod = prime1.multiply(prime2);
        BigInteger decryptionKey = publicKey.modInverse(decryptionMod);
        return cipherText.modPow(decryptionKey, modulus);
    }
}
