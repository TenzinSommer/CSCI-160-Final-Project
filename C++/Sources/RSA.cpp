#include "RSA.h"

#include<iostream>

#include <cmath>
#include <chrono>
#include <ostream>

void RSA::setPrivateKeys() {
    setPrivateKey();
    setPrivateKey2();
}
void RSA::setPrivateKeys(long long privateKey, long long privateKey2) {
    this->privateKey = privateKey;
    setPrivateKey2(privateKey2);
}
void RSA::setPrivateKeys(int privateKey, int privateKey2) {
    this->privateKey = privateKey;
    setPrivateKey2(privateKey2);
}
void RSA::setPrivateKey() {
    privateKey = genRandPrime().getFirst();
}
void RSA::setPrivateKey2() {
    if (privateKey != 0) {
        Pair<int> privateKey2_h = genRandPrime();
        if (privateKey2_h.getFirst() == privateKey) {
            privateKey2_h = genRandPrimeNotEqual(privateKey2_h.getSecond());
        }
        privateKey2 = privateKey2_h.getFirst();
    }
}
void RSA::setPrivateKey2(long long privateKey2) {
    this->privateKey2 = privateKey2;
}
void RSA::setPrivateKey2(int privateKey2) {
    this->privateKey2 = privateKey2;
}
void RSA::setModulus() {
    if (privateKey != 0 && privateKey2 != 0) {
        modulus = privateKey * privateKey2;
    }
}

void RSA::setPublicKey() {
    if (privateKey != 0 && privateKey2 != 0) {
        long long privateKeyMin1 = privateKey - 1;
        long long privateKey2Min1 = privateKey2 - 1;
        long long privateKeysMultiplied = privateKeyMin1 * privateKey2Min1;

        Pair<int> primeLinePair = genRandPrimeLessThan(privateKeysMultiplied);

        while (gcd(primeLinePair.getFirst(), privateKeysMultiplied) != 1) {
            std::cout << primeLinePair.getFirst() << " "  << privateKeysMultiplied << " " << gcd(primeLinePair.getFirst(), privateKeysMultiplied) << std::endl;
            primeLinePair = genRandPrimeNotEqual(primeLinePair.getSecond());
        }

        this->publicKey = primeLinePair.getFirst();
    }
}
void RSA::setPublicKey(long long publicKey) {
    this->publicKey = publicKey;
}
void RSA::setPublicKey(int publicKey) {
    this->publicKey = publicKey;
}

RSA::RSA() : PublicKeyCryptosystem() {
    setPrivateKey();
    setPrivateKey2();
    setModulus();
    setPublicKey();
}
RSA::RSA(long long privateKey, long long privateKey2) : PublicKeyCryptosystem(privateKey) {
    setPrivateKey2(privateKey2);
    setModulus();
    setPublicKey();
}
RSA::RSA(int privateKey, int privateKey2) : PublicKeyCryptosystem(privateKey) {
    setPrivateKey2(privateKey2);
    setModulus();
    setPublicKey();
}
RSA::RSA(long long privateKey, long long privateKey2, long long publicKey) : PublicKeyCryptosystem(privateKey) {
    setPrivateKey2(privateKey2);
    setModulus();
    setPublicKey(publicKey);
}
RSA::RSA(int privateKey, int privateKey2, int publicKey) : PublicKeyCryptosystem(privateKey) {
    setPrivateKey2(privateKey2);
    setModulus();
    setPublicKey(publicKey);
}
RSA::~RSA() {}

long long RSA::getPrivateKey2() {
    return privateKey2;
}
long long RSA::encrypt(long long plainText) {
    return modPow(plainText, publicKey, modulus);
}
long long RSA::timedEncrypt(long long plainText, std::ostream& os, char delim) {
    int numBits1 = floor(log2(privateKey)) + 1;
    int numBits2 = floor(log2(privateKey2)) + 1;
    int avgNumBits = (numBits1 + numBits2) / 2;
    
    auto startTime = std::chrono::steady_clock::now();
    long long encryptedText = encrypt(plainText);
    auto endTime = std::chrono::steady_clock::now();

    os << avgNumBits << delim << std::chrono::duration_cast<std::chrono::nanoseconds>(endTime - startTime).count() << std::endl;

    return encryptedText;
}
long long RSA::decrypt(long long cipherText) {
    long long prime1 = privateKey - 1;
    long long prime2 = privateKey2 - 1;
    long long decryptionMod = prime1 * prime2;
    long long decryptionKey = modInverse(publicKey, decryptionMod);
    return modPow(cipherText, decryptionKey, modulus);
}
long long RSA::timedDecrypt(long long cipherText, std::ostream& os, char delim) {
    int numBits1 = floor(log2(privateKey)) + 1;
    int numBits2 = floor(log2(privateKey2)) + 1;
    int avgNumBits = (numBits1 + numBits2) / 2;
    
    auto startTime = std::chrono::steady_clock::now();
    long long decryptedText = decrypt(cipherText);
    auto endTime = std::chrono::steady_clock::now();

    os << avgNumBits << delim << std::chrono::duration_cast<std::chrono::nanoseconds>(endTime - startTime).count() << std::endl;

    return decryptedText;
}
