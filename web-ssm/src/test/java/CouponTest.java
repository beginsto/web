import pers.jess.template.common.httpclient.HttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CouponTest {


    public static void main(String[] args){
        StringBuffer str = new StringBuffer();

        //str.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
        str.append("<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:q0='http://service/'");
        str.append(" xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'");
        str.append("<soapenv:Body>");
        str.append("<opr_in>");
        str.append("<channel_info>");
        str.append("<channel_id>1010</channel_id>");
        str.append("<channel_pass>123456</channel_pass>");
        str.append("</channel_info>");
        str.append("<content_in>");
        str.append("<user_id>15905836605</user_id>");
        str.append("<home_area>573</home_area>");
        str.append("<user_type>01</user_type>");
        str.append("<pay_type>1</pay_type>");
        str.append("<islimit_channel>0</islimit_channel>");
        str.append("<type>1</type>");
        str.append("<app_code>");
        str.append("</app_code>");
        str.append("<content_sub_in>");
        str.append("<type_code>sdfasdfasdf/type_code>");
        str.append("<number_value>1</number_value>");
        str.append("</content_sub_in>");
        str.append("</content_in>");
        str.append("<req_seq></req_seq>");
        str.append("<service_id>100101</service_id>");
        str.append("</opr_in>");
        str.append("</soapenv:Body></soapenv:Envelope>");


        String data ="<?xml version='1.0' encoding='GBK'?>"
                    + "<opr_in>"
                    + " <channel_info>"
                    + "  <channel_id>573</channel_id>"
                    + "  <channel_pass>573</channel_pass>"
                    + " </channel_info>"
                    + " <content_in>"
                    + "  <user_id>15001136602</user_id>"
                    + "  <home_area>573</home_area>"
                    + "  <user_type>01</user_type>"
                    + "  <pay_type>1</pay_type>"
                    + "   <islimit_channel>0</islimit_channel>"
                    + "   <type>1</type>"
                    + "  <app_code></app_code>"
                    + "  <content_sub_in>"
                    + "   <type_code>20171101093748</type_code>"
                    + "   <number_value>1</number_value>"
                    + "  </content_sub_in>"
                    + " </content_in>"
                    + " <req_seq>1352963020015</req_seq>"
                    + "<service_id>100101</service_id>"
                    + "</opr_in>";

        String result = "";
        /*HttpURLConnection connection = null;
        OutputStream os = null;
        InputStream is = null;
        try {
            String DsrWebService = "http://20.26.17.115:9500/yhqmanage/client/httpService";
            URL url = new URL(DsrWebService);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type","text/xml;charset=utf-8");
            os = connection.getOutputStream();
            os.write(data.getBytes("utf-8"));
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                is = connection.getInputStream();
//System.out.println("return " + is.available());
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) > 0) {
                    result += new String(buffer, 0, len);
                }
            }
            if (result != null && !"".equals(result)) {
                int beginIndex = result.indexOf("<return>");
                int endIndex = result.indexOf("</return>");
                result = result.substring(beginIndex + 8, endIndex);
            }
        } catch (Exception e) {
            if (connection != null) {
                connection.disconnect();
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e1) {
// TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e2) {
// TODO Auto-generated catch block
                    e2.printStackTrace();
                }
            }
        }
//结果输出
        System.out.println(result);*/


        try {
            HttpClient httpClient = new HttpClient();
            System.out.println(data);
             result  = httpClient.doPost("http://20.26.17.115:9500/yhqmanage/client/httpService",data,"UTF-8");
            //result  = httpClient.doPost("http://dqxnfw-ec.yw.zj.chinamobile.com:9400/yhqinterface/client/httpService",data,"UTF-8");
            System.out.println("响应："+result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
