import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.persistence.criteria.From;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadText {


    public static void main(String[] args){
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        JSONObject json = null;
        JSONArray arr =null;
        List<String[]> list = new ArrayList<>();

        try {
            String sb_1 = "";
            String sb_2 = "";
            fileInputStream = new FileInputStream("/Users/mac/desktop/bus.txt");
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((sb_1 = bufferedReader.readLine()) != null){
                sb_2 = sb_1 ;
                String[] a = sb_2.split("\\t");
                list.add(a);
            }
            String[] str={"南湖区","嘉兴市","海盐县","海宁市","平湖市","桐乡市","嘉善县","all"};
            String[] str_1 = {"nh","xz","hy","hn","ph","tx","js","all"};
          //  for(int i=0; i<8;i++){

                bufferedWriter = new BufferedWriter(new FileWriter("/Users/mac/desktop/bus.json"));
                arr = new JSONArray();
                for(int j=0;j<list.size();j++){
                    json = new JSONObject();
                   /* if(!str[i].equals("all")){
                        if (list.get(j)[0].equals(str[i])){
                            System.out.println(list.get(j)[0]+"/"+list.get(j)[1]+"/"+list.get(j)[2]);
                            json.put("area",list.get(j)[0]);
                            json.put("orgName",list.get(j)[1]);
                            //json.put("addr",list.get(j)[2]);
                            json.put("type",list.get(j)[2]);
                            arr.add(json);
                        }
                    }else{
                        json.put("area",list.get(j)[0]);
                        json.put("orgName",list.get(j)[1]);
                        //json.put("addr",list.get(j)[2]);
                        json.put("type",list.get(j)[2]);
                        arr.add(json);
                    }*/
                    json.put("city",list.get(j)[0]);
                    json.put("area",list.get(j)[1]);
                    json.put("name",list.get(j)[2]);
                    json.put("addr",list.get(j)[3]);
                    arr.add(json);

               }

                /*bufferedWriter = new BufferedWriter(new FileWriter("/Users/mac/desktop/all.json"));
                arr = new JSONArray();
                for(int j=0;j<list.size();j++){
                    json = new JSONObject();

                        json.put("area",list.get(j)[0]);
                        json.put("orgName",list.get(j)[1]);
                        json.put("addr",list.get(j)[2]);
                        //json.put("type",list.get(j)[3]);
                        arr.add(json);

                }*/
                bufferedWriter.write(arr.toJSONString());
                bufferedWriter.close();
          //  }


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
