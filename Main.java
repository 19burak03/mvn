import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import java.util.*;

public class Main {

    public static void main(String[] args) {
       System.out.println(args[0] + "  " + args[1]);
       String frk = args[0];
       Integer brk = Integer.valueOf(args[1]);
       HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
       Map<String, String> map = hazelcastInstance.getMap(frk);
       for (int i = 0; i < brk; i++) {
           map.put("key" + i, "value" + i);
       }
    }
}
