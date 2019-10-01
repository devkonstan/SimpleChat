package network;

public interface DisconnectObservable {
    void subscribe(DisconnectObserver observer);
    void notifyDisconnected(ChatClient client);
}
