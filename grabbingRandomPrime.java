import java.io.RandomAccessFile;
import java.util.Random;

public class grabbingRandomPrime {
    
    public int numberGrab() throws Exception {
        // takes number from text file and returns it
        RandomAccessFile raf = new RandomAccessFile("primes_primRoot2.txt", "r");
        long fileLength = raf.length();
        Random randomSelect = new Random();
        raf.seek(randomSelect.nextLong(fileLength));
        String finalOutput = raf.readLine();
        raf.close();
        return Integer.valueOf(finalOutput);
        //return Integer.valueOf(raf.readLine());
       
    }

    }


