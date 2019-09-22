package commands;

import com.beust.jcommander.Parameter;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Login extends ChatCommand {
    @Parameter(names = {"-u", "--username"}, required = true)
    private String username;
    @Parameter(names = {"-p", "--password"}, required = true, password = true)
    private String password;
}
