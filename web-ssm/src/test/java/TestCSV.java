import pers.jess.template.ad.model.AdUser;
import pers.jess.template.ad.service.AdService;

import javax.batch.api.chunk.listener.RetryReadListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestCSV {

    public  static List<AdUser> list = new ArrayList<>();

    public static void readCVS(String url) throws IOException{
        FileReader fr = new FileReader(new File(url));
        BufferedReader br = new BufferedReader(fr);

        String line = br.readLine();
        while (line != null){
            String[] strs = line.split(",");
            AdUser u = new AdUser();
            u.setId(Integer.parseInt(strs[0]));
            u.setPhone(strs[3]);
            u.setRealname(strs[2]);
            list.add(u);
            line = br.readLine();
        }
        br.close();
        fr.close();
    }

    public static void getUserId(String inputstr, String outstr) throws IOException{
        //字符流
        FileReader fr = null;
        FileWriter fw = null;
        //缓冲流
        BufferedReader br = null;
        BufferedWriter bw = null;

        fr = new FileReader(new File(inputstr));
        br = new BufferedReader(fr);   //扩容，类似加水管
        fw = new FileWriter(outstr);
        bw = new BufferedWriter(fw);

        String line = br.readLine();    //逐行复制
        while (line != null) {
            for (AdUser u : list){
                if (line.contains(u.getRealname())) {
                    if (u.getId() == 38){
                        if (line.contains("费袁杰")){
                            continue;

                        }else{
                            line = line.replace(u.getRealname(), ""+u.getId());
                        }
                    }else{
                        line = line.replace(u.getRealname(), ""+u.getId());
                    }

                }
            }

            bw.write(line);
            bw.newLine();  //换行输出
            line = br.readLine();
        }

        bw.close();
        fw.close();
        br.close();
        fr.close();
        System.out.println("完毕............");
    }

    public static void main(String[] args) throws IOException {
        readCVS("/Users/mac/Desktop/list/list_1.csv");
        getUserId("/Users/mac/Desktop/list/list_2.csv","/Users/mac/Desktop/list/list_2_new.csv");
    }
}
