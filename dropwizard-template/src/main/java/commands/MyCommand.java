package commands;

import io.dropwizard.cli.Command;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;

/**
 * @Author Hariom Yadav
 * @create 5/24/2021
 * https://www.dropwizard.io/en/latest/manual/core.html#man-core-commands
 */
public class MyCommand extends Command {//a.1. create command class
    public MyCommand() {
        super("name", "description");//we can execute this command : java -jar <jarfile> hello dropwizard
    }

    @Override
    public void configure(Subparser subparser) {
        //command line option
        subparser.addArgument("-u", "--user")
                .dest("user")
                .type(String.class)
                .required(true)
                .help("The user of the program");

    }

    @Override
    public void run(Bootstrap<?> bootstrap, Namespace namespace) throws Exception {
        System.out.println("Hello "+namespace.getString("user"));
    }
}
