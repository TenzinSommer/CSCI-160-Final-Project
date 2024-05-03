import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class RSA extends PublicKeyCryptosystem {
    protected BigInteger privateKey2;

    public RSA() {
        super();
        setPrivateKey();
        setPrivateKey2();
        setModulus();
        setPublicKey();
    }
    public RSA(BigInteger privateKey, BigInteger privateKey2) {
        super(privateKey);
        setPrivateKey2(privateKey2);
        setModulus();
        setPublicKey();
    }
    public RSA(int privateKey, int privateKey2) {
        super(privateKey);
        setPrivateKey2(privateKey2);
        setModulus();
        setPublicKey();
    }
    public RSA(BigInteger privateKey, BigInteger privateKey2, BigInteger publicKey) {
        super(privateKey);
        setPrivateKey2(privateKey2);
        setModulus();
        setPublicKey(publicKey);
    }
    public RSA(int privateKey, int privateKey2, int publicKey) {
        super(privateKey);
        setPrivateKey2(privateKey2);
        setModulus();
        setPublicKey(publicKey);
    }
    protected void setPrivateKeys() {
        setPrivateKey();
        setPrivateKey2();
    }
    protected void setPrivateKeys(BigInteger privateKey, BigInteger privateKey2) {
        setPrivateKey(privateKey);
        setPrivateKey2(privateKey2);
    }
    protected void setPrivateKeys(int privateKey, int privateKey2) {
        setPrivateKey(privateKey);
        setPrivateKey(privateKey2);
    }
    protected void setPrivateKey() {
        try {
            long privateKey_h = super.genRandPrime().getFirst().longValue();
            this.privateKey = new BigInteger(String.valueOf(privateKey_h));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void setPrivateKey2() {
        if (privateKey != null) {
            try {
                Pair<Integer> privateKey2_h = super.genRandPrime();
                if (privateKey2_h.getFirst() == privateKey.longValue()) {
                    privateKey2_h = genRandPrimeNotEqual(privateKey2_h.getSecond());
                }
                this.privateKey2 = new BigInteger(String.valueOf(privateKey2_h.getFirst()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    protected void setPrivateKey2(BigInteger privateKey2) {
        this.privateKey2 = new BigInteger(privateKey2.toString());
    }
    protected void setPrivateKey2(int privateKey2) {
        this.privateKey2 = new BigInteger(String.valueOf(privateKey2));
    }
    protected void setModulus() {
        if(privateKey != null && privateKey2 != null)  {
            this.modulus = new BigInteger(privateKey.multiply(privateKey2).toString());
        }
    }

    protected void setPublicKey() {
        if (super.privateKey != null && this.privateKey2 != null) {
            // Set Public Key to a random prime less than (privateKey-1)(privateKey2-1)
            try {
                BigInteger privateKeyMin1 = BigInteger.valueOf(super.privateKey.longValue()).subtract(BigInteger.ONE);
                BigInteger privateKey2Min1 = BigInteger.valueOf(this.privateKey2.longValue()).subtract(BigInteger.ONE);
                BigInteger privateKeysMultiplied = privateKeyMin1.multiply(privateKey2Min1);

                long privateKeysMultipliedLong = privateKeysMultiplied.longValueExact();

                Pair<Integer> primeLinePair = genRandPrimeLessThan(privateKeysMultipliedLong);

                while (privateKeysMultiplied.gcd(BigInteger.valueOf(primeLinePair.getFirst())).compareTo(BigInteger.ONE) != 0) {
                    primeLinePair = genRandPrimeNotEqual(primeLinePair.getSecond());
                }

                this.publicKey = new BigInteger(String.valueOf(primeLinePair.getFirst()));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    protected void setPublicKey(BigInteger publicKey) {
        this.publicKey = new BigInteger(publicKey.toString());
    }
    protected void setPublicKey(int publicKey) {
        this.publicKey = new BigInteger(String.valueOf(publicKey));
    }
    public BigInteger getPrivateKey2() {
        return privateKey2;
    }
    public BigInteger encrypt(BigInteger plainText) {
        BigInteger cipherText = plainText.modPow(publicKey, modulus);
        return cipherText;
    }
    public BigInteger timedEncrypt(BigInteger plainText, File output, Character delim) throws IOException {
        int bitLength1 = privateKey.bitLength();
        int bitLength2 = privateKey2.bitLength();
        double avgBitLength = (bitLength1 + bitLength2) / 2;

        long startTime = System.nanoTime();
        BigInteger encryptedText = encrypt(plainText);
        long endTime = System.nanoTime();

        output.createNewFile();
        FileWriter writer = new FileWriter(output.getName(), true);
        PrintWriter printer = new PrintWriter(writer);
        printer.println(avgBitLength + delim.toString() + (endTime - startTime));
        writer.close();
        printer.close();

        return encryptedText;
    }
    public BigInteger decrypt(BigInteger cipherText) {
        BigInteger prime1 = privateKey.subtract(BigInteger.ONE);
        BigInteger prime2 = privateKey2.subtract(BigInteger.ONE);
        BigInteger decryptionMod = prime1.multiply(prime2);
        try {
            BigInteger decryptionKey = publicKey.modInverse(decryptionMod);
            return cipherText.modPow(decryptionKey, modulus);
        } catch (ArithmeticException e) {
            System.out.println("Public key: " + publicKey);
            System.out.println("Decryption mod: " + decryptionMod);
            e.printStackTrace();
        }
        return null;
    }
    public BigInteger timedDecrypt(BigInteger cipherText, File output, Character delim) throws IOException {
        int bitLength1 = privateKey.bitLength();
        int bitLength2 = privateKey2.bitLength();
        double avgBitLength = (bitLength1 + bitLength2) / 2;

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
