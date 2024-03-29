// this class is for testing if our grabbingRandomPrime works

public class testGrabbingRandomPrime {
    public static void main(String[] args) {
        try {
            grabbingRandomPrime grabbedPrime = new grabbingRandomPrime();
            System.out.println(grabbedPrime.numberGrab("primes_primRoot2.txt", 10000));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("test");
        }


        
    }
}
