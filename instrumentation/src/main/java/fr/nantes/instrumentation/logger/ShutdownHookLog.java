package fr.nantes.instrumentation.logger;

public class ShutdownHookLog extends Thread {

    public void run() {
        LogWriter.writeLog();
    }
}
