import java.io.RandomAccessFile;
import java.util.Random;

// this class is to take a number from our primes text file randomly and return it

public class grabbingRandomPrime {

    public Pair<Integer> numberGrab(String fileName, int fileLength) throws Exception {
        // makes a new randomaccessfile
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        // makes a new random to grab a line with
        Random randomSelect = new Random();
        // picks a random number from 0 to fileLength and finds the line corresponding to it
        // long line = randomSelect.nextInt() % 10001;
        // the hexadecimals make it so that it can't be negative (important!!!)
        int line = (randomSelect.nextInt() & 0x7fffffff) % (fileLength + 1);

        for (int i = 0; i < line - 1; i++) {
            // goes through all lines until it reaches line
            raf.readLine();
        }

        String finalOutput = raf.readLine().trim();
        raf.close();
        Pair<Integer> finalAndLine = new Pair<Integer>(Integer.valueOf(finalOutput), Integer.valueOf(line));
        return finalAndLine;
    }

    public Pair<Integer> numberGrabLessThan(String fileName, int fileLength, int prevLine) throws Exception {
        // don't pick randomly and instead use line found in first version

        // makes a new randomaccessfile
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");

        // the hexadecimals make it so that it can't be negative (important!!!)
        int line = ((prevLine / 2) & 0x7fffffff) % (fileLength + 1);

        for (int i = 0; i < line - 1; i++) {
            // goes through all lines until it reaches line
            raf.readLine();
        }

        String finalOutput = raf.readLine().trim();
        raf.close();
        Pair<Integer> finalAndLine = new Pair<Integer>(Integer.valueOf(finalOutput), Integer.valueOf(line));
        return finalAndLine;
    }

    // returns the prime right before the given line if the given line is greater than 1, or one after
    // the given line if the given line is the first line
    public Pair<Integer> numberGrabNotEqual(String fileName, int fileLength, int prevLine) throws Exception {
        // makes a new randomaccessfile
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");

        // the hexadecimals make it so that it can't be negative (important!!!)
        int line;
        if (prevLine > 1) {
            line = ((prevLine - 1) & 0x7fffffff) % (fileLength + 1);
        } else {
            line = ((prevLine + 1) & 0x7fffffff) % (fileLength + 1);
        }

        for (int i = 0; i < line - 1; i++) {
            // goes through all lines until it reaches line
            raf.readLine();
        }

        String finalOutput = raf.readLine().trim();
        raf.close();
        Pair<Integer> finalAndLine = new Pair<Integer>(Integer.valueOf(finalOutput), Integer.valueOf(line));
        return finalAndLine;
    }
}
