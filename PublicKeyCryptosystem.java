import java.math.BigInteger;
import java.util.Random;

public abstract class PublicKeyCryptosystem {
    protected BigInteger modulus;
    protected BigInteger privateKey;
    protected BigInteger publicKey;

    protected PublicKeyCryptosystem() {
        setPrivateKey();
    }
    protected PublicKeyCryptosystem(BigInteger privateKey) {
        setPrivateKey(privateKey);
    }
    protected PublicKeyCryptosystem(int privateKey) {
        setPrivateKey(privateKey);
    }
    protected PublicKeyCryptosystem(BigInteger modulus, BigInteger privateKey) {
        setModulus(modulus);
        setPrivateKey(privateKey);
    }
    protected PublicKeyCryptosystem(int modulus, int privateKey) {
        setModulus(modulus);
        setPrivateKey(privateKey);
    }

    protected abstract void setModulus();
    protected void setModulus(BigInteger modulus) {
        this.modulus = new BigInteger(String.valueOf(modulus));
    }
    protected void setModulus(int modulus) {
        this.modulus = BigInteger.valueOf(modulus);
    }
    protected abstract void setPrivateKey();
    protected void setPrivateKey(BigInteger privateKey) {
        this.privateKey = new BigInteger(privateKey.toString());
    }
    protected void setPrivateKey(int privateKey) {
        this.privateKey = new BigInteger(String.valueOf(privateKey));
    }
    protected abstract void setPublicKey();

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
        return BigInteger.valueOf(primeGenerator.numberGrab("primes_primRoot2.txt", 10000).getFirst());
    }
    // USE INSTEAD OF REPEATING WORK IN CLASSES
    /*protected BigInteger genRandPrimeLessThan() {

    }
    protected BigInteger genRandPrimeGreaterThan() {

    }*/

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
