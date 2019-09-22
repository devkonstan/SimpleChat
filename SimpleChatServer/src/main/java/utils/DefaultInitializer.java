package utils;

import network.Channel;
import network.ChannelRepository;
import network.PublicChannel;
import settings.ChannelSettings;

public class DefaultInitializer implements ChatInitializer {
    private final ChannelRepository channelRepository;

    public DefaultInitializer(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public void initialize() {
        Channel generalChannel = new PublicChannel(ChannelSettings.DEFAULT_CHANNEL_NAME);
        channelRepository.add(generalChannel);
    }
}
