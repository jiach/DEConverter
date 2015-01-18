package edu.upenn;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import ubic.basecode.util.StringUtil;

import java.util.*;

/**
 * Created by cheng on 1/18/15.
 */
public class IsoformInfo {
    String chr = null;
    String transcript_id = null;
    String transcript_name = null;
    String gene_id = null;
    String gene_name = null;
    int exon_numbers = 0;
    Map<Integer,Long[]> exons = new TreeMap<Integer, Long[]>();

    List<Long[]> CDs = new ArrayList<Long[]>();
    Boolean has_CDs = false;

    public IsoformInfo(String line){
        String[] line_tokens = line.split("\t");

        if (this.transcript_id == null) {
            this.chr = line_tokens[0];
            String[] attribute_tokens =line_tokens[8].split(";");
            Map<String, String> attribute_maps = new HashMap<String, String>();
            for (String att_str:attribute_tokens){
                String att_name = att_str.trim().split(" ")[0];
                String att_value = StringUtils.substringBetween(att_str,"\"", "\"");
                attribute_maps.put(att_name,att_value);
            }

            this.transcript_id = attribute_maps.get("transcript_id");
            this.transcript_name = attribute_maps.get("transcript_name");
            this.gene_id = attribute_maps.get("gene_id");
            this.gene_name = attribute_maps.get("gene_name");
        }

        if (line_tokens[2].equals("CDS")) {
            this.add_CDS(line_tokens);
        } else if (line_tokens[2].equals("exon")){
            this.add_exon(line_tokens);
        } else {
            return;
        }
    }

    public void add_exon(String[] line){
        Long[] exon_pos = new Long[] {Long.parseLong(line[3]), Long.parseLong(line[4])};
        this.exons.put(Integer.parseInt(this.extract_attribute(line[8],"exon_number")), exon_pos);
        this.exon_numbers++;
    }

    public void add_CDS(String[] line){
        Long[] cds_pos = new Long[] {Long.parseLong(line[3]), Long.parseLong(line[4])};
        this.CDs.add(cds_pos);
        this.has_CDs = true;
    }

    protected String extract_attribute(String att_section, String attribute_name){

        String[] attribute_tokens = att_section.split(";");
        Map<String, String> attribute_maps = new HashMap<String, String>();
        for (String att_str:attribute_tokens){
            String att_name = att_str.trim().split(" ")[0];
            String att_value = StringUtils.substringBetween(att_str,"\"", "\"");
            attribute_maps.put(att_name,att_value);
        }

        return attribute_maps.get(attribute_name);
    }
}
