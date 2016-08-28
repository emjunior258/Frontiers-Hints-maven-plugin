package mz.hi.maven.js.frontiers.suggestions;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

@Mojo(name = "frontiers-js", defaultPhase = LifecyclePhase.PREPARE_PACKAGE)
public class JSGenerator extends AbstractMojo {

    @Parameter(readonly = true, defaultValue = "${project}")
    private MavenProject project;

    public void execute() throws MojoExecutionException, MojoFailureException {


        String directory = project.getBuild().getOutputDirectory();
        String path = directory+"/indexable.suggestions.js";

        File file = new File(path);
        if(file.exists())
            file.delete();

        try {

            file.createNewFile();

        }catch (IOException ex){

            throw new MojoFailureException("Failed to create suggestions file at "+path);

        }


        try {

            FileOutputStream fout = new FileOutputStream(file);
            PrintStream ps = new PrintStream(fout);
            ps.println("console.log('hello');");
            ps.flush();
            ps.close();


        }catch (IOException ex){

            throw new MojoFailureException("Failed to write on suggestions file : "+path);

        }


    }
}
