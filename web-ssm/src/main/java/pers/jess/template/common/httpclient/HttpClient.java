package pers.jess.template.common.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/7.
 */
@Service()
public class HttpClient {


    static int socketTimeout = 10000;// 请求超时时间
    static int connectTimeout = 10000;// 传输超时时间

    Logger log = Logger.getLogger(HttpClient.class);
    // 创建HttpClient对象
    @Autowired(required = false)
    private CloseableHttpClient httpClient;

    // 请求信息的配置
    @Autowired(required = false)
    private RequestConfig requestConfig;

    /**
     * 执行Get请求
     *
     * @param url
     * @return 请求到的内容
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public String doGet(String url) throws URISyntaxException,
            ClientProtocolException, IOException {
        return doGet(url, null);
    }

    /**
     * 执行Get请求
     *
     * @param url
     * @param params 请求中的参数
     * @return 请求到的内容
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public String doGet(String url, Map<String, Object> params)
            throws URISyntaxException, ClientProtocolException, IOException {
        //实例化httpclient
        httpClient = HttpClients.createDefault();
        // 定义请求的参数
        URI uri = null;
        if (params != null) {
            URIBuilder builder = new URIBuilder(url);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.addParameter(entry.getKey(),
                        String.valueOf(entry.getValue()));
            }
            uri = builder.build();
        }

        // 创建http GET请求
        HttpGet httpGet = null;
        if (uri != null) {
            httpGet = new HttpGet(uri);
        } else {
            httpGet = new HttpGet(url);
        }
        // 设置请求参数
        httpGet.setConfig(this.requestConfig);

        // 请求的结果
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取服务端返回的数据,并返回
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    /**
     *
     * @param url
     * @param params  请求中的参数
     * @return 请求到的内容
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String doPost(String url, Map<String, Object> params)
            throws URISyntaxException, ClientProtocolException, IOException {
        //实例化httpclient
        httpClient = HttpClients.createDefault();
        // 设置post参数
        List<NameValuePair> parameters = null;
        // 构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = null;

        // 定义请求的参数
        if (params != null) {
            // 设置post参数
            parameters = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                // 添加参数
                parameters.add(new BasicNameValuePair(entry.getKey(), String
                        .valueOf(entry.getValue())));
            }
            // 构造一个form表单式的实体
            formEntity = new UrlEncodedFormEntity(parameters);
        }

        // 创建http GET请求
        HttpPost httpPost = null;
        if (formEntity != null) {
            httpPost = new HttpPost(url);
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(formEntity);
            // 伪装浏览器请求
            httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httpPost.setHeader("Accept-Encoding", "gzip, deflate, sdch");
            httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httpPost.setHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36");
        } else {
            httpPost = new HttpPost(url);
            // 伪装浏览器请求
            httpPost.setHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
        }
        // 设置请求参数
        httpPost.setConfig(this.requestConfig);

        // 请求的结果
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取服务端返回的数据,并返回
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    /**
     *
     * @param url 请求中的参数
     * @return 请求到的内容
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String doPost(String url)  {
        try {
            return doPost(url, null);
        }catch (URISyntaxException e){
            e.printStackTrace();
            log.error("HttpClient请求URISyntaxException异常...Exception［" + e.toString() +"］");
            return null;
        }catch (ClientProtocolException e){
            e.printStackTrace();
            log.error("HttpClient请求ClientProtocolException异常...Exception［" + e.toString() +"］");
            return null;
        }catch (IOException e){
            e.printStackTrace();
            log.error("HttpClient请求IOException异常...Exception［" + e.toString() +"］");
            return null;
        }catch (Exception e){
            e.printStackTrace();
            log.error("HttpClient请求异常...Exception［" + e.toString() +"］");
            return null;
        }

    }


    public String doPost(String url,String jsonstr,String charset){
        //log.info("进入httpClient请求...");
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);

            requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectionRequestTimeout(connectTimeout).build();
            httpPost.setConfig(requestConfig);

            StringEntity se = new StringEntity(jsonstr,charset);
            httpPost.setEntity(se);

            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

}
