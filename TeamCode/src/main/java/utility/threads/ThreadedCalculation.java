package utility.threads;

public class ThreadedCalculation<T> {

    private T lastResult;
    private final long refreshRate;
    private final Calculation<T> calculation;

    public final Object objectLock = new Object();

    private long lastUpdate = System.currentTimeMillis();

    public ThreadedCalculation(
            T initialValue,
            long refreshRate,
            Calculation<T> calculation
    ) {
        this.lastResult = initialValue;
        this.refreshRate = refreshRate;
        this.calculation = calculation;
    }

    public synchronized void run() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastUpdate < refreshRate) {
            return;
        }

        lastUpdate = currentTime;

        synchronized (objectLock) {
            lastResult = calculation.calculate(lastResult);
        }
    }

    public T getValue() {
        synchronized (objectLock) {
            return lastResult;
        }
    }

    @FunctionalInterface
    public interface Calculation<T> {
        T calculate(T previous);
    }
}
