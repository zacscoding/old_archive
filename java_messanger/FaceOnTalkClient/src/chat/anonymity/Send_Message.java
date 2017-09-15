package chat.anonymity;

import java.io.IOException;


public class Send_Message {
	public static void chattingStart(String consoleData){
		System.out.println(consoleData);
		try{
			if("".equals(consoleData)){
				consoleData = " ";
				anonyChatFrame.sendMsg(MsgInfo.CHAT,consoleData);		//그냥 엔터를 치더라도 값이 나와야 함.
			}else if("EXIT".equals(consoleData)){		//exit는 종료
				anonyChatFrame.sendMsg(MsgInfo.EXIT,null);
			}else{										//그 외는 대화이므로..
				anonyChatFrame.sendMsg(MsgInfo.CHAT,consoleData);
			}
		}catch(Exception e1){
			try {anonyChatFrame.networkWriter.close();} catch (IOException e) {}
			try {anonyChatFrame.networkReader.close();} catch (IOException e) {}
			try {anonyChatFrame.socket.close();       } catch (IOException e) {}
		}
	}
}
