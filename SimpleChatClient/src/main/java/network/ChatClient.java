package network;

public interface ChatClient {
    void connect(String ip, int port);
    void disconnect();
    void sendMessage(String message);
    boolean isOnline();
}
