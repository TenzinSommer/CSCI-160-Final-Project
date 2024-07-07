# Comparing the Efficiency of RSA and ElGamal: A Performance Analysis of Two Similarly Secure, Asymmetric Cryptosystems
### Mia Kelley-Lanser and Tenzin Sommer

## Abstract
We study two asymmetric cryptosystems, RSA [12] and ElGamal [4], comparing their runtimes. Despite the two systems sharing similar security levels [1] — RSA based on the difficulty of factoring the product of large primes and ElGamal on the discrete logarithm problem —RSA is much more commonly used for applications such as website encryption [9]. Our project investigates whether RSA is favored for its greater efficiency by comparing the runtimes of the two systems’ encryption and decryption algorithms. After devising and implementing the algorithms and calculating their time complexity, a sampling of 1000 execution times for both the encryption and decryption functions of each cryptosystem was recorded. The data collected was plotted in graphs to help visualize the results. Two different languages were used in the implementation and data collection: Java and C++. As Java’s performance as an interpreted language is generally slower than that of the compiled language C++ [19], both languages were used to provide diverse data points to compare the cryptosystems’ efficiency. Between the two languages, cryptosystems, and methods, 8000 datapoints were collected and analyzed. Our data in both languages supports the hypothesis that RSA is significantly faster in its encryption process but shows that the two systems are closer in performance in decryption, where ElGamal performs marginally better than RSA. Our implementation is also available for others to download and collect additional runtime data on their own.

## Problem Statement
A limited number of asymmetric cryptosystems have been devised to date. The first published presentation of the concept of asymmetric cryptography was authored by Whitfield Diffie and Martin Hellman in their paper, “New Directions in Cryptography” in 1976 [3], [6]. With less than half a century of advancement in asymmetric systems in comparison to the thousands of years [2] spent developing symmetric systems, considerably fewer asymmetric systems have been devised and published than symmetric. ElGamal [4] and RSA [12] are two of the most academically established asymmetric systems [16]. However, RSA is much more commonly used; most websites’ encryption certificates list RSA [9], while ElGamal seems to be left in textbooks as a system to study and not put to use. Despite this disparity in popularity, both are similarly secure [1], asymmetric cryptosystems. Why then is one favored so heavily over the other? Our hypothesis was that ElGamal is *less efficient* than RSA.

While security is the most obvious measure of a good cryptosystem, the time it takes for authorized, communicating parties to encrypt and decrypt data is crucial; large values [13] are used in standard industry encryption processes to combat the extensive computational power we have today. To enable near-instant access to personal data on web-based applications, such as online banking, shopping, and medical record access, the process of “locking” and “unlocking” communicated information before and after transit must be fast. Therefore, given that ElGamal and RSA are similarly secure [1] options, it would make sense if RSA were widely used for its comparative speed. With available information regarding this hypothesis largely limited to opinions on online forums without any data backing them, we decided to produce our own data to test and compare the two systems’ performances.

## Solution
Our implementation of the ElGamal and RSA cryptosystems provides a way for anyone to easily collect data to compare the efficiency of the two systems’ encryption and decryption processes. Timed tests can be run in both Java and C++ to procure data on how long it takes to execute each system in relation to private key size in both an interpreted and a compiled language. Eight-thousand total data points are also provided and graphed to quickly visualize the comparative performances of each system in each language.
While the main purpose of this implementation is to produce runtime data, the systems are fully functional and can be used for noncommercial encryption needs, with 10,000 provided prime numbers [14] up to 310,091 for random key and modulus generation, as well as the option to supply your own values. Pseudocode for the encryption and decryption algorithms of each system is also supplied for those that want to gain a better understanding of how the cryptosystems work.

## System Architecture
The structure of our software is broken down into classes containing the methods needed for each cryptosystem. At the highest level, there is an abstract Public Key Cryptosystem class  [\[Java\]](Java/Classes/PublicKeyCryptosystem.java) [\[C++\]](C++/Headers/PublicKeyCryptosystem.h) that contains the basic member variables and methods common to both RSA and ElGamal (e.g., a modulus, a get modulus function, etc.). The RSA  [\[Java\]](Java/Classes/RSA.java) [\[C++\]](C++/Headers/RSA.h) and ElGamal [\[Java\]](Java/Classes/ElGamal.java) [\[C++\]](C++/Headers/ElGamal.h) classes are derived from this abstract class and inherit all of its contents. However, each implements its own encryption and decryption functions as well as distinct setters, getters, and member variables. Our code is structured in this way both to show a clear demonstration of the relation between the two cryptosystems and to eliminate repetitive function declarations that are needed in both RSA and ElGamal.

In order to implement ElGamal’s encryption process, which uses a pair of ciphertext values rather than a single value, we include a templated Pair class [\[Java\]](Java/Classes/Pair.java) [\[C++\]](C++/Headers/Pair.h) that can store two values and be used as a return type.

Finally, as both systems require the use of primes for multiple values in the encryption and decryption processes, we have outsourced a database of primes [14] of reasonable size for our purposes (up to 310,091). Our grabbingRandomPrime class [\[Java\]](Java/Classes/grabbingRandomPrime.java) [\[C++\]](C++/Headers/grabbingRandomPrime.h) handles the basic file accessing, providing methods to grab a random prime, grab a random prime less than a given prime, and a random prime not equal to a given prime.

All implementations are done in both [Java](Java) and [C++](C++) and can be found in their distinctive folders in the repository. See the classes UML diagrams below, or in [PNG format](UMLs) for detailed descriptions of the variables and methods in each class.

### Java Classes UML Diagram
![Classes UML diagram for our Java solution showing the variables and methods in each class, as well as the relations between the classes.](/UMLs/Java_Comparing%20Efficiency%20of%20ElGamal%20and%20RSA%20UML.png)

### C++ Classes UML Diagram
![Classes UML diagram for our C++ solution showing the variables and methods in each class, as well as the relations between the classes.](/UMLs/C++_Comparing%20Efficiency%20of%20ElGamal%20and%20RSA%20UML.png)

## Component Interaction
Since both the RSA [\[Java\]](Java/Classes/RSA.java) [\[C++\]](C++/Headers/RSA.h) and ElGamal [\[Java\]](Java/Classes/ElGamal.java) [\[C++\]](C++/Headers/ElGamal.h) classes contain timed encrypt and decrypt functions that print data to a text file in addition to the un-timed ones, both classes have methods that may throw an IOException in the Java implementation. Similarly, our grabbingRandomPrime class [\[Java\]](Java/Classes/grabbingRandomPrime.java) [\[C++\]](C++/Headers/grabbingRandomPrime.h), which handles the basics of file access for prime generation from our primes source [14], can throw a file not found exception if the [primes_primRoot2.txt](Primes%20Source/primes_primRoot2.txt) file is not properly included.

The Generate Times class [\[Java\]](Java/Tests/GenerateTimes.java) [\[C++\]](C++/Tests/GenerateTimes.cpp) in each language can be used to produce more data in CSV format. Currently, the number of tests is set to 1000 per function per cryptosystem, however, this can easily be changed by altering the value of the const/static variable at the top, NUM_DATA_POINTS. However, it should be noted that the time to produce the data is not trivial; to produce 1000 datapoints for each function (encryption and decryption) in each cryptosystem, it took 2 minutes and 25 seconds in Java and 4 seconds in C++.

The Test Public Key Cryptosystem [\[Java\]](Java/Tests/TestPublicKeyCryptosystem.java) [\[C++\]](C++/Tests/TestPublicKeyCryptosystem.cpp) in both languages can be used for general testing purposes without producing time data. In Java, a [unit test](Java/Tests/PublicKeyCryptosystem_UnitTest.java) to show correctness is also provided, as well as [toy examples](Java/Toy%20Examples) we developed early on without the object-oriented design. Note that C++ files are separated into distinct header and cpp files for the Public Key Cryptosystem, ElGamal, and RSA classes, while Java classes are each contained in one file.

## Cryptosystem Comparison
While RSA and ElGamal differ in basis for security (the inefficiency of factoring for RSA and the difficulty of the Discrete Logarithm Problem for ElGamal), both share operations in common in their encryption and decryption processes. This makes comparison of the two systems’ algorithms feasible.

We examine and compare the operations in each system’s encryption and decryption methods in order to determine the relation between the two systems’ efficiencies.

The following pseudocode shows the encryption process for RSA:

<img width="586" alt="FR_RSA_Encryption_Pseudocode" src="https://github.com/TenzinSommer/CSCI-160-Final-Project/assets/89313852/e35e5141-e960-45ff-8e98-854fa42d3a5f">

At a high level, the process involves one exponentiation (*m<sup>e</sup>*) and one reduction in a modulus (% *N*). However, examining the following pseudocode, it is easy to see that ElGamal’s encryption process is more laborious:

<img width="585" alt="FR_ElGamal_Encryption_Pseudocode" src="https://github.com/TenzinSommer/CSCI-160-Final-Project/assets/89313852/de93cc29-2452-4259-bf19-078df4dc5ca9">

Here, line 2 has the same operations as the RSA encryption algorithm: one exponentiation (*g<sup>k</sup>*) and one reduction in a modulus (% *p*). However, there is more work on line 3: another exponentiation (*pubKey<sup>k</sup>*), another reduction in a modulus (% *p*), and a multiplication (*m* · the result of *pubKey<sup>k</sup>* % *p*).

In effect, the ElGamal encryption algorithm therefore requires over twice as much work, with twice each operation in RSA, plus an extra multiplication operation.

The decryption relation is perhaps more intriguing. The following pseudocode shows the decryption process for RSA:

<img width="586" alt="FR_RSA_Decryption_Pseudocode" src="https://github.com/TenzinSommer/CSCI-160-Final-Project/assets/89313852/6cc3bdd9-b9c4-4226-8fe8-d9707cdae51e">

The process here involves several operations: one inverse operation (*c*<sup>-1</sup>), one multiplication ((*p* – 1)(*q* – 1)), one exponentiation (*c<sup>d</sup>*), and two reductions in a modulus (% the result of (*p* – 1)(*q* – 1), and % *N*).

Surprisingly, ElGamal’s decryption process looks quite similar:

<img width="585" alt="FR_ElGamal_Decryption_Pseudocode" src="https://github.com/TenzinSommer/CSCI-160-Final-Project/assets/89313852/662483e9-8ef1-4f9c-b081-e6053147ee86">

Like RSA, ElGamal’s decryption algorithm has one inverse operation ((the result of *c*<sub>1</sub>*<sup>privKey</sup>*) <sup>-1</sup>), one multiplication (*x* · *c*<sub>2</sub>), one exponentiation (*c*<sub>1</sub>*<sup>privKey</sup>*), and two reductions in a modulus (two separate % *p* operations).

Due to the two systems’ equal number of each type of operation, they should have similar performances in decryption. Any variation in performance would likely be due to variation in the size of each value and the order the operations are performed in.

Therefore, through analysis of the two systems’ pseudocode, we have determined that ElGamal takes over twice as much work as RSA in encryption, while the two systems take about the same amount of work in decryption. Therefore, RSA should be over two times faster for encryption, while the two systems should take about the same amount of time to execute decryption processes.

## Results and Analysis
We ran our code to generate data on a 2020 MacBook Pro with a 2 GHz Quad-Core Intel Core i5 processor, 16 GB of memory, and Ventura 13.2 operating system. In total, we collected 8000 data points. We timed 1000 ElGamal encryptions, 1000 ElGamal decryptions, 1000 RSA encryptions, and 1000 RSA decryptions each in Java and C++, totaling to 4000 tests in each language and 8000 total tests. Duration of the execution of encryption and decryption functions were recorded alongside the size of the private key for each time.

Our data confirms that RSA’s encryption process is over twice more efficient than ElGamal’s in both Java and C++. In Java, encryption at the median private key size of 15 bits averaged at 23564 nanoseconds for ElGamal, while RSA averaged at 7970, meaning RSA was 3 times faster, which is consistent with our pseudocode analysis that ElGamal's encryption algorithm takes over twice as much work as RSA’s. The figure below shows the graph for all tested private key sizes for Java encryption. Examining the trends, not only is ElGamal higher on the graph (higher meaning taking more nanoseconds to execute), but ElGamal’s graph spikes much more steeply, suggesting RSA’s encryption performance is more consistent in Java.

![A graph comparing ElGamal and RSA's encryption performance in Java.](/Results/Graphs/Java%20Encryption%20Performance.png)

Similar results came from C++ runtimes; at a 15-bit private key size, ElGamal averaged at 1086 nanoseconds while RSA averaged at 436, meaning RSA was 2.5 times faster. This is perhaps even closer to our pseudocode analysis, which showed that ElGamal took a little over twice as much work as RSA in encryption. The results of our data are reflected in the figure below, which shows the graph for all tested private key sizes for C++ encryption. The trends for ElGamal and RSA show similar rates of growth, with RSA consistently remaining much lower (lower meaning taking fewer nanoseconds) on the graph. Notably, the C++ graph is much smoother in comparison to the Java graph, most likely due to the lack of dynamic data allocation, automatic garbage collection, and use of primitive types in the C++ implementation.

![A graph comparing ElGamal and RSA's encryption performance in C++.](/Results/Graphs/C++%20Encryption%20Performance.png)

The decryption data is more surprising.

In Java, decryption at the median private key size of 15 bits averaged at 19031 nanoseconds for ElGamal, while RSA averaged at 17044, meaning RSA was only 1.1 times faster. However, as the graph below shows, by the 16-bit point, ElGamal becomes slightly faster than RSA. The graph clearly shows that the two systems are close in runtimes, reflecting the pseudocode analysis suggestion that the systems take about the same amount of work. Once again, ElGamal’s graph spikes much more steeply than RSA’s, suggesting RSA’s decryption performance is also more consistent in Java.

![A graph comparing ElGamal and RSA's decryption performance in Java.](/Results/Graphs/Java%20Decryption%20Performance.png)

However, the C++ decryption data showcases a more dramatic difference; at a 15-bit private key size, ElGamal averaged at 539 nanoseconds while RSA averaged at 826, meaning ElGamal was 1.5 times faster. These results are reflected in the figure below, which shows the graph for all tested private key sizes for C++ decryption. While a bit farther than the Java data from our expectations from our pseudocode analysis, in comparison to 2.5 to 3 times difference between RSA and ElGamal’s encryption performances, a 1.5 times difference is still relatively similar between the two systems for decryption. The graphs for ElGamal and RSA in C++ show similar rates of growth for the two systems, but here with RSA consistently remaining much higher (higher meaning taking more nanoseconds) on the graph. Once again, the C++ graph is much smoother in comparison to the Java graph.

![A graph comparing ElGamal and RSA's decryption performance in C++.](/Results/Graphs/C++%20Decryption%20Performance.png)

Overall, RSA is shown to be the more efficient system, as it was, across the board, faster than ElGamal for encryption, with larger margins than ElGamal’s win in decryption performance. While RSA performed better in encryption for both Java and C++, the decryption results differed between the languages; in Java, decryption was slightly faster (1.1 times) in RSA while in C++, it was faster (1.5 times) in ElGamal.

One reason ElGamal may have performed better than RSA in C++ decryption is due to the size of the values used in the operations. For example, the modPow operation in ElGamal is the first cipher text value to the power of the privateKey (a random value between 2 and the modulus), mod the modulus. The first cipher text value is previously calculated in the encrypt function as primElement to the power of the randomKey (between 3 and the max int value), mod the modulus. For our implementation, we outsourced primes [14] that all have a primitive root of 2 (which is a common primitive root) to avoid the complicated process of finding a primitive root of a given prime. However, 2 is notably one of the smallest possible primitive roots (1 being the smallest primitive root of a prime value). It is possible that this consistency of a low value made ElGamal’s decryption process deal with lower values than RSA’s, where there are no consistent low values used, only random ones. However, a study of relationships between values and their inverses within moduli would have to be conducted in order to gain proper insight on the likelihood of this having a major effect.

While this same speculation applies to our Java implementation, Java is less predictable due to its built-in dynamic data management and garbage collection; the slight edge RSA had on ElGamal in decryption in Java may be due to more spikes in the handling of ElGamal’s data than RSA’s for temporary variables in its methods. This hypothesis is supported by the Java decryption trend, which is spikier in comparison to the RSA decryption trend. Notably, the two trends do cross, with ElGamal performing a bit better than RSA from 16 bits to 18 bits in private key size. Perhaps, without spikes in garbage collection, the trends would be even closer together, or perhaps ElGamal would outperform RSA in decryption, as it does in C++.

## Conclusion
Our data supports the hypothesis that RSA is overall more efficient than ElGamal, with RSA encryption performing 2.5 to 3 times faster than ElGamal. However, for decryption, ElGamal performed marginally better at 1.5 times the speed of RSA’s decryption in C++ while RSA only performed 1.1 times faster than ElGamal in decryption in Java. Therefore, overall, RSA is the more efficient cryptosystem. This data is supported by the analysis of the algorithms themselves, which show that ElGamal has twice as many operations as RSA in its decryption process, while the two systems have the same number of each type of operation in decryption, therefore having similar runtimes. This data helps inform why RSA has taken over the current asymmetric cryptography applications, such as website security.

## Implementation Sources
For our implementation, we outsourced prime values. The primes_primRoot2.txt file in our GitHub contains the primes without the beginning comments from b001122.txt. These primes were obtained from The On-Line Encyclopedia of Integer Sequences (OEIS) [14] and can be found [here](https://oeis.org/A001122).

We use this source in particular as it contains a list of primes that all have a primitive root of 2. ElGamal requires a prime modulus and corresponding primitive root of that prime. Because finding a primitive root of a given prime is not straightforward and is not part of our time calculations and comparisons between the two cryptosystems, we chose to use a random prime from this list with a primitive root of 2 when the ElGamal class is not supplied with a modulus and primitive root in the constructor. As there are 10000 primes with a primitive root of 2 supplied in this list, we found it sufficient for our purposes.

Although RSA does not have the same need for prime and primitive root pairs, because we are comparing the runtimes of the two cryptosystems, we use the same source for RSA's primes.

In addition, while we used the Big Integer class methods for modular operations in Java, we implemented them ourselves in C++. Algorithms for modPow, modInverse, gcd, and
extendedEuclideanAlgorithm were derived from the pseudocode and explanations in An Introduction to Mathematical Cryptography by Jeffrey Hoffstein, Jill Pipher, and Joseph H. Silverman [6].

## Future Work
There are several potential avenues for further work on our implementation and study. In our development process, the Big Integer class in Java was the cause of a redesign of our classes due to the discovery of its immutable classification. However, ideally setter methods in our classes would be made public instead of protected with mutable datatypes so that users can change their private key or other values after initialization without creating a whole new object. There are some existing implementations of mutable big integer classes, however they are not produced by Oracle. Creating our own mutable big integer class would allow us to optimize methods for efficiency and may be helpful for further expansion of our project in Java with other cryptosystems.

In terms of data collection and comparison of runtimes, we could provide more time- testing functions, such as for modulus calculation in RSA or public key generation in ElGamal. While these methods are not used as frequently as encryption and decryption since the values may be used multiple times, the calculations are still non-trivial and would be interesting to compare for efficiency. Additionally, we could increase the size of our data set by expanding our prime database. Our largest primes were 19 bits, which is dwarfed by the size of industry values—usually at least 2048 bits [13]. With a sufficient, larger set of primes closer to this size, we could collect data even more relevant to industry usage.

Finally, while ElGamal and RSA are two well-known asymmetric cryptosystems, there are others we could implement and compare, such as ECC (Elliptic Curve Cryptography). As ElGamal is not used as often in practice, comparing a more typically used system to the industry standard, RSA, could provide more insight into the comparative strengths and weaknesses in performance of the top-competing systems.

## References and Related Work
[1] Boudot, Fabrice et al. Comparing the Difficulty of Factorization and Discrete Logarithm: a 240-digit Experiment. IACR, 2020. https://eprint.iacr.org/2020/697.pdf. Accessed 1 May 2024.

[2] Britannica, The Editors of Encyclopaedia. "cipher". Encyclopedia Britannica, 9 Mar. 2024. https://www.britannica.com/topic/cipher. Accessed 1 May 2024.

[3] Diffie, Whitfield, and Martin E. Hellman. New Directions in Cryptography. IEEE Transactions on Information Theory, Vol. IT-22, NO. 6, Nov. 1976. https://ee.stanford.edu/~hellman/publications/24.pdf. Accessed through Stanford Department of Electrical Engineering, 1 May 2024.

[4] ElGamal, Taher. “A Public Key Cryptosystem and a Signature Scheme Based on Discrete Logarithms.” IEEE Transactions on Information Theory, Vol. IT-31, NO. 4, July, 1985. https://caislab.kaist.ac.kr/lecture/2010/spring/cs548/basic/B02.pdf. Accessed 26 Apr. 2024.

[5] Grewal, Jaspreet Kaur. “ElGamal: Public-Key Cryptosystem.” Math and Computer Science Department, Indiana State University. 30 Sept. 2015. https://cs.indstate.edu/~jgrewal/steps.pdf. Accessed 26 Apr. 2024.

[6] Hoffstein, Jeffrey et al. An Introduction to Mathematical Cryptography. Springer Science+Business Media New York, 2008.

[7] Ioannou, Orestis. “RSA_ElGamal.” GitHub, https://github.com/oorestisime/RSA_ElGamal. Accessed 26 Apr. 2024.

[8] “Java Security Standard Algorithm Names.” JavaTM Documentation, https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html. Accessed 26 Apr. 2024.

[9] Kee, Lila. “RSA Is Dead —We Just Haven’t Accepted It Yet.” Forbes, 6 May 2021. https://www.forbes.com/sites/forbestechcouncil/2021/05/06/rsa-is-dead--- we-just- haventaccepted-ityet/?sh=3ee6f4395d22. Accessed 1 May 2024.

[10] Lake, Josh. “Exploring RSA Encryption: A comprehensive guide to how it works.” Comparitech, 18 March 2021. https://www.comparitech.com/blog/information security/rsa-encryption/. Accessed 26 Apr. 2024.

[11] Litterio, Francis. “The Mathematical Guts of RSA Encryption. http://fringe.davesource.com/Fringe/Crypt/RSA/Algorithm.html. Accessed 26 Apr. 2024.

[12] Rivest, Ronald L., et al. Cryptographic Communications System and Method. US4405829A, U.S. Patent and Trademark Office, 20 Sept. 1983. https://patents.google.com/patent/US4405829A/en. Accessed 26 Apr. 2024.

[13] “Size Considerations for Public and Private Keys.” IBM, 27 May 2021. https://www.ibm.com/docs/en/zos/2.4.0?topic=certificates-size-considerations-public- private-keys. Accessed 1 May 2024

[14] Sloane, N. J. A. “Primes with Primitive Root 2.” On-Line Encyclopedia of Integer Sequences. https://oeis.org/A001122. Accessed 1 May 2024

[15] Stüvel, Sybren. “Python-RSA.” GitHub, https://github.com/sybrenstuvel/python- rsa/tree/main. Accessed 26 Apr. 2024.

[16] Thakkar, Jay. “Types of Encryption: 5 Encryption Algorithms & How to Choose the Right One.” Security Boulevard, 23 May 2020. https://securityboulevard.com/2020/05/types-of-encryption-5-encryption-algorithms- how-to-choose-the-right-one/. Accessed 1 May 2024

[17] “The ElGamal Public Key Encryption Algorithm.” University of Illinois Chicago. https://homepages.math.uic.edu/~leon/mcs425-s08/handouts/el-gamal.pdf. Accessed 26 Apr. 2024.

[18] “The RSA Algorithm.” University of Illinois Chicago. http://homepages.math.uic.edu/~leon/mcs425-s08/handouts/RSA.pdf. Accessed 26 Apr. 2024.

[19] Tran, Trung. “Java Vs. C++: Which Is Better for Your Project?.” Orient Software, 21 Aug. 2023. https://www.orientsoftware.com/blog/java-vs-c-plus-plus/#:~:text=Speed%20%26%20Performance,the%20top%20fastest%20programming% 20languages. Accessed 1 May 2024.

[20] Weniseb. “RSA_CPP.” GitHub, https://github.com/weniseb/RSA_CPP. Accessed 26 Feb. 2024.
