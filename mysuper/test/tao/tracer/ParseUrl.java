package tao.tracer;

import java.io.BufferedReader;  
import java.io.FileReader;  
import java.util.ArrayList;  
import java.util.concurrent.CountDownLatch;  
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
import org.apache.log4j.Logger;  
  
public class ParseUrl {  
    private static Logger log = Logger.getLogger(ParseUrl.class);  
  
    private static final String[] portArray = new String[] { "1111", "2222",  
            "3333" };  
  
    /*从文件中使用正则解析出url的部分信息，下面正则是将 以/开头的，非空白字符结尾的字符串取出，如“/abcc空格”字符串，\s为任意的空白符  */  
    public static ArrayList<String> fetchUrlFromFile(String str) {  
        ArrayList<String> arrayList = new ArrayList<String>();  
        Pattern urlPattern = Pattern.compile("/[^\\s]+");  
        //Pattern urlPattern = Pattern.compile("/[\\S]+");  
        Matcher matcher = urlPattern.matcher(str);  
        while (matcher.find()) {  
            arrayList.add(matcher.group());  
        }  
        return arrayList;  
    }  
  
    public static void main(String[] args) throws Exception {  
        CountDownLatch begin = new CountDownLatch(1);  
  
//        StringBuilder stringBuilder = new StringBuilder();  
//        String filePath = args[0];  
//        FileReader fr = new FileReader(filePath);  
//        BufferedReader br = new BufferedReader(fr);  
//        while (br.ready()) {  
//            stringBuilder.append(br.readLine());  
//        }  
//        String stringAll = stringBuilder.toString();  
//        ArrayList<String> arrayList = fetchUrlFromFile(stringAll);  
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("/taobao/tracer/logTraceInfo.do");
        int allRequestSize = arrayList.size();  
        log.info("all request size is " + allRequestSize);  
        //设置最大的并发数量为60  
        ExecutorService exec = Executors.newFixedThreadPool(60);  
  
        CountDownLatch end = new CountDownLatch(allRequestSize);  
//      int i = 0;  
        for (String str : arrayList) {  
            exec.execute(new CallHttpRequest(portArray[0], str, begin, end));  
              
            /*如果想测试60个线程并发的访问,发配到同一台服务器上的两个tomcat，就用下面注释掉的代码 
             * if (i % 2 == 0) { 
                exec.execute(new CallHttpRequest(portArray[0], str, begin, end)); 
            } else if (i % 2 == 1) { 
                exec.execute(new CallHttpRequest(portArray[1], str, begin, end)); 
            } */  
//          i++;  
        }  
        long startTime = System.currentTimeMillis();  
        //当60个线程，初始化完成后，解锁，让六十个线程在4个双核的cpu服务器上一起竞争着跑，来模拟60个并发线程访问tomcat  
        begin.countDown();  
  
        try {  
            end.await();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        } finally {  
            log.info("all url requests is done!");  
            log.info("the success size: " + CallHttpRequest.successRequest);  
            log.info("the fail size: " + CallHttpRequest.failRequest);  
            log.info("the timeout size: " + CallHttpRequest.timeOutRequest);  
            double successRate = (double)CallHttpRequest.successRequest / allRequestSize;  
            log.info("the success rate is: " + successRate*100+"%");  
            long endTime = System.currentTimeMillis();  
            long costTime = endTime - startTime;  
            log.info("the total time cost is: " + costTime + " ms");  
            log.info("every request time cost is: " + costTime / allRequestSize  
                    + " ms");  
        }  
        exec.shutdown();  
        log.info("main method end");  
  
    }  
} 