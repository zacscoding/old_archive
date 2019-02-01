package controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import controller.feed.ModifyFeedController;
import controller.feed.ReadFeedController;
import controller.feed.RemoveFeedController;
import controller.feed.WriteFeedController;
import controller.member.ConfirmIdController;
import controller.member.FindUserInfoController;
import controller.member.JoinController;
import controller.member.ModifyInfoController;
import controller.member.SearchFriendController;
import controller.schedule.ReadScheduleController;
import controller.schedule.RemoveScheduleController;
import controller.schedule.WriteScheduleController;

public class ActionFactory {
	// singleton
	private static ActionFactory instance = null;	
	public static ActionFactory getInstance() throws RuntimeException {
		if (instance == null)
			instance = new ActionFactory();
		return instance;
	}	
	
	//variables
	Map<String,WebCommandController> commandsMap;
	private ActionFactory(){
		commandsMap = new HashMap<String,WebCommandController>(16,0.95f);
		initProperties();
	}	
	
	private void initProperties() throws RuntimeException {
		Properties prop = new Properties();
		try(FileReader fis = new FileReader(new File("commands.properties"))) {
			prop.load(fis);
		} catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Iterator keyIter = prop.keySet().iterator();
		while(keyIter.hasNext()) {
			String command = (String) keyIter.next();			
			String handlerClassName = prop.getProperty(command);
			System.out.println("초기화 command : "+command+"\t,className : "+handlerClassName);
			try {
				Class<?> handlerClass = Class.forName(handlerClassName);
				WebCommandController handlerInst = (WebCommandController) handlerClass.newInstance();
				if(handlerInst == null) {
					System.out.println(command+"클래스 널");
				}
				commandsMap.put(command, handlerInst);
			} catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}		
		System.out.println("해쉬맵 사이즈 : "+commandsMap.size());
	}

	public WebCommandController getAction(String type) {
		
		WebCommandController controller = null;		
		controller = commandsMap.get(type);
		if(controller ==null)
			System.out.println("controller null");
		return controller;			

//		////////////////////////////////////////////
//		// 회원 및 친구 관련
//		///////////////////////////////////////////
//		if (type.equals("join")) { // 회원가입
//			// System.out.println("가입요청해석");
//			controller =  new JoinController();
//		} else if (type.equals("modify")) { // 회원 정보 수정
//			// System.out.println("정보수정 요청 해석");
//			controller =  new ModifyInfoController();
//		} else if (type.equals("searchfriend")) { // 친구찾기
//			// System.out.println("친구찾기해석");
//			controller =  new SearchFriendController();
//		} else if (type.equals("confirmid")) {
//			// System.out.println("아이디 중복 확인 해석");
//			controller =  new ConfirmIdController();
//		} else if (type.equals("finduser")) {
//			controller =  new FindUserInfoController();
//		}
//
//		////////////////////////////////////////////
//		// 피드 관련
//		///////////////////////////////////////////
//		else if (type.equals("writefeed")) {// 쓰기
//			System.out.println("게시글 쓰기 해석");
//			controller =  new WriteFeedController();
//		} else if (type.equals("readfeed")) {// 읽기
//			System.out.println("게시글 읽기 해석");
//			controller =  new ReadFeedController();
//		} else if (type.equals("modifyfeed")) {// 수정
//			System.out.println("게시글 수정 해석");
//			controller =  new ModifyFeedController();
//		} else if (type.equals("removefeed")) {// 삭제
//			System.out.println("게시글 삭제 해석");
//			controller =  new RemoveFeedController();
//		}
//
//		////////////////////////////////////////////
//		// 스케줄 관련
//		///////////////////////////////////////////
//		else if (type.equals("writeschedule")) { // 쓰기
//			// System.out.println("스케줄 쓰기 해석 완료");
//			controller =  new WriteScheduleController();
//		} else if (type.equals("readschedule")) { // 읽기
//			System.out.println("스케줄 읽기 해석 완료");
//			controller =  new ReadScheduleController();
//		} else if (type.equals("modifyschedule")) { // 수정
//			// System.out.println("스케줄 수정 해석 완료");
//			// controller =  new ModifyScheduleController();
//		} else if (type.equals("removeschedule")) { // 삭제
//			//System.out.println("스케줄 지우기 해석 완료");
//			controller =  new RemoveScheduleController();
//		}
//		return controller;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("ActionFactory소멸");
	}
	
	
	
}
