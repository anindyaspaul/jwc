package com.anindyaspaul;

import com.anindyaspaul.cli.ArgumentParser;
import com.anindyaspaul.cli.CommandLine;
import com.anindyaspaul.cli.Option;
import com.anindyaspaul.cli.Options;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Option byteCountsOpt = new Option("c", false, "print the byte counts");

        Options options = new Options.Builder()
                .with(byteCountsOpt)
                .build();
        CommandLine commandLine = ArgumentParser.parse(args, options);
    }
}