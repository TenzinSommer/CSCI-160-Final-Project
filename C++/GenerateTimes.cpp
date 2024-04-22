#include "ElGamal.h"
#include "RSA.h"
#include "grabbingRandomPrime.h"

#include <limits>

int main() {
    srand(static_cast<int>(time(NULL)));
    
    const int NUM_DATA_POINTS = 1000;
    
    std::ofstream output;

    // ElGamal
    grabbingRandomPrime randomGen = grabbingRandomPrime();
    for (int i = 0; i < NUM_DATA_POINTS; ++i) {
        ElGamal elGamal = ElGamal();
        long long e_plainText = i + 3;
        int randomKey = randomGen.genRandValue(3, INT_MAX);
        
        output.open("ElGamal_encryptTimes.txt", std::ios_base::app);
        Pair<long long> e_encryptedText = elGamal.timedEncrypt(e_plainText, randomKey, output, ',');
        output.close();
        
        output.open("ElGamal_decryptTimes.txt", std::ios_base::app);
        long long e_decryptedText = elGamal.timedDecrypt(e_encryptedText, output, ',');
        output.close();
    }

    // RSA
    for (int i = 0; i < NUM_DATA_POINTS; ++i) {
        RSA rsa = RSA();
        long long r_plainText = i + 3;
        
        output.open("RSA_encryptTimes.txt", std::ios_base::app);
        long long r_encryptedText = rsa.timedEncrypt(r_plainText, output, ',');
        output.close();

        output.open("RSA_decryptTimes.txt", std::ios_base::app);
        long long r_decryptedText = rsa.timedDecrypt(r_encryptedText, output, ',');
        output.close();
    }
    
    return 0;
}
