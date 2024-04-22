#pragma once
#ifndef GRABBING_RANDOM_PRIME_H
#define GRABBING_RANDOM_PRIME_H

#include <string>
#include <fstream>

// this class is to take a number from our primes text file randomly and return it

class grabbingRandomPrime {
public:
    int genRandValue(int min, int max) {
        int range = max - min + 1;
        return rand() % range + min;
    }
    
    Pair<int> numberGrab(std::string fileName, int fileLength) {
        std::ifstream inputFile;
        std::string inputText;
        std::string strPrime;
        
        int randomLine = genRandValue(1, fileLength - 1);
        
        inputFile.open(fileName);
        
        int i = 0;
        while (getline(inputFile, inputText)) {
            if (i == randomLine - 1) {
                strPrime = inputText;
            }
            ++i;
        }
        inputFile.close();
        
        Pair<int> finalAndLine = Pair<int>(stoi(strPrime), randomLine);
        return finalAndLine;
    }

    Pair<int> numberGrabLessThan(std::string fileName, int fileLength, int prevLine) {
        std::ifstream inputFile;
        std::string inputText;
        std::string strPrime;
        
        int line = prevLine / 2;
        if (line < 1) {
            throw "Bad line number for numberGrabLessThan";
        }
        
        inputFile.open(fileName);

        int i = 0;
        while (getline(inputFile, inputText)) {
            if (i == line - 1) {
                strPrime = inputText;
            }
            ++i;
        }
        inputFile.close();

        Pair<int> finalAndLine = Pair<int>(stoi(strPrime), line);
        return finalAndLine;
    }

    // returns the prime right before the given line if the given line is greater than 1, or one after
    // the given line if the given line is the first line
    Pair<int> numberGrabNotEqual(std::string fileName, int fileLength, int prevLine) {
        std::ifstream inputFile;
        std::string inputText;
        std::string strPrime;

        int line;
        if (prevLine > 1) {
            line = prevLine - 1;
        } else {
            line = prevLine + 1;
        }

        inputFile.open(fileName);

        int i = 0;
        while (getline(inputFile, inputText)) {
            if (i == line - 1) {
                strPrime = inputText;
            }
            ++i;
        }
        inputFile.close();

        Pair<int> finalAndLine = Pair<int>(stoi(strPrime), line);
        return finalAndLine;
    }
};

#endif
