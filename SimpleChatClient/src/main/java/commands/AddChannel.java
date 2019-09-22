package commands;

import com.beust.jcommander.Parameter;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddChannel extends ChatCommand {
    @Parameter(required = true)
    private String channelname;
    @Parameter(names = {"--private"})
    private boolean isPrivate;
}
