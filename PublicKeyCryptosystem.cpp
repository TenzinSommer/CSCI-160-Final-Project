#include "PublicKeyCryptosystem.h"

#include <cstdlib>

PublicKeyCryptosystem::PublicKeyCryptosystem(){}
PublicKeyCryptosystem::PublicKeyCryptosystem(long long privateKey) {
    setPrivateKey(privateKey);
}
PublicKeyCryptosystem::PublicKeyCryptosystem(int privateKey) {
    setPrivateKey(privateKey);
}
PublicKeyCryptosystem::PublicKeyCryptosystem(long long modulus, long long privateKey) {
    setModulus(modulus);
    setPrivateKey(privateKey);
}
PublicKeyCryptosystem::PublicKeyCryptosystem(int modulus, int privateKey) {
    setModulus(modulus);
    setPrivateKey(privateKey);
}

PublicKeyCryptosystem::~PublicKeyCryptosystem(){}

void PublicKeyCryptosystem::setModulus(long long modulus) {
    this->modulus = modulus;
}
void PublicKeyCryptosystem::setModulus(int modulus) {
    this->modulus = modulus;
}
void PublicKeyCryptosystem::setPrivateKey(long long privateKey) {
    this->privateKey = privateKey;
}
void PublicKeyCryptosystem::setPrivateKey(int privateKey) {
    this->privateKey = privateKey;
}

Pair<int> PublicKeyCryptosystem::genRandPrime() {
    grabbingRandomPrime primeGrabber = grabbingRandomPrime();
    return primeGrabber.numberGrab("primes_primRoot2.txt", 10000);
}
Pair<int> PublicKeyCryptosystem::genRandPrimeLessThan(long greaterValue) {
    grabbingRandomPrime primeGrabber = grabbingRandomPrime();
    Pair<int> primeLinePair = primeGrabber.numberGrab("primes_primRoot2.txt", 10000);
    int smallerValue = primeLinePair.getFirst();
    int smallerValueLine = primeLinePair.getSecond();

    while (smallerValue >= greaterValue) {
        primeLinePair = primeGrabber.numberGrabLessThan("primes_primRoot2.txt", 10000, smallerValueLine);
        smallerValue = primeLinePair.getFirst();
        smallerValueLine = primeLinePair.getSecond();
    }

    Pair<int> returnPair = Pair<int>(smallerValue, smallerValueLine);
    return returnPair;
}
Pair<int> PublicKeyCryptosystem::genRandPrimeNotEqual(int prevLine) {
    grabbingRandomPrime primeGenerator = grabbingRandomPrime();
    return primeGenerator.numberGrabNotEqual("primes_primRoot2.txt", 10000, prevLine);
}

long PublicKeyCryptosystem::genRandValue(long min, long max) {
    long range = max - min + 1;
    return rand() % range + min;
}

void PublicKeyCryptosystem::extendedEuclideanAlgorithm(const long long &a, const long long &b, long long &u, long long &v) {
    u = 1;
    long long g = a;
    long long x = 0;
    long long y = b;

    long long q, t, s;

    while (y != 0) {
        q = g / y;
        t = g % y;
        s = u - (q * x);
        u = x;
        g = y;
        x = s;
        y = t;
    }

    v = (g - (a * u)) / b;
}
long long PublicKeyCryptosystem::gcd(const long long &a, const long long &b) {
    if (b == 0) {
        return abs(a);
    }

    long long u, v;
    extendedEuclideanAlgorithm(a, b, u, v);
    return a * u + b * v;
}
long long PublicKeyCryptosystem::modInverse(const long long &value, const long long &modulus) {
    if (modulus == 0) {
        return -1;
    }

    long long u, v;
    extendedEuclideanAlgorithm(value, modulus, u, v);
    
    if (value * u + modulus * v != 1) {
        return -2;
    }

    return (u % modulus + modulus) % modulus;
}
long long PublicKeyCryptosystem::modPow(const long long &base, const long long &exp, const long long &mod) {
    // Fast powering algorithm
    long long a = base;
    long long b = 1;
    long long power = exp;
    
    while (power > 0) {
        if (power % 2 == 1) {
            b = (b * a) % mod;
        }
        a = (a * a) % mod;
        power = power / 2;
    }
    
    return b;
}

long long PublicKeyCryptosystem::getModulus() {
    return modulus;
}
long long PublicKeyCryptosystem::getPrivateKey() {
    return privateKey;
}
long long PublicKeyCryptosystem::getPublicKey() {
    return publicKey;
}
