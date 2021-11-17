import java.util.ArrayList;

public abstract class Agent {
    protected int numDim;
    protected Integer[] pos;

    Agent(int numDim) {
        this.numDim = numDim;

        pos = new Integer[numDim];
        for (int i = 0; i < numDim; i++) {
            pos[i] = 0;
        }
    }

    public static Integer[] idToAction(int id) {
        Integer[] action = {0,0};
        if (id == 0) return action;
        // left
        else if (id == 1) action[0] = -1;
        // right
        else if (id == 2) action[0] = 1;
        // down
        else if (id == 3) action[1] = -1;
        // up
        else if (id == 4) action[1] = 1;

        return action;
    }

    public Integer[] getPosition() {
        return this.pos;
    }

    public boolean move(int id) {
        this.pos = Agent.idToAction(id);
        return true;
    }
}
