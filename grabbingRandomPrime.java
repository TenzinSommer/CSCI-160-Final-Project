import java.io.RandomAccessFile;
import java.util.Random;

// this class is to take a number from our primes text file randomly and return it

public class grabbingRandomPrime {
    
    public int numberGrab(String fileName, long fileLength) throws Exception {
        // makes a new randomaccessfile
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        // makes a new random to grab a line with
        Random randomSelect = new Random();
        // picks a random number from 0 to fileLength and finds the line corresponding to it
        // long line = randomSelect.nextInt() % 10001;
        // the hexadecimals make it so that it can't be negative (important!!!)
        long line = (randomSelect.nextInt() & 0x7fffffff) % (fileLength + 1);
        
        for (int i = 0; i < line - 1; i++) {
            // goes through all lines until it reaches line
            raf.readLine();
        }

        String finalOutput = raf.readLine().trim();
        raf.close();
        return Integer.valueOf(finalOutput);
    }

}


