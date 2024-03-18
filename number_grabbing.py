textFile = open("b001122.txt")

newFile = open("primes_primRoot2.txt", "w")

for sentence in textFile:
    if sentence.startswith("#") == False:
        #print(sentence)
        #print(sentence[sentence.index(" "):])
        newFile.write(sentence[sentence.index(" "):])

textFile.close()
newFile.close()