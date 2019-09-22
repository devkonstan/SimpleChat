package commands;

public class ChatCommandFactory {
    public ChatCommand create(SupportedCommands commandType) {
        switch (commandType) {
            case LOGIN:
                return new Login();
            case REGISTER:
                return new Register();
            case ADD_CHANNEL:
                return new AddChannel();
            case JOIN_CHANNEL:
                return new JoinChannel();
            case CHANGE_USERNAME:
                return new ChangeUsername();
            case UNKNOWN:
            default:
                throw new IllegalStateException("Unrecognized command");
        }
    }
}
