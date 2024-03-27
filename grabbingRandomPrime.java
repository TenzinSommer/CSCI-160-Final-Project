import java.io.RandomAccessFile;
import java.util.Random;

public class grabbingRandomPrime {
    
    public int hehehehee() throws Exception {
        // File primesFile = new File('C:\\Users\\tenzi\\OneDrive\\Documents\\GitHub\\CSCI-160-Final-Project\\primes_primRoot2.txt')
        RandomAccessFile raf = new RandomAccessFile("primes_primRoot2.txt", "r");
        long fileLength = raf.length();
        Random bleh = new Random();
        raf.seek(bleh.nextLong(fileLength));
        String finalOutput = raf.readLine();
        raf.close();
        return Integer.valueOf(finalOutput);
        //return Integer.valueOf(raf.readLine());
       
    }

}

