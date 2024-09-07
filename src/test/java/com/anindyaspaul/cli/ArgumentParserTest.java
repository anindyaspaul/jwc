package com.anindyaspaul.cli;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArgumentParserTest {

    private Option optX;
    private Option optZ;
    private Option flagC;
    private Option flagW;
    private Options options;

    @BeforeEach
    void setUp() {
        optX = new Option("x", true, null);
        optZ = new Option("z", true, null);
        flagC = new Option("c", false, null);
        flagW = new Option("w", false, null);
        options = new Options.Builder()
                .with(optX)
                .with(optZ)
                .with(flagC)
                .with(flagW)
                .build();
    }

    @Test
    void parseValidArguments() {

        CommandLine commandLine = ArgumentParser.parse(new String[]{"-c", "-x", "blabla", "fileName"}, options);

        String optXValue = commandLine.getOptionValue(optX);
        String optZValue = commandLine.getOptionValue(optZ);
        Boolean flagCValue = commandLine.hasOption(flagC);
        Boolean flagWValue = commandLine.hasOption(flagW);
        String inputFile = commandLine.getInputFile();

        Assertions.assertThat(optXValue).isEqualTo("blabla");
        Assertions.assertThat(optZValue).isNull();
        Assertions.assertThat(flagCValue).isEqualTo(true);
        Assertions.assertThat(flagWValue).isEqualTo(false);
        Assertions.assertThat(inputFile).isEqualTo("fileName");
    }

    @Test
    void parseInvalidArguments() {
        // Unknown option
        Assertions.assertThatThrownBy(
                () -> ArgumentParser.parse(new String[]{"-cc", "-xx", "blabla", "fileName"}, options)
        ).isInstanceOf(IllegalArgumentException.class);


        // Missing argument for option
        Assertions.assertThatThrownBy(
                () -> ArgumentParser.parse(new String[]{"-c", "-x"}, options)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}