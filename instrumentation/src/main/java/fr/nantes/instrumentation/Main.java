package fr.nantes.instrumentation;

import spoon.Launcher;
import spoon.MavenLauncher;
import fr.nantes.instrumentation.processor.LogProcessor;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("No project to instrument.");
            System.exit(1);
        }

        File projectToInstrument = new File(args[0]);

        Launcher launcher = new MavenLauncher(projectToInstrument.getAbsolutePath(), MavenLauncher.SOURCE_TYPE.ALL_SOURCE);
        launcher.addProcessor(new LogProcessor());
        launcher.setSourceOutputDirectory(new File(projectToInstrument, "spoonedSources"));
        launcher.setBinaryOutputDirectory(new File(projectToInstrument, "spoonedBinaries"));

        launcher.getEnvironment().setShouldCompile(true);
        launcher.run();
    }
}
