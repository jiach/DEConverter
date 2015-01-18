/**
 * Created by cheng on 1/7/15.
 */

package edu.upenn;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

/**
 * Created by cheng on 1/7/15.
 */
public class CufflinksParser {

    Map<String, ArrFPKM> dict_arr_fpkm = new TreeMap<String, ArrFPKM>();
    String[] uniq_group = null;

    public CufflinksParser() {
    }

    public CufflinksParser(String[] fn, Logger log){
        this.read_in_file(fn, log);
    }

    public void read_in_file(String[] fn, Logger log){
        try {
            for (int i = 0; i < fn.length; i++) {
                log.log_message("processing file: "+fn[i]);
                BufferedReader in = new BufferedReader(new FileReader(fn[i]));
                String line;
                String[] line_tokens;
                in.readLine();
                while ((line=in.readLine())!=null){
                    line_tokens = line.split("\t");
                    if (this.dict_arr_fpkm.containsKey(line_tokens[0])) {
                        this.dict_arr_fpkm.get(line_tokens[0]).append(Double.parseDouble(line_tokens[9]),Double.parseDouble(line_tokens[10]),Double.parseDouble(line_tokens[11]));
                    }else{
                        this.dict_arr_fpkm.put(line_tokens[0],new ArrFPKM());
                        this.dict_arr_fpkm.get(line_tokens[0]).append(Double.parseDouble(line_tokens[9]),Double.parseDouble(line_tokens[10]),Double.parseDouble(line_tokens[11]));
                    }

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public void trim_isoforms(Double min_fpkm_mean, int sample_num){

        for(Iterator<Map.Entry<String, ArrFPKM>> it = this.dict_arr_fpkm.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, ArrFPKM> entry = it.next();
            if ((entry.getValue().get_mean_fpkm()<min_fpkm_mean) || (entry.getValue().get_sample_num()!=sample_num)) {
                it.remove();
            }
        }
    }

    public void trim_isoform(Double max_cv, String[] group_var){
        Set<String> unique_group_var_set = new HashSet<String>(Arrays.asList(group_var));
        String[] unique_group_var = unique_group_var_set.toArray(new String[unique_group_var_set.size()]);
        this.uniq_group = unique_group_var;

        for(Iterator<Map.Entry<String, ArrFPKM>> it = this.dict_arr_fpkm.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, ArrFPKM> entry = it.next();
            Map<String, Double> cur_cv = entry.getValue().get_cv(group_var, unique_group_var);

            Boolean remove_isoform = false;
            for (Double aCV_val : cur_cv.values()){
                if(aCV_val.isInfinite() || aCV_val.isNaN() || (aCV_val>1)){
                    remove_isoform = true;
                }else{
                    entry.getValue().set_status_ok(max_cv);
                }
            }

            if (remove_isoform){
                it.remove();
            }
        }

    }

    public Boolean get_ok_status(String isoform_name){
        return this.dict_arr_fpkm.get(isoform_name).status_ok;
    }

//    public String get_formatted_output_str(String method){
//        if (method.equals("")){
//
//        }
//
//    }



    public long get_num_isoforms(){
        return(dict_arr_fpkm.size());
    }

    //TODO: public get_cv(String[] group_cov) {}
}

