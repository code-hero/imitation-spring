package com.slc.framework.container;

public enum ConfigurationFactory {
    INSTANCE;
    private Configuration configuration = new Configuration();

    public Configuration loadConfiguration() {
        return ConfigurationFactory.INSTANCE.configuration;
    }
}
