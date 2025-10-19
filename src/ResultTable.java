import java.util.HashMap;

public class ResultTable {
    private final HashMap<Integer, Integer> map = new HashMap<>();

    public synchronized void put (int pos, int digit){
        map.put(pos, digit);
    }

    public synchronized Integer get (int pos){
        return map.get(pos);
    }
}
