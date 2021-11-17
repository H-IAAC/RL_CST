import java.util.ArrayList;
import java.util.HashMap;

public abstract class Environment {
    private int numDim;
    private Domain numActions;
    private boolean displayGame;
    private ArrayList codelets;
    private ArrayList memoryObjects;
    private ArrayList<Domain> observationSpace;

    public Environment(int numDim, Domain numActions, boolean displayGame) { // Codelets codelets, MemoryObjects memoryObjects)
        this.numDim = numDim;
        this.numActions = numActions;
        this.displayGame = displayGame;
        // this.codelets = codelets;
        // this.memoryObjects = memoryObject;
    }

    public ArrayList<Domain> getObservationSpace() {
        return this.observationSpace;
    }

    public Domain getActionSpace() {
        return this.numActions;
    }

    // extract information of Codelets to update enviroment.
    public abstract void extractCodelets();

    // extract information of Memory Objects to update enviroment.
    public abstract void extractMemoryObjects();

    // returns the new observation space at the end of the function.
    public abstract ArrayList<Domain> reset();

    // returns {observationSpace : ArrayList<Domain>, reward : int, done : boolean, info : String}
    public abstract ArrayList step(int idAction);

    // depending of your problem, you might want to display your environment.
    public abstract void render();
}
