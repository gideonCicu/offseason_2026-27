package utility.threads;

import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadedCalculationRunner {
    public static long safety = 5;

    private static final CopyOnWriteArrayList<
            ThreadedCalculation<?>> threadedCalculations =
            new CopyOnWriteArrayList<>();

    private static Thread runnerThread;

    private ThreadedCalculationRunner() {}

    public static void add(ThreadedCalculation<?> calculation) {
        threadedCalculations.add(calculation);
    }

    public static synchronized Thread start() {

        if (runnerThread != null && runnerThread.isAlive()) {
            return runnerThread;
        }

        runnerThread = new Thread(() -> {

            while (!Thread.currentThread().isInterrupted()) {

                for (ThreadedCalculation<?> calculation :
                        threadedCalculations) {

                    calculation.run();
                }

                try {
                    Thread.sleep(safety);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        runnerThread.setName("ThreadedCalculationRunner");
        runnerThread.start();

        return runnerThread;
    }

    public static synchronized void stop() {

        if (runnerThread != null) {
            runnerThread.interrupt();
            runnerThread = null;
        }
    }

}
