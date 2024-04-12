import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class RSA extends PublicKeyCryptosystem {
    private BigInteger privateKey2;

    public RSA() {
        super();
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
    /*public RSA(BigInteger modulus, BigInteger privateKey, BigInteger privateKey2) {
        super(modulus, privateKey);
        setPrivateKey2(privateKey2);
    }
    public RSA(int modulus, int privateKey, int privateKey2) {
        super(modulus, privateKey);
        setPrivateKey2(privateKey2);
    }*/
    public void setPrivateKeys() {
        try {
            privateKey = super.genRandPrime();
            this.privateKey2 = super.genRandPrime();
            while (privateKey == privateKey2) {
                this.privateKey2 = super.genRandPrime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setPrivateKeys(BigInteger privateKey, BigInteger privateKey2) {
        setPrivateKey(privateKey);
        setPrivateKey2(privateKey2);
    }
    public void setPrivateKeys(int privateKey, int privateKey2) {
        setPrivateKey(privateKey);
        setPrivateKey(privateKey2);
    }
    protected void setPrivateKey() {
        // NEED TO MAKE SURE IT IS AT LEAST 5 so that public key can be smaller
        try {
            long privateKey_h = super.genRandPrime().longValue();
            // CHANGE IF BAD VAL
            this.privateKey = new BigInteger(String.valueOf(privateKey_h));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void setPrivateKey2() {
        // MAKE SURE NOT EQUAL TO PRIVATE KEY
        try {
            while (setPrivateKey() == setPrivateKey2()) {
                long privateKey2_h = super.genRandPrime().longValue();
                this.privateKey2 = new BigInteger(String.valueOf(privateKey2_h));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void setPrivateKey2(BigInteger privateKey) {
        this.privateKey2 = new BigInteger(privateKey.toString());
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
            grabbingRandomPrime primeGrabber = new grabbingRandomPrime();
            try {
                Pair<Integer> primeLinePair = primeGrabber.numberGrab("primes_primRoot2.txt", 10000);
                long publicKey_h = primeLinePair.getFirst();
                int publicKeyLine = primeLinePair.getSecond();
                BigInteger privatekeyMin1 = BigInteger.valueOf(super.privateKey.longValue()).subtract(BigInteger.ONE);
                BigInteger privateKey2Min1 = BigInteger.valueOf(this.privateKey2.longValue()).subtract(BigInteger.ONE);
                BigInteger privateKeysMultiplied = privatekeyMin1.multiply(privateKey2Min1);

                long privateKeysMultipliedLong = privateKeysMultiplied.longValueExact();

                while (publicKey_h >= privateKeysMultipliedLong) {
                    primeLinePair = primeGrabber.numberGrab("primes_primRoot2.txt", 10000, publicKeyLine);
                    publicKey_h = primeLinePair.getFirst();
                    publicKeyLine = primeLinePair.getSecond();
                }

                this.publicKey = new BigInteger(String.valueOf(publicKey_h));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /*public void setPublicKey(BigInteger publicKey) {
        this.publicKey = publicKey;
    }
    public void setPublicKey(int publicKey) {
        this.publicKey = BigInteger.valueOf(publicKey);
    }*/
    public BigInteger getPrivateKey2() {
        return privateKey2;
    }
    public BigInteger encrypt(BigInteger plainText) {
        BigInteger cipherText = plainText.modPow(publicKey, modulus);
        return cipherText;
    }
    public BigInteger timedEncrypt(BigInteger plainText, File output, Character delim) throws IOException {
        int bitLength = plainText.bitLength();

        long startTime = System.nanoTime();
        BigInteger encryptedText = encrypt(plainText);
        long endTime = System.nanoTime();

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
        int bitLength = cipherText.bitLength();

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
