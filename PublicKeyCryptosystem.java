import java.math.BigInteger;
import java.util.Random;

public abstract class PublicKeyCryptosystem {
    protected BigInteger modulus = new BigInteger("1");
    protected BigInteger privateKey = new BigInteger("1");
    protected BigInteger publicKey = new BigInteger("1");

    protected PublicKeyCryptosystem() {
        setModulus();
        setPrivateKey(2);
        setPublicKey();
    }
    protected PublicKeyCryptosystem(BigInteger privateKey) {
        setModulus();
        setPrivateKey(privateKey);
        setPublicKey();
    }
    protected PublicKeyCryptosystem(int privateKey) {
        setModulus();
        setPrivateKey(privateKey);
        setPublicKey();
    }
    protected PublicKeyCryptosystem(BigInteger modulus, BigInteger privateKey) {
        setModulus(modulus);
        setPrivateKey(privateKey);
        setPublicKey();
    }
    protected PublicKeyCryptosystem(int modulus, int privateKey) {
        setModulus(modulus);
        setPrivateKey(privateKey);
        setPublicKey();
    }

    public abstract void setModulus();
    public void setModulus(BigInteger modulus) {
        this.modulus = modulus;
        setPublicKey();
    }
    public void setModulus(int modulus) {
        this.modulus = BigInteger.valueOf(modulus);
        setPublicKey();
    }
    protected abstract void setPrivateKey(BigInteger privateKey);
    protected abstract void setPrivateKey(int privateKey);
    public abstract void setPublicKey();

    public BigInteger getModulus() {
        return modulus;
    }
    public BigInteger getPrivateKey() {
        return privateKey;
    }
    public BigInteger getPublicKey() {
        return publicKey;
    }

    protected BigInteger genRandPrime() throws Exception {
        grabbingRandomPrime primeGenerator = new grabbingRandomPrime();
        return BigInteger.valueOf(primeGenerator.numberGrab("primes_primRoot2.txt", 10000));
    }

    protected BigInteger genRandValue(long min, long max) {
        if (max == min) return BigInteger.ZERO;
        if (max < min) {
            long temp = max;
            max = min;
            min = temp;
        }
        Random randomGenerator = new Random();
        return BigInteger.valueOf(randomGenerator.nextLong(max - min) + min);
    }
}
