package edu.upenn;

import org.apache.commons.cli.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        //parsing commandline arguments
//        Options options = new Options();
//        options.addOption("v", "verbose", false, "print detailed output for development purposes.");
//        Option opt_input_file_list = OptionBuilder.withArgName("input_file_list").hasArg().isRequired().withDescription("specify the list of input files and covariates. Refer to README for format details.").create("input_file_list");
//        Option opt_output_dir = OptionBuilder.withArgName( "output_dir" ).hasArg().isRequired().withDescription("specify the location where temporary files and final results are stores.").create("output_dir");
//
//        Option opt_method_in = OptionBuilder.withArgName( "method_in" ).hasArg().isRequired().withDescription("specify the method with which the input files are generated, method = \"cufflinks\" ").create("method_in");
//        Option opt_method_out = OptionBuilder.withArgName( "method_out" ).hasArg().isRequired().withDescription("specify the method for which the output files are generated, method = \"edgeR\" or \"DESeq\". ").create("method_out");
//
//        options.addOption(opt_input_file_list);
//        options.addOption(opt_output_dir);
//        options.addOption(opt_method_in);
//        options.addOption(opt_method_out);
//
//        CommandLine line = null;
//
//        try {
//            CommandLineParser parser = new GnuParser();
//            line = parser.parse(options, args );
//        } catch( ParseException exp ) {
//            HelpFormatter formatter = new HelpFormatter();
//            formatter.printHelp( "java -jar DEConverter.jar", options );
//            System.exit(0);
//        }
//
//
//        String input_list_fn = line.getOptionValue("input_file_list");
//        String output_dir = line.getOptionValue("output_dir");
//        String method_in = line.getOptionValue("method_in");
//        String method_out = line.getOptionValue("method_out");
//
//        Boolean verbose = false;
//
//        if (line.hasOption("v")){
//            verbose=true;
//        }
//
//
//        //initialize buffered writer for log file
//        Path output_path = Paths.get(output_dir);
//        String log_file = output_path.resolve("deconverter.log").toString();
//        Logger deconverter_logger = new Logger(verbose,log_file);
//        deconverter_logger.log_message("Starting logging to file: " + log_file);

        IsoformInfo iso = new IsoformInfo("1\tprotein_coding\tCDS\t69091\t70005\t.\t+\t0\tgene_id \"ENSG00000186092\"; transcript_id \"ENST00000335137\"; exon_number \"1\"; gene_name \"OR4F5\"; gene_biotype \"protein_coding\"; transcript_name \"OR4F5-001\"; protein_id \"ENSP00000334393\";");



    }
}
