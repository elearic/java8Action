package ericzz.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.GZIPInputStream;

public class HttpUtil {

    private static RequestConfig requestConfig = null;

    static {
        requestConfig = RequestConfig.custom().setConnectTimeout(300000)
                .setConnectionRequestTimeout(300000).build();
    }

    public static String doPost(String url, Map<String, String> headers,
                                Map<String, String> params) {
        String body = "";
        try {
            //创建httpclient对象
            CloseableHttpClient client = HttpClients.createDefault();

            //创建post方式请求对象
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = Lists.newArrayList();
            if (params != null) {
                Set<Entry<String, String>> entrySet = params.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs, "utf-8");//解决中文乱码问题    
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/x-www-form-urlencoded;charset=utf-8");
            httpPost.setEntity(entity);
            if (headers != null) {
                Set<Entry<String, String>> entrySet = headers.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            //执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = client.execute(httpPost);
            //获取结果实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                //按指定编码转换结果实体为String类型
                body = EntityUtils.toString(responseEntity, "utf-8");
            }
            EntityUtils.consume(entity);
            //释放链接
            response.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    /**
     * 发送get请求
     *
     * @param url 路径
     * @return
     */
    public static JSONObject doGet(String url, Map<String, String> headerParam) {
        // get请求返回结果
        JSONObject jsonResult = null;
        CloseableHttpClient client = HttpClients.createDefault();
        // 发送get请求
        HttpGet request = new HttpGet(url);
        List<Header> headerLinkedList = Lists.newLinkedList();
        Set<Entry<String, String>> entrySet = headerParam.entrySet();
        if (!entrySet.isEmpty()) {
            Iterator<Entry<String, String>> it = entrySet.iterator();
            while (it.hasNext()) {
                Entry<String, String> entry = it.next();
                String key = entry.getKey();
                String value = entry.getValue();
                Header header = new BasicHeader(key, value);
                headerLinkedList.add(header);
            }
        }
        Header[] newHeaders = new Header[headerLinkedList.size()];
        request.setHeaders(headerLinkedList.toArray(newHeaders));
        request.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = client.execute(request);

            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 读取服务器返回过来的json字符串数据
                HttpEntity entity = response.getEntity();
                String strResult = EntityUtils.toString(entity, "utf-8");
                // 把json字符串转换成json对象
                jsonResult = JSONObject.parseObject(strResult);
            } else {
                System.out.println("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            System.out.println("get请求提交失败:" + url + e);
        } finally {
            request.releaseConnection();
        }
        return jsonResult;
    }

    /**
     * 从网络Url中下载文件
     *
     * @param urlStr
     * @param fileName
     * @throws IOException
     */
    public static void doIoStream(String urlStr, String fileName) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(10 * 60 * 1000);
        conn.setReadTimeout(10 * 60 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent",
                "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        try (InputStream inputStream = conn.getInputStream();
             GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[4096];
            int len = 0;
            while ((len = gzipInputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            //获取自己数组
            byte[] getData = bos.toByteArray();
            //文件保存位置
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("下载失败");
        }
        System.out.println("info:" + url + " download success");
    }

}
