package net.anotheria.maf.action;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Configuration of the Framework. This class contains all mappings the framework will react on.
 * @author another
 *
 */
public final class ActionMappings {
	
	private static final Map<String, String> aliases = new ConcurrentHashMap<String, String>();
	private static final Map<String, ActionMapping> mappings = new ConcurrentHashMap<String, ActionMapping>(); 

	
	public static void addMapping(String path, String type, ActionForward... forwards){
		mappings.put(path, new ActionMapping(path, type, forwards));
	}
	
	public static void addAlias(String sourcePath, String targetPath){
		aliases.put(sourcePath, targetPath);
	}
	
	public static ActionMapping findMapping(String actionPath){
		String alias = aliases.get(actionPath);
		if (alias!=null)
			return findMapping(alias);
		return mappings.get(actionPath);
	}
	
	private ActionMappings(){}
}
