import java.util.ArrayList;
import java.util.Collections;

public abstract class ValueBasedRL {
    protected Double ALPHA;
    protected Double GAMMA;
    // protected Double EPSILON;
    // protected Double EPS_DECAY_RATE;
    // protected Integer typeEpsilonDecay;
    protected Integer numActions;

    //constructor
    protected ValueBasedRL(double alpha, double gamma, Integer numActions) {
        this.GAMMA = gamma;
        this.ALPHA = alpha;
        this.numActions = numActions;
    }

    protected void init(Environment env) {}

    protected void update (ArrayList<Domain> state, ArrayList<Domain> newState,
                           Integer action, Double reward) {}

    protected Double getValue(ArrayList<Domain> state, Integer action) {
        return -100.0;
    }

    protected ArrayList<Double> getValues(ArrayList<Domain> state) {
        return new ArrayList<Double>();
    }

    protected Integer getIdBestValue(ArrayList<Domain> state) {
        ArrayList<Double> vals = getValues(state);
        Double maxV = -Double.MIN_VALUE;
        Integer id = -1;
        for (Integer i = 0 ; i < vals.size(); i++) {
            if (vals.get(i) > maxV) {
                maxV = vals.get(i);
                id = i;
            }
        }
        return id;
    }

    protected Double getBestValue(ArrayList<Domain> state) {
        return Collections.max(getValues(state));
    }

    protected Integer epsilonGreedyPolicy(double epsilon, ArrayList<Domain> state) {
        if (Math.random() < epsilon)
            return (int) Math.floor(Math.random() * this.numActions);
        else {
            return getIdBestValue(state);
        }
    }


    protected abstract void proc();
    protected abstract void accessMemoryObjects();
    // may be unnecessary
    protected abstract void calculateActivation();
}
