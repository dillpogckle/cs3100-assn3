public class Worker extends Thread {
    public Worker(TaskQueue tasks, ResultTable results, Bpp bpp, Counter counter) {
        this.tasks = tasks;
        this.results = results;
        this.bpp = bpp;
        this.counter = counter;
    }

    private final TaskQueue tasks;
    private final ResultTable results;
    private final Bpp bpp;
    private final Counter counter;

    @Override
    public void run(){
        while(true){
            Task task = tasks.get();
            if (task == null)break;

            int pos = task.position;

            try{
                int block = bpp.getDecimal(pos);
                int digit = (int)(block / 100_000_000);
                results.put(pos, digit);

                int progress = counter.increment();
                if (progress % 10 == 0){
                    System.out.print(".");
                    if (progress % 100 == 0){
                        System.out.println();
                    }
                    System.out.flush();
                }
            }
            catch(Exception e){
                System.err.println("Error computing digit" + pos + ":" + e.getMessage());
            }
        }
    }
}
