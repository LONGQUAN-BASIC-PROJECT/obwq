package cn.obwq.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guoxing.zgx on 2017/1/11.
 */
public class FeatureUtil {


    public static final String  SPLIT_TOKEN_MIDDLE = "->";   //K->V#
    public static final String  SPLIT_TOKEN_END = "#";  //KV分割符

    public static Map<String,String> initAttrMap(String attr){
        Map<String,String> attrMap = new HashMap<String, String>();
        if (StringUtils.isNotBlank(attr)) {
            String[] featureArray = attr.split(SPLIT_TOKEN_END);
            if (featureArray != null && featureArray.length > 0) {
                for (String feature : featureArray) {
                    if (StringUtils.isNotBlank(feature)) {
                        String[] aKeyAndValue = new String[2];
                        int index = feature.indexOf(SPLIT_TOKEN_MIDDLE);
                        if (index > 0) {
                            aKeyAndValue[0] = feature.substring(0, index);
                            aKeyAndValue[1] = feature.substring(index + SPLIT_TOKEN_MIDDLE.length());
                            if (aKeyAndValue != null && aKeyAndValue.length > 0) {
                                String key = aKeyAndValue[0];
                                String value = aKeyAndValue[1];
                                if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
                                    attrMap.put(key, value);
                                }
                            }
                        }
                    }

                }
            }
        }
        return attrMap;
    }


    public static String resetAttrMap(Map<String,String> attr){
        StringBuffer sb = new StringBuffer("");
        if(attr == null || attr.size() == 0){
            return sb.toString() ;
        }
        for(Map.Entry<String, String> en : attr.entrySet()){
            String aValue = en.getValue();
            String key = en.getKey() ;
            sb.append(key);
            sb.append(SPLIT_TOKEN_MIDDLE);
            sb.append(aValue);
            sb.append(SPLIT_TOKEN_END);
        }
        return sb.toString();
    }




}
