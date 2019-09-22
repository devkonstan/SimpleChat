package trialServer;

import java.io.IOException;

public class ServerContinousDemo {
    public static void main(String[] args) throws IOException {
        SampleServerContinous server = new SampleServerContinous();
        server.startServer(50000);
    }
}
