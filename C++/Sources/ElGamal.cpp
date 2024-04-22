#include "ElGamal.h"

#include "Pair.h"

#include <cmath>
#include <chrono>
#include <ostream>

void ElGamal::setModulus() {
    modulus = genRandPrime().getFirst();
}
void ElGamal::setPrivateKey() {
    privateKey = genRandValue(2, modulus);
}
void ElGamal::setPublicKey() {
    if (primElement != 0 && modulus != 0 && privateKey != 0) {
        this->publicKey = modPow(primElement, privateKey, modulus);
    }
}

void ElGamal::setPrimElement() {
    primElement = 2;
}
void ElGamal::setPrimElement(long long primElement) {
    this->primElement = primElement;
}
void ElGamal::setPrimElement(int primElement) {
    this->primElement = primElement;
}

ElGamal::ElGamal() : PublicKeyCryptosystem() {
    setModulus();
    setPrimElement();
    setPrivateKey();
    setPublicKey();
}
ElGamal::ElGamal(long long privateKey) : PublicKeyCryptosystem(privateKey) {
    setModulus();
    setPrimElement();
    setPublicKey();
}
ElGamal::ElGamal(int privateKey) : PublicKeyCryptosystem(privateKey) {
    setModulus();
    setPrimElement();
    setPublicKey();
}
ElGamal::ElGamal(long long modulus, long long privateKey, long long primElement) : PublicKeyCryptosystem(modulus, privateKey) {
    setPrimElement(primElement);
    setPublicKey();
}
ElGamal::ElGamal(int modulus, int privateKey, int primElement) : PublicKeyCryptosystem(modulus, privateKey) {
    setPrimElement(primElement);
    setPublicKey();
}
ElGamal::~ElGamal() {}

long long ElGamal::getPrimElement() {
    return primElement;
}

Pair<long long> ElGamal::encrypt(long long plainText, long long randomKey) {
    long long cipherText1 = modPow(primElement, randomKey, modulus);
    long long cipherText2 = modPow(publicKey, randomKey, modulus);
    cipherText2 *= plainText;
    cipherText2 = cipherText2 % modulus;
    
    Pair<long long> encryptedText(cipherText1, cipherText2);
    return encryptedText;
}
Pair<long long> ElGamal::timedEncrypt(long long plainText, long long randomKey, std::ostream& os, char delim) {
    int numBits = floor(log2(privateKey)) + 1;
    
    auto startTime = std::chrono::steady_clock::now();
    Pair<long long> encryptedText = encrypt(plainText, randomKey);
    auto endTime = std::chrono::steady_clock::now();

    os << numBits << delim << std::chrono::duration_cast<std::chrono::nanoseconds>(endTime - startTime).count() << std::endl;

    return encryptedText;
}
long long ElGamal::decrypt(Pair<long long> cipherText) {
    long long x = cipherText.getFirst();
    x = modPow(x, privateKey, modulus);
    x = modInverse(x, modulus);
    x = (x * cipherText.getSecond()) % modulus;
    return x;
}
long long ElGamal::timedDecrypt(Pair<long long> cipherText, std::ostream& os, char delim) {
    int numBits = floor(log2(privateKey)) + 1;
    
    auto startTime = std::chrono::high_resolution_clock::now();
    long long decryptedText = decrypt(cipherText);
    auto endTime = std::chrono::high_resolution_clock::now();

    os << numBits << delim << std::chrono::duration_cast<std::chrono::nanoseconds>(endTime - startTime).count() << std::endl;
    
    return decryptedText;
}
