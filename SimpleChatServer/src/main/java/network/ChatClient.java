package network;

public interface ChatClient extends DisconnectObservable {
    void sendMessage(String message);
    void disconnect();
    boolean isOnline();
//    void changeCurrentChannel(String channelName);
    String getCurrentChannelName();
}