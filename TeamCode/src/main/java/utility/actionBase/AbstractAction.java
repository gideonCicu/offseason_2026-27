package utility.actionBase;

public abstract class AbstractAction implements Action {

    protected boolean started = false;
    protected boolean finished = false;
    protected boolean interruptible = true;

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public boolean isInterruptible() {
        return interruptible;
    }

    @Override
    public void start() {
        started = true;
    }

    @Override
    public void stop() {}

    @Override
    public void setStarted(boolean started) {
        this.started = started;
    }

    @Override
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public AbstractAction asNonInterruptible() {
        interruptible = false;
        return this;
    }
}