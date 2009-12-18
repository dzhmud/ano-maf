package net.anotheria.maf;

import java.util.HashMap;
import java.util.Map;

public class ActionMapping {
	/**
	 * Path for the action to react to.
	 */
	private String path;
	/**
	 * Classname of the action class.
	 */
	private String type;
	/**
	 * Map with action forwards.
	 */
	private Map<String, ActionForward> forwards;
	
	public ActionMapping(String aPath, String aType, ActionForward... someForwards){
		path = aPath;
		type = aType;
		forwards = new HashMap<String, ActionForward>();
		if (someForwards!=null)
			for (ActionForward f : someForwards)
				forwards.put(f.getName(), f);
		
	}
	
	/**
	 * Returns a stored forward for the given forward name.
	 * @param name
	 * @return
	 */
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
