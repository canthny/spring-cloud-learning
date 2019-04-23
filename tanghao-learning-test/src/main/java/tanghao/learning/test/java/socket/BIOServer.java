package tanghao.learning.test.java.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author： Canthny
 * @Description： 阻塞服务端
 * @Date： Created in 2019/3/21 0:26
 */
public class BIOServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7788);
            Socket clientConn = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientConn.getInputStream()));
            PrintWriter out = new PrintWriter(clientConn.getOutputStream());
            String request,response;
            while ((request=in.readLine())!=null){
                if ("stop".equals(request)) {
                    break;
                }
                response = processRequest(request);
                out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String processRequest(String request){
        System.out.println("get request and process");
        return "success";
    }
}
