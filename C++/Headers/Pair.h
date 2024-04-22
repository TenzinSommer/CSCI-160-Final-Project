#pragma once

#ifndef PAIR_H
#define PAIR_H

#include <string>
#include <sstream>

template<class T>
class Pair {
private:
    T first;
    T second;

public:
    Pair(T first, T second) {
        set(first, second);
    }
    void setFirst(T first) {
        this->first = first;
    }
    void setSecond(T second) {
        this->second = second;
    }
    void set(T first, T second) {
        setFirst(first);
        setSecond(second);
    }
    T getFirst() {
        return first;
    }
    T getSecond() {
        return second;
    }
    std::string toString() {
        std::stringstream ss;
        ss << first << ", " << second;
        return ss.str();
    }
};

#endif
