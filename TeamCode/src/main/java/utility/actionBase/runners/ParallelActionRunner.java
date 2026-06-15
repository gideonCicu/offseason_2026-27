package utility.actionBase.runners;

import utility.actionBase.AbstractAction;

import utility.actionBase.AbstractAction;
import utility.actionBase.Action;

public class ParallelActionRunner extends AbstractAction {
    private final Action[] actions;

    public ParallelActionRunner(Action... actions) {
        this.actions = actions;
    }

    @Override
    public boolean isInterruptible() {
        if (!interruptible) {
            return false;
        }

        for (Action action : actions) {
            if (!action.isInterruptible()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void start() {
        super.start();

        for (Action action : actions) {
            action.start();
        }
    }

    @Override
    public void update() {
        boolean allFinished = true;

        for (Action action : actions) {

            if (!action.isFinished()) {
                action.update();
            }

            if (action.isFinished()) {
                action.stop();
            }

            allFinished &= action.isFinished();
        }

        finished = allFinished;
    }
}
