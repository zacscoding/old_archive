package chat.anonymity;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class Room_Manager {
	//전체 접속자!!
	public static Vector<Client_Connection> allUserList = new Vector<Client_Connection>();
	
	/*
	 *  접속자!! 이부분은 쪽지나 1:1 대화를 위해 필요로 한다.
	 *  대화명으로 접속정보를 가져와야 하기 때문에 Map(아이디, 접속정보) 형식으로 등록한다.
	 */
	public static Map<String, Client_Connection> connectedUser = new Hashtable<String, Client_Connection>();
	
	/*
	 * 아래는 방을 만들고 지울때 사용하는 맵이다.
	 * 등록은 put(방이름, 방정보를 가진 벡터) 제거할때는 remove(방이름)으로 사용한다.
	 */
	public static Map<String, Room> roomMap = new Hashtable<String, Room>();
	
//	/*
//	 * 1:1대화를 요청한 유저들의 커넥션.
//	 */
//	public static Vector<Client_Connection> user = new Vector<Client_Connection>();
//	
	/*-----------------------------------------------------
	 * 			방을 만들고 Map에 집어 넣기.
	 *-----------------------------------------------------*/
	public static void makeRoom(String roomName){
		Room newRoom = new Room(roomName);
		roomMap.put(roomName, newRoom);
	}
	/*-----------------------------------------------------
	 * 			방이름을 리턴.
	 *-----------------------------------------------------*/
	public static Room getRoom(String roomName){		
		return roomMap.get(roomName);
	}
	/*-----------------------------------------------------
	 * 			방을 지운다. remove(방이름)
	 *-----------------------------------------------------*/
	public static void removeRoom(String roomName){
		roomMap.remove(roomName);
	}
	/*-----------------------------------------------------
	 *	접속하는 유저를 connectedUser(Map)에 put시킨다. 
	 *-----------------------------------------------------*/
	public static void addallUserList(String username, Client_Connection client){
		connectedUser.put(username, client);
	}
	/*-----------------------------------------------------
	 *	접속 종료시 connectedUser(Map)에서 remove한다.
	 *-----------------------------------------------------*/
	public static void removeallUSerList(String username){
		connectedUser.remove(username);
	}
	/*-----------------------------------------------------
	 * 			ID-List를 가져오는 부분.
	 *-----------------------------------------------------*/	
	public static String getIDlist(){
		Set<String> lists = connectedUser.keySet();			//키셋을 가져온다.
		String data = lists.toString();						//String으로 변환
		String list = data.substring(1, data.length()-1);	//[ 와 ]를 빼고.
		String[] userlists = list.split(", ");				// aaa, bbb 의 구분은 , 이므로 잘라낸다.
		String sendlist = "";
		for(int i =0; i<userlists.length; i++){
			sendlist += userlists[i]+"/";					//유저들을 추가
		}
		return sendlist;
	}

	/*-----------------------------------------------------
	 * 	방에 관한 정보를 리턴한다.
	 *-----------------------------------------------------*/
	public static String getRoomList(){
		StringBuffer sb = new StringBuffer();
		for(Room room : roomMap.values()){
			sb.append( room.getRoomName() + "/");
		}
		return sb.toString(); 
	}
	/*-----------------------------------------------------
	 * 맵이기 때문에 이름만 입력하면 자동적으로 대화명에 
	 * 맞는 접속정보(키)가 나오게 된다.	
	 *-----------------------------------------------------*/
	private static Client_Connection findUser(String userName){
		return connectedUser.get(userName);
	}
}
