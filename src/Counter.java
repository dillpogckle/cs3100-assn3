public class Counter {
    private int count = 0;
    public synchronized int increment(){
        count++;
        return count;
    }
}
