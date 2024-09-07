package com.anindyaspaul.cli;

import java.util.HashMap;
import java.util.Map;

public class Options {
    private final Map<String, Option> optionsMap;

    private Options() {
        optionsMap = new HashMap<>();
    }

    private void add(Option option) {
        if (optionsMap.containsKey(option.name())) {
            throw new IllegalArgumentException("Option already exists: " + option.name());
        }
        optionsMap.put(option.name(), option);
    }

    public Option getOption(String name) {
        return optionsMap.get(name);
    }

    public static class Builder {
        private final Options options;

        public Builder() {
            options = new Options();
        }

        public Builder with(Option option) {
            options.add(option);
            return this;
        }

        public Options build() {
            return options;
        }
    }
}
