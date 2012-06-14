package tao.tracer;

import java.net.HttpURLConnection;  
import java.net.SocketTimeoutException;  
import java.net.URL;  
import java.net.URLConnection;  
import java.util.concurrent.CountDownLatch;  
  
import org.apache.log4j.Logger;  
  
public class CallHttpRequest implements Runnable {  
    private static Logger log = Logger.getLogger(CallHttpRequest.class);  
    public  static int successRequest = 0;  
    public  static int failRequest = 0;  
    public  static int timeOutRequest = 0;  
    private final String hostString = "http://localhost:9090/mysuper";  
    private String port;  
    private String puductPartURL;  
  
    private CountDownLatch begin;  
    private CountDownLatch end;  
  
    CallHttpRequest(String port, String puductPartURL, CountDownLatch begin,  
            CountDownLatch end) {  
        this.port = port;  
        this.puductPartURL = puductPartURL;  
        this.begin = begin;  
        this.end = end;  
    }  
  
    private String makeFullURL() {  
        return hostString + port + puductPartURL;  
  
    }  
    private  static synchronized void incrementSuccessCount(){  
         successRequest++;  
    }  
      
    private  static synchronized void incrementFailCount(){  
         failRequest++;  
    }  
      
    private static synchronized void incrementTimeOutCount(){  
         timeOutRequest++;  
    }  
      
    @Override  
    public void run() {  
        String urlStr = makeFullURL();  
        URL url;  
        try {  
            begin.await();  
            url = new URL(urlStr);  
            URLConnection URLconnection = url.openConnection();  
            HttpURLConnection httpConnection = (HttpURLConnection) URLconnection;  
            httpConnection.setConnectTimeout(3000);  
            int responseCode = httpConnection.getResponseCode();  
            if (responseCode == HttpURLConnection.HTTP_OK) {  
                incrementSuccessCount();  
            } else {  
                incrementFailCount();  
            }  
        } catch (SocketTimeoutException e) {  
            incrementTimeOutCount();  
            log.error(e.getMessage(), e);  
        } catch (Exception e) {  
            log.error(e.getMessage(), e);  
        } finally {  
            end.countDown();  
        }  
  
    }  
  
}  
