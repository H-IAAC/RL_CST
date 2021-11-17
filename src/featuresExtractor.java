import java.util.ArrayList;
import java.util.LinkedHashMap;

public class featuresExtractor {
    public LinkedHashMap<String, Double> getFeatures(ArrayList<Domain> state, Integer action) {
        LinkedHashMap<String, Double> f = new LinkedHashMap<String, Double>();

        Integer[] d_xy = Agent.idToAction(action); // change name agent!
        Integer dx = d_xy[0], dy = d_xy[1];

        f.put("bias", 1.0);
        /*
        * create features
        * */

        return f;
    }
}
