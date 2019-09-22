package trialServer;

import java.io.IOException;

public class ServerAsyncDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        SampleServerAsync server = new SampleServerAsync();
        server.startServer(50000);
        Thread.sleep(10000);
        server.shutdown();
    }
}
