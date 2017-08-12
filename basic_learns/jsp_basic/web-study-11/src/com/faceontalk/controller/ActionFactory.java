package com.faceontalk.controller;

import com.faceontalk.controller.action.*;

public class ActionFactory {
	//singleton
	private static ActionFactory instance = new ActionFactory();
	private ActionFactory(){}
	public static ActionFactory getInstance() {
		if(instance == null)
			instance = new ActionFactory();
		return instance;
	}		
	public Action getAction(String command) {
		Action action = null;
		if(command==null || command.isEmpty())
			action = new BoardListAction();
		else if(command.equals("board_list"))
			action = new BoardListAction();
		else if(command.equals("board_write_form"))
			action = new BoardWriteFormAction();
		else if(command.equals("board_write"))
			action = new BoardWriteAction();
		else if(command.equals("board_view"))
			action = new BoardViewAction();
		else if(command.equals("board_check_pass_form"))
			action = new BoardCheckPassFormAction();
		else if(command.equals("board_check_pass"))
			action = new BoardCheckPassAction();
		else if(command.equals("board_update_form"))
			action = new BoardUpdateFormAction();
		else if(command.equals("board_update"))
			action = new BoardUpdateAction();
		else if(command.equals("board_delete"))
			action = new BoardDeleteAction();
		return action;		
	}
}
