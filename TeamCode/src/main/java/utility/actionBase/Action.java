package utility.actionBase;

public interface Action {
     boolean isStarted();
     boolean isFinished();
     boolean isInterruptible();

     void start();
     void update();
     void stop();

     void setStarted(boolean started);
     void setFinished(boolean finished);

     default void interrupt() {
          if (isInterruptible()) {
               setFinished(true);
          }
     }
}