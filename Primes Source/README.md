## Source
The **primes_primRoot2.txt** file contains the primes without the beginning comments from **b001122.txt**. These primes were outsourced from The On-Line Encyclopedia of Integer Sequences (OEIS) and can be found [here](https://oeis.org/A001122). 



## Reasoning for Use
We use this source in particular, as it contains a list of primes that all have a primitive root of 2. ElGamal requires a prime modulus and corresponding primitive root of that prime. Because finding a primitive root of a given prime is not straightforward and is not part of our time calculations and comparisons between the two cryptosystems, we chose to use a random prime from this list with a primitive root of 2 when the ElGamal class is not supplied with a modulus and primitive root in the constructor. As there are 10000 primes with a primitive root of 2 supplied in this list, we found it sufficient for our purposes. 

Although RSA does not have the same need for prime and primitive root pairs, because we are comparing the runtimes of the two cryptosystems, we use the same source for RSA's primes.
