import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.codehaus.jackson.map.util.JSONPObject;

import java.io.*;

public class ReadConstellation {

    public static void main(String[] args){
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try{
            //fileInputStream = new FileInputStream("/Users/mac/desktop/tx");
            fileInputStream = new FileInputStream("/Users/mac/desktop/tx");
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String str="";
            JSONObject json = null;
;            String str_2="";
            while ((str = bufferedReader.readLine()) != null){
                if (!str.contains("null")){
                    System.out.println(str);
                    json = new JSONObject();
                    json = JSONObject.parseObject(str);
                    str_2 += "update t_constellation_info set phone = '"+String.valueOf(json.get("phone")) + "' where openid = '"+String.valueOf(json.get("openId"))+"';\n";

                }


            }

            System.out.println(str_2);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
                inputStreamReader.close();
                bufferedReader.close();

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
