package utility.actionBase;

public interface Action {
     boolean isStarted();
     boolean isFinished();
     boolean interruptable();

     void start();

     void update();
     void stop();

     default void inturrpt(){
         if (interruptable()) stop();
     }


}
