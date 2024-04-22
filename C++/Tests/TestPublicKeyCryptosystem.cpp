#include <iostream>
#include <fstream>

#include "ElGamal.h"
#include "RSA.h"
#include "Pair.h"
#include "grabbingRandomPrime.h"

int main(int argc, const char * argv[]) {
    srand(static_cast<int>(time(NULL)));
    
    // ElGamal
    std::cout << "ElGamal" << std::endl;
    ElGamal elGamal = ElGamal();
    std::cout << "Moulus: " << elGamal.getModulus() << std::endl;
    std:: cout << "Primitive Root: " << elGamal.getPrimElement() << std::endl;
    std:: cout << "Public Key: " << elGamal.getPublicKey() << std::endl;
    long long e_plainText = 331;
    std::cout << "Plain Text: " << e_plainText << std::endl;
    long long randomKey = 197;
    
    std::ofstream output;
    
    output.open("ElGamal_encryptionTimes.txt");
    Pair<long long> e_encryptedText = elGamal.timedEncrypt(e_plainText, randomKey, output, ',');
    std::cout << "Encrypted Text: " << e_encryptedText.toString() << std::endl;
    output.close();
    
    output.open("ElGamal_decryptionTimes.txt");
    long long e_decryptedText = elGamal.timedDecrypt(e_encryptedText, output, ',');
    std::cout << "Decrypted Text: " << e_decryptedText << std::endl;
    output.close();
    
    std::cout << std::endl;
    
    // RSA
    std::cout << "RSA" << std::endl;
    RSA rsa = RSA(1223, 1987);
    std::cout << "Modulus: " << rsa.getModulus() << std::endl;
    std::cout << "Public Key: " << rsa.getPublicKey() << std::endl;
    long long r_plainText = 1070777;
    std::cout << "Plaintext: " << r_plainText << std::endl;
    
    output.open("RSA_encryptTimes.txt");
    long long r_encryptedText = rsa.timedEncrypt(r_plainText, output, ',');
    std::cout << "Encrypted Text: " << r_encryptedText << std::endl;
    output.close();

    output.open("RSA_decryptTimes.txt");
    long long r_decryptedText = rsa.timedDecrypt(r_encryptedText, output, ',');
    std::cout << "Decrypted Text: " << r_decryptedText << std::endl;
    output.close();

    return 0;
}
