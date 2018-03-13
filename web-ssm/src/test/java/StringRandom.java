import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

public class StringRandom {

    private static BufferedWriter out = null;

    //生成随机数字和字母,
    public static void getStringRandom(int length,String coupdesc) throws IOException {
        out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/Users/mac/Desktop/StringRandom.txt", true)));  //以追加的方式写入到指定的文件
        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                //int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                int temp = 65;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        System.out.println(val+"\t"+coupdesc+"\n");
        out.write(val+"\t"+coupdesc+"\n");
        out.close();
    }

    public static void  main(String[] args) {

        String[] str = {"100M移动光宽带","10GB流量","50元话费","5GB流量","1GB流量"};
        Integer[] iarr = {50,100,200,500,10000};
        StringRandom test = new StringRandom();
        try {
         /*   for(int i = 0;i < 50;i++){
                getStringRandom(6,"100M移动光宽带");
            }
            for(int i = 0;i < 100;i++){
                getStringRandom(6,"10GB流量");
            }
            for(int i = 0;i < 200;i++){
                getStringRandom(6,"50元话费");
            }
            for(int i = 0;i < 500;i++){
                getStringRandom(6,"5GB流量");
            }*/
            for(int i = 0;i < 9900;i++){
                getStringRandom(6,"1GB流量");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}