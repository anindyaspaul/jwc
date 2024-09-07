package com.anindyaspaul.cli;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandLine {
    private final Map<String, String> optionValues;
    private String inputFile;

    public CommandLine() {
        this.optionValues = new HashMap<>();
    }

    public Boolean hasOption(Option option) {
        return optionValues.containsKey(option.name());
    }

    public String getOptionValue(Option option) {
        return optionValues.get(option.name());
    }

    public void addOptionValue(Option option, String value) {
        if (optionValues.containsKey(option.name())) {
            throw new IllegalArgumentException("Option value already exists: " + option.name());
        }
        optionValues.put(option.name(), value);
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getInputFile() {
        return inputFile;
    }
}
