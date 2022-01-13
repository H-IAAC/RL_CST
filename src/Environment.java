import java.util.ArrayList;
import java.util.HashMap;

public abstract class Environment {
    private int numDim;
    // (min value, max value, type: 0 - Discrete, 1 - Continuous)
    private Domain actionSpace[];
    private boolean displayGame;
    // faz sentido ter codelets?
    private ArrayList codelets;
    // faz sentido NAO ter memoryObjects?
    private ArrayList memoryObjects;
    // o espaco de observacao nao seria o próprio conjunto de dados acessados pelso objetos de memória?
    private ArrayList<Domain> observationSpace;

    public Environment(int numDim, Domain actionSpace[], boolean displayGame) { // Codelets codelets, MemoryObjects memoryObjects)
        this.numDim = numDim;
        this.actionSpace = actionSpace;
        this.displayGame = displayGame;
        // this.codelets = codelets;
        // this.memoryObjects = memoryObject;
    }

    public ArrayList<Domain> getObservationSpace() {
        return this.observationSpace;
    }

    public Domain[] getActionSpace() {
        return this.actionSpace;
    }

    // extract information of Codelets to update enviroment.
    public abstract void extractCodelets();

    // extract information of Memory Objects to update enviroment.
    public abstract void extractMemoryObjects();

    // returns the new observation space at the end of the function.
    public abstract ArrayList<Domain> reset();

    // returns {observationSpace : ArrayList<Domain>, reward : Double, done : boolean, info : String}
    public abstract ArrayList step(Domain action);

    // depending of your problem, you might want to display your environment.
    public abstract void render();
}
