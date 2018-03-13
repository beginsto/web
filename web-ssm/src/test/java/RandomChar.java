import java.util.Random;

public class RandomChar {

    private static Random ran = new Random();
    private final static int delta = 0x9fa5 - 0x4e00 + 1;

    public static char getRandomHan() {
        return (char)(0x4e00 + ran.nextInt(delta));
    }

    /**public static void main(String[] args) {
        for(int i=0;i<100;i++)
            System.out.print(getRandomHan());
    }*/

    public static void main(String[] args) {

        Random rand = new Random();
        // 4E00(十六进制)转换成 19968(十进制), 4E00是汉字在Unicode编码的起始位置
        final int start = 19968;

        // 9FA5(十六进制)转换成 40869(十进制),9FA5是汉字在Unicode编码的终止位置
        final int end = 40869;

        //因nextInt()方法产生的随机数是从0到指定的参数之间（不包含），所以加了1
        int temp = rand.nextInt(end+1);

        //如果temp==0,那么会显示为汉字“一”,不会出现别的字符
        if(temp < start) temp += start;

        //输出随机生成的汉字
        System.out.println(Integer.toHexString(temp));
    }





}
