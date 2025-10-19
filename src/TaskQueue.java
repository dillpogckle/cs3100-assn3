import java.util.LinkedList;

public class TaskQueue {
    private final LinkedList<Task> tasks = new LinkedList<>();

    public synchronized void add(Task task) {
        tasks.addLast(task);
    }

    public synchronized Task get() {
        if (tasks.isEmpty()) return null;
        return tasks.removeFirst();
    }

    public synchronized boolean isEmpty() {
        return tasks.isEmpty();
    }
}
