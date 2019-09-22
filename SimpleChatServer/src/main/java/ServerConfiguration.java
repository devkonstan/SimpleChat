import network.ChannelRepository;
import network.InMemoryChannelRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfiguration {
    @Bean
    public ChannelRepository channelRepository() {
        return new InMemoryChannelRepository();
    }
}