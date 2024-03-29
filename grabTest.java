public class grabTest {
    public static void main(String[] args) {
        try {
            grabbingRandomPrime grabbedPrime = new grabbingRandomPrime();
            System.out.println(grabbedPrime.numberGrab());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("test");
        }


        
    }
}
