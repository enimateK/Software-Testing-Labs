package vv.spoon;

import spoon.MavenLauncher;
import vv.spoon.processor.LogProcessor;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("No project to instrument.");
            System.exit(1);
        }

        File projectToInstrument = new File(args[0]);

        MavenLauncher mavenLauncher = new MavenLauncher(projectToInstrument.getAbsolutePath(), MavenLauncher.SOURCE_TYPE.ALL_SOURCE);
        mavenLauncher.addProcessor(new LogProcessor());
        mavenLauncher.setSourceOutputDirectory(new File(projectToInstrument, "spoonedSources"));
        mavenLauncher.setBinaryOutputDirectory(new File(projectToInstrument, "spoonedBinaries"));

        mavenLauncher.getEnvironment().setShouldCompile(true);
        mavenLauncher.run();
    }
}
