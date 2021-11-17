import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Tabular extends ValueBasedRL {

    private HashMap<ArrayList<Domain>, ArrayList<Double>> qTable;

    //constructor
    public Tabular(double alpha, double gamma, double epsilon, double epsDecayRate, int typeEpsilonDecay, int numActions) {
        super(alpha, gamma, epsilon, epsDecayRate, typeEpsilonDecay, numActions);
    }

    protected void initQTable(ArrayList<Domain> state) {
        ArrayList<Double> initVals = new ArrayList<Double>();
        for (int i = 0; i < numActions; i++)
            initVals.add(Math.random());

        qTable.put(state, initVals);
    }

    @Override
    protected Double getValue(ArrayList<Domain> state, Integer idAction) {
        ArrayList<Double> val = qTable.get(state);
        if (val == null)
            initQTable(state);

        return qTable.get(state).get(idAction);
    }

    @Override
    protected ArrayList<Double> getValues(ArrayList<Domain> state) {
        return qTable.get(state);
    }

    @Override
    protected void update (ArrayList<Domain> state, ArrayList<Domain> newState, Integer idAction, int reward) {
        Double maxFutureQ = this.getBestValue(newState);
        ArrayList<Double> qValues = this.getValues(state);
        Double qVal = qValues.get(idAction);
        qVal += this.ALPHA*(reward + this.GAMMA * maxFutureQ - qVal);
        qValues.set(idAction, qVal);
        // perhaps it is not even necessary! Passed my reference.
        this.qTable.put(state, qValues);
    }
}
