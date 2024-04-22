#pragma once

#ifndef RSA_H
#define RSA_H

#include "PublicKeyCryptosystem.h"

class RSA : public PublicKeyCryptosystem {
protected:
    long long privateKey2;
    
    void setPrivateKeys();
    void setPrivateKeys(long long privateKey, long long privateKey2);
    void setPrivateKeys(int privateKey, int privateKey2);
    void setPrivateKey();
    void setPrivateKey2();
    void setPrivateKey2(long long privateKey);
    void setPrivateKey2(int privateKey2);
    void setModulus();

    void setPublicKey();
    void setPublicKey(long long publicKey);
    void setPublicKey(int publicKey);

public:
    RSA();
    RSA(long long privateKey, long long privateKey2);
    RSA(int privateKey, int privateKey2);
    RSA(long long privateKey, long long privateKey2, long long publicKey);
    RSA(int privateKey, int privateKey2, int publicKey);
    
    long long getPrivateKey2();
    long long encrypt(long long plainText);
    long long timedEncrypt(long long plainText, std::ostream& os, char delim);
    long long decrypt(long long cipherText);
    long long timedDecrypt(long long cipherText, std::ostream& os, char delim);
};

#endif
