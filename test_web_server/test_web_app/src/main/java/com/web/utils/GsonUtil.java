package com.web.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
	private GsonUtil(){}
	private static Gson gson;    
	
	public static Gson getGsonForToString() {
		if(gson == null)
			gson = new GsonBuilder().serializeNulls().create();
		return gson;
	}

}
