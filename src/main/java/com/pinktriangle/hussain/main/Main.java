package com.pinktriangle.hussain.main;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pinktriangle.hussain.gui.CreateISOGUI;

public class Main {
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Option guiOption = OptionBuilder.withArgName("GUI Option").hasArg()
				.withDescription("Which user interface would you like to load, c for creating ISOs and e for extracting ISOs")
				.create("gui");
		Options options = new Options();
		options.addOption(guiOption);
		
		CommandLineParser parser = new GnuParser();
		try{
			CommandLine line = parser.parse(options, args);
			if(line.hasOption("gui"))
			{
				String optionValue = line.getOptionValue("gui");
				if(optionValue.equals("c"))
				{
					LOG.trace("The c option was passed in, showing the create ISO GUI now");
					new CreateISOGUI().setVisible(true);
				}
				else if(optionValue.equals("e"))
				{
					LOG.trace("The e option was passed in, showing the extract ISO GUI now");
				}
				else
				{
					LOG.trace("The correct GUI option was not passed in");
				}
			}
			else
			{
				HelpFormatter help = new HelpFormatter();
				help.printHelp("Help", options);
			}
		}
		catch(ParseException exception){
			LOG.error("There was an error when parsing the" +
					" command line arguments", exception);
		}
	}

}
