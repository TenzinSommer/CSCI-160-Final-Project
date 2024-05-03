#pragma once

#ifndef PUBLIC_KEY_CRYPTOSYSTEM
#define PUBLIC_KEY_CRYPTOSYSTEM

#include "Pair.h"
#include "grabbingRandomPrime.h"

class PublicKeyCryptosystem {
protected:
    long long modulus;
    long long privateKey;
    long long publicKey;

    PublicKeyCryptosystem();
    PublicKeyCryptosystem(long long privateKey);
    PublicKeyCryptosystem(int privateKey);
    PublicKeyCryptosystem(long long modulus, long long privateKey);
    PublicKeyCryptosystem(int modulus, int privateKey);
    
    ~PublicKeyCryptosystem();

    virtual void setModulus() = 0;
    void setModulus(long long modulus);
    void setModulus(int modulus);
    virtual void setPrivateKey() = 0;
    void setPrivateKey(long long privateKey);
    void setPrivateKey(int privateKey);
    virtual void setPublicKey() = 0;

    Pair<int> genRandPrime();
    Pair<int> genRandPrimeLessThan(long greaterValue);
    Pair<int> genRandPrimeNotEqual(int prevLine);

    long genRandValue(long min, long max);

    void extendedEuclideanAlgorithm(const long long &a, const long long &b, long long &u, long long &v);
    long long gcd(const long long &a, const long long &b);
    long long modInverse(const long long &value, const long long &modulus);
    long long modPow(const long long &base, const long long &exp, const long long &mod);

public:
    long long getModulus();
    long long getPrivateKey();
    long long getPublicKey();
};

#endif
