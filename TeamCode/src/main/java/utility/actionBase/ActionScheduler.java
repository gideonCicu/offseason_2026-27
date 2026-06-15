package utility.actionBase;

import java.util.ArrayList;

public class ActionScheduler {
    private final ArrayList<Action> actions = new ArrayList<>();
    void schedule(Action action){
        actions.add(action);
    }

    void update(){
        for (Action action : actions){
            if (!action.isStarted()) action.start();

            if (!action.isFinished()) action.update();

            if (action.isFinished()) action.stop();
        }
        actions.removeIf(Action::isFinished);
    }
}
