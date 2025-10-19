import java.util.ArrayList;

public class Assn3 {
    public static void main(String[] args) {
        TaskQueue tasks = new TaskQueue();
        ResultTable table = new ResultTable();
        Counter counter = new Counter();
        Bpp bpp = new Bpp();
        int NUM_DIGITS = 1000;

        ArrayList<Integer> order = new ArrayList<>();

        for (int i = 1; i <= NUM_DIGITS; i++) {
            order.add(i);
        }
        for (int i : order) {
            tasks.add(new Task(i));
        }

        int cores = Runtime.getRuntime().availableProcessors();

       long start = System.currentTimeMillis();

       Thread[] threads = new Thread[cores];
       for (int i = 0; i < cores; i++) {
           threads[i] = new Worker(tasks, table, bpp, counter);
           threads[i].start();
       }

       for (Thread t : threads) {
           try {
               t.join();
           } catch (InterruptedException ignored) {}
       }

       long elapsed = System.currentTimeMillis() - start;

       StringBuilder sb = new StringBuilder();
       for (int i = 1; i <= NUM_DIGITS; i++) {
           Integer digit = table.get(i);
           sb.append(digit == null ? "0" : digit);
       }

        System.out.println();
        System.out.println("3." + sb);
        System.out.println("Elapsed time: " + elapsed + "ms");
    }
}
