package com.coz.coin;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VTUtil {



    public static String reqgetString(String reqStr,String defaultVal) {
        if (reqStr != null && !reqStr.equals("")) {
            return reqStr;
        }
        return defaultVal;
    }

    public static String getValueString(String value, String data){
        String result = "";
        int startIndx = data.indexOf("[");
        int endIndx =  data.indexOf("]");
        data = data.substring(startIndx+5,endIndx-5);

        String dataArr[] = data.split(",");
        for (int i=0; i<dataArr.length;i++){
            String ayrac[] = dataArr[i].split(":");
            if(ayrac[0].substring(0,ayrac[0].length()-1).equals(value)){
                result = ayrac[1].substring(1,ayrac[1].length()-1);
                break;
            }
        }
        return result;
    }


    public static String currentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date()).toString();
    }




}
