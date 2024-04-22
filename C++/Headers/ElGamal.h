#pragma once

#ifndef ELGAMAL_H
#define ELGAMAL_H

#include "PublicKeyCryptosystem.h"
#include "Pair.h"

class ElGamal : public PublicKeyCryptosystem {
protected:
    long long primElement;

    void setModulus() override;
    void setPrivateKey() override;
    void setPublicKey() override;

    void setPrimElement();
    void setPrimElement(long long primElement);
    void setPrimElement(int primElement);

public:
    ElGamal();
    ElGamal(long long privateKey);
    ElGamal(int privateKey);
    ElGamal(long long modulus, long long privateKey, long long primElement);
    ElGamal(int modulus, int privateKey, int primElement);

    ~ElGamal();

    long long getPrimElement();

    Pair<long long> encrypt(long long plainText, long long randomKey);
    Pair<long long> timedEncrypt(long long plainText, long long randomKey, std::ostream& os, char delim);
    long long decrypt(Pair<long long> cipherText);
    long long timedDecrypt(Pair<long long> cipherText, std::ostream& os, char delim);
};

#endif
