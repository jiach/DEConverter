package edu.upenn;

/**
 * Created by cheng on 1/18/15.
 */
public class IncompleteAttributesException extends Exception {
    public IncompleteAttributesException(String att_name_missing) {
        super("Attribute "+att_name_missing+" is missing from the input gtf!");
    }
}
