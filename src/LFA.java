import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

public class LFA extends ValueBasedRL  {

    public LFA(double alpha, double gamma, double epsilon, double epsDecayRate,
                                  Integer typeEpsilonDecay, Integer numActions) {
        super(alpha, gamma, epsilon, epsDecayRate, typeEpsilonDecay, numActions);
    }
    private LinkedHashMap<String, Double> weights = new LinkedHashMap<String, Double>();
    private featuresExtractor extractor = new featuresExtractor();

    public LinkedHashMap<String, Double> getWeights() {
        return this.weights;
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
                       Integer action, int reward) {
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
}
