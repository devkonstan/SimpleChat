package network;

public interface DisconnectObservable {
    void subscribe(DisconnectObserver observer);
    void unsubscribe(DisconnectObserver observer);
    void notifyDisconnected(ChatClient client);
}
