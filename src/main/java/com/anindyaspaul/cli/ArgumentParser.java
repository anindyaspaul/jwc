package com.anindyaspaul.cli;

public class ArgumentParser {
    public static CommandLine parse(String[] args, Options options) {
        CommandLine commandLine = new CommandLine();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            Option option = getOption(arg, options);
            if (option != null) { // arg is an option
                if (option.hasArg()) {
                    // take the arg
                    if (i+1 == args.length) {
                        throw new IllegalArgumentException("Missing argument for option: " + option.name());
                    }
                    String optionValue = args[i+1];
                    commandLine.addOptionValue(option, optionValue);
                    i++;
                }
                else {
                    commandLine.addOptionValue(option, null);
                }
            }
            else { // arg is not an option
                if (i+1 == args.length) { // This is the last item, so it must be the inputFile
                    commandLine.setInputFile(arg);
                } else {
                    throw new IllegalArgumentException("Invalid option: " + arg);
                }
            }
        }

        return commandLine;
    }

    private static Option getOption(String arg, Options options) {
        if (!arg.startsWith("-")) {
            return null;
        }
        String optionName = arg.substring(1);
        return options.getOption(optionName);
    }
}
