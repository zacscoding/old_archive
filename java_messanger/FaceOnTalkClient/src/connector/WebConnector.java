package connector;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import request.Request;
import serverinfo.ServerInfo;

/*
 *  웹 서버 연결 시 Connector
 */
public class WebConnector {
   private WebConnector(){}   
   public static Request connect(Request req) {	
      Socket socket =  null;
      Request response = null;
      try{
    	 //connect
    	 socket = new Socket(ServerInfo.IP,ServerInfo.WEB_PORT);
         //socket.connect(new InetSocketAddress(ServerInfo.IP,ServerInfo.WEB_PORT));
         
         //out (client -> server)
         ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));         
         oos.writeObject(req);
         oos.flush();
         
         //in (server -> client)
         ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
         response = (Request)ois.readObject();
      }catch(IOException e){
    	  e.printStackTrace();
         if(socket!=null && !socket.isClosed())
            try{socket.close();}catch(IOException ignored){}
      }catch(ClassNotFoundException e){
    	  
      }
      return response;   
   }   
}
