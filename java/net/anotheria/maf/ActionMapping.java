package net.anotheria.maf;

import java.util.HashMap;
import java.util.Map;

public class ActionMapping {
	
	private String path;
	private String type;
	private Map<String, ActionForward> forwards;
	
	public ActionMapping(String aPath, String aType, ActionForward... someForwards){
		path = aPath;
		type = aType;
		forwards = new HashMap<String, ActionForward>();
		if (someForwards!=null)
			for (ActionForward f : someForwards)
				forwards.put(f.getName(), f);
		
	}
	
	public ActionForward findForward(String name){
		return forwards.get(name);
	}
	
	public String getPath(){
		return path;
	}
	
	public String getType(){
		return type;  
	}
}
