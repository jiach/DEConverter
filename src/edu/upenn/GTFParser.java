package edu.upenn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by cheng on 1/18/15.
 * This class only parses ENSEMBL GTF!
 */
public class GTFParser {
    Map<String, IsoformInfo> map_isoname_to_iso = new TreeMap<String, IsoformInfo>();

    public GTFParser(String filename){
        try {
            BufferedReader gtf_in = new BufferedReader(new FileReader(filename));
            String s = null;
            while ((s = gtf_in.readLine())!=null) {
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
