package com.cyx.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient; 
import org.apache.commons.httpclient.HttpMethod; 
import org.apache.commons.httpclient.HttpStatus; 
import org.apache.commons.httpclient.URIException; 
import org.apache.commons.httpclient.methods.GetMethod; 
import org.apache.commons.httpclient.methods.PostMethod; 
import org.apache.commons.httpclient.params.HttpMethodParams; 
import org.apache.commons.httpclient.util.URIUtil; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class HttpRequest {
	 private String cookie;
	    private String resultString="";
	    private List<String> cookieList;

	    /**
	     * �����ṩ����cookie�ķ���
	     *
	     * @param cookie ����Ҫ���õ�cookie�ַ�
	     */
	    public void setCookie(String cookie) {
	        this.cookie = cookie;
	    }

	    /**
	     * �����ṩ�������ַ�
	     *
	     * @return ������
	     */
	    public String getResultString() {
	        return this.resultString;
	    }

	    /**
	     * �����ṩcookie�б�
	     * @return cookie�б�
	     */
	    public List<String> getCookieList() {
	        return cookieList;
	    }


	    /**
	     * �����ṩ�����ķ��ʷ���
	     * @param urlString url����������
	     * @param params  ���ݵĲ��� ��ʽ���£�aa=bb&cc=dd&ee=ff
	     * @param method  ��������ķ�ʽ��ֻ��ȡֵΪ��POST�����ߡ�GET��
	     * @return ����ַ�
	     * @throws Exception no such method to request
	     */
	    public String request(String urlString,String params,String method)throws Exception{
	        System.out.println(urlString);
	        urlString=urlString.replace(" ","");
	        if("POST".equals(method)){
	            return doPost(urlString,params);
	        }else if("GET".equals(method)){
	            return doGet(urlString+"?"+params);
	        }else{
	            throw new NoSuchFieldException("����ʽ����ΪPOST����ΪGET");

	        }

	    }
	    /**
	     * �ṩGET�������
	     *
	     * @param urlString url�ַ�����
	     * @return result ������
	     * @throws Exception
	     */
	    private String doGet(String urlString) throws Exception {
	    System.out.println(urlString);
	        InputStream in = null;
	        this.resultString =null;
	        try {           
	        	URL url = new URL (urlString);
	            HttpsURLConnection conn = (HttpsURLConnection) url
	                    .openConnection();
	            conn.setRequestProperty("Content-type","application/x-www-form-urlencoded");
	            setSSL(conn);
	            setCookie(conn);
	            conn.setDoInput(true);
	            conn.setConnectTimeout(3000);
	            conn.setReadTimeout(3000);
	            conn.connect();
	            getCookie(conn);//��ȡ�����cookie
	            in  = conn.getInputStream();
	            this.resultString = InputStreamToString(in);//���������õ��ַ�
	            in.close();
	            conn.disconnect();
	        } catch (ConnectException e) {
	            throw e;
	        } catch (IOException e) {
	            throw e;
	        } finally {
	            try {
	                in.close();
	            } catch (Exception e) {
	            }
	        }
	        return this.resultString;
	    }
	    /**
	     * �ṩPOST�������
	     * @param urlString url�ַ�����
	     * @param params ������
	     * @return
	     * @throws Exception
	     */
	    private String doPost(String urlString,String params) throws Exception{
	        System.out.println("����post");
	        HttpsURLConnection urlCon = null;
	        String str="";
	        try {
	            urlCon = (HttpsURLConnection) (new URL(urlString)).openConnection();
	            urlCon.setDoInput(true);
	            urlCon.setDoOutput(true);
	            urlCon.setRequestMethod("POST");
	            urlCon.setRequestProperty("Content-type","application/x-www-form-urlencoded");
	            urlCon.setUseCaches(false);
	            setSSL(urlCon);
	            urlCon.setConnectTimeout(3000);
	            urlCon.setReadTimeout(3000);
	            setCookie(urlCon);
	            //����Ϊgbk���Խ������������ʱ��ȡ�����������������
	            urlCon.getOutputStream().write(params.getBytes("utf-8"));
	            urlCon.getOutputStream().flush();
	            urlCon.getOutputStream().close();
	            BufferedReader in = new BufferedReader(new InputStreamReader(
	                    urlCon.getInputStream()));
	            getCookie(urlCon);
	            while ((str = in.readLine()) != null) {
	                this.resultString+=str;
	            }
	            in.close();
	        } catch (Exception e) {
	            System.out.println(e);
	            e.printStackTrace();
	        }
	        return this.resultString;
	    }
	    /**
	     * ������м�¼�ķ���
	     */
	    public void clearAll() {
	        this.cookie = "";
	        this.resultString = "";
	        this.cookieList = null;
	    }
	    /**

	    /**
	     * ��������ת��Ϊ����ΪUTF-8���ַ�
	     *
	     * @param is ������
	     * @return �ַ�
	     * @throws IOException
	     */
	    private String InputStreamToString(InputStream is) throws IOException {
	        if (is != null) {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    is, "UTF-8"));
	            String ret = "";
	            StringBuffer sb = new StringBuffer();
	            while (ret != null) {
	                ret = reader.readLine();
	                if (ret != null && !ret.trim().equals("")) {
	                    sb.append(ret + "\n");
	                }
	            }
	            return sb.toString();
	        }
	        return null;
	    }

	    /**
	     * HTTPS��SSL֤�������
	     *
	     * @param conn url������
	     * @throws Exception
	     */
	    private void setSSL(HttpsURLConnection conn) throws Exception {
	        if (conn != null) {
	            SSLContext sc = SSLContext.getInstance("SSL");
	            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()},
	                    new java.security.SecureRandom());
	            conn.setSSLSocketFactory(sc.getSocketFactory());
	            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
	        }
	    }


	    /**
	     * ���ж�cookie���������������ʱ�����cookie
	     *
	     * @param conn url������
	     */
	    private void setCookie(HttpsURLConnection conn) {
	        if (conn != null && cookie != null && cookie.length() > 0) {
	            conn.setRequestProperty("Cookie", cookie);
	        }
	    }

	    /**
	     * �����ȡ��cookie�б�
	     *
	     * @return ����cookie
	     */
	    private void getCookie(HttpsURLConnection conn) {
	        if (conn != null) {
	            cookieList = conn.getHeaderFields().get("Set-Cookie");
	        }

	    }


	    /********************************
	     * ������֤����������
	     ******************************/
	    private static class TrustAnyTrustManager implements X509TrustManager {
	        public void checkClientTrusted(X509Certificate[] chain, String authType)
	                throws CertificateException {
	        }

	        public void checkServerTrusted(X509Certificate[] chain, String authType)
	                throws CertificateException {
	        }

	        public X509Certificate[] getAcceptedIssuers() {
	            return new X509Certificate[]{};
	        }
	    }

	    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
	        public boolean verify(String hostname, SSLSession session) {
	            return true;
	        }
	    }
	    public static String sendGet(String url, String param) {
	        String result = "";
	        BufferedReader in = null;
	        try {
	            String urlNameString = url + "?" + param;
	            URL realUrl = new URL(urlNameString);
	            // 打开和URL之间的连接
	            URLConnection connection = realUrl.openConnection();
	            // 设置通用的请求属性
	            connection.setRequestProperty("accept", "*/*");
	            connection.setRequestProperty("connection", "Keep-Alive");
	            connection.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            // 建立实际的连接
	            connection.connect();
	            // 获取所有响应头字段
	            Map<String, List<String>> map = connection.getHeaderFields();
	            // 遍历所有的响应头字段
	            for (String key : map.keySet()) {
	                System.out.println(key + "--->" + map.get(key));
	            }
	            // 定义 BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(new InputStreamReader(
	                    connection.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("发送GET请求出现异常！" + e);
	            e.printStackTrace();
	        }
	        // 使用finally块来关闭输入流
	        finally {
	            try {
	                if (in != null) {
	                    in.close();
	                }
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        }
	        return result;
	    }

	    /**
	     * 向指定 URL 发送POST方法的请求
	     * 
	     * @param url
	     *            发送请求的 URL
	     * @param param
	     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	     * @return 所代表远程资源的响应结果
	     */
	    public static String sendPost(String url, String param) {
	        PrintWriter out = null;
	        BufferedReader in = null;
	        String result = "";
	        try {
	            URL realUrl = new URL(url);
	            // 打开和URL之间的连接
	            URLConnection conn = realUrl.openConnection();
	            // 设置通用的请求属性
	            conn.setRequestProperty("accept", "*/*");
	            conn.setRequestProperty("connection", "Keep-Alive");
	            conn.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            // 发送POST请求必须设置如下两行
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            // 获取URLConnection对象对应的输出流
	            out = new PrintWriter(conn.getOutputStream());
	            // 发送请求参数
	            out.print(param);
	            // flush输出流的缓冲
	            out.flush();
	            // 定义BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(
	                    new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("发送 POST 请求出现异常！"+e);
	            e.printStackTrace();
	        }
	        //使用finally块来关闭输出流、输入流
	        finally{
	            try{
	                if(out!=null){
	                    out.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }
	        return result;
	    }    
}
