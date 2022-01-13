import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

public class LFA extends ValueBasedRL  {
    private LinkedHashMap<String, Double> weights = new LinkedHashMap<String, Double>();
    private featuresExtractor extractor; // new featuresExtractor();

    public LFA(double alpha, double gamma, Integer numActions, featuresExtractor fe) {
        super(alpha, gamma, numActions);
        this.extractor = fe;
    }

    @Override
    public void init(Environment env) {
        LinkedHashMap<String, Double> gradient = this.extractor.getFeatures(env.getObservationSpace(), 0);
        Set<String> features = gradient.keySet();
        for(String f : features) {
            this.weights.put(f, Math.random());
        }
    }

    @Override
    public void update(ArrayList<Domain> state, ArrayList<Domain> new_state,
                       Integer action, Double reward) {
        LinkedHashMap<String, Double> gradient = this.extractor.getFeatures(state, action);

        Double new_q_val = this.getBestValue(new_state);
        Double q_val = this.getValue(state, action);

        Double target = reward + this.GAMMA * new_q_val;

        Set<String> name_features = gradient.keySet();
        for(String f : name_features) {
            Double w = this.ALPHA * (target - q_val) * gradient.get(f);
            this.weights.put(f, w);
        }
    }

    public LinkedHashMap<String, Double> getWeights() {
        return this.weights;
    }

    @Override
    protected Double getValue (ArrayList<Domain> state, Integer action) {
        Double q_val = (double) 0;
        LinkedHashMap<String, Double> gradient = this.extractor.getFeatures(state, action);

        Set<String> features = gradient.keySet();
        for (String f : features) {
            q_val += this.weights.get(f)*gradient.get(f);
        }

        return q_val;
    }

    @Override
    protected ArrayList<Double> getValues(ArrayList<Domain> state) {
        ArrayList<Double> vals = null;
        for (Integer a = 0; a < this.numActions; a++) {
            vals.add(getValue(state, a));
        }
        return vals;
    }

    @Override
    protected void proc() {

    }

    @Override
    protected void accessMemoryObjects() {
        // MemoryObject moA, moB, moC...
        // these memory objects are going to be basically the state, during training.
        // moX = this.getInput("NAME_MO_TYPE")
        // moY = this.getOutput("NAME_MO_TYPE")
        // // moList.append(mo[N])
    }

    @Override
    protected void calculateActivation() {

    }
}
