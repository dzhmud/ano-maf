 package net.anotheria.maf.action;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.anotheria.maf.builtin.ShowMappingsAction;

/**
 * Configuration of the Framework. This class contains all mappings the framework will react on.
 * @author another
 *
 */
public final class ActionMappings {
	
	/**
	 * Action aliases.
	 */
	private static final ConcurrentMap<String, String> aliases = new ConcurrentHashMap<String, String>();
	/**
	 * Action mappings.O
	 */
	private static final ConcurrentMap<String, ActionMapping> mappings = new ConcurrentHashMap<String, ActionMapping>();
	
	static{
		aliases.put("maf/showMappings", "/maf/showMappings");
		mappings.put("/maf/showMappings", new ActionMapping("/maf/showMappings", ShowMappingsAction.class.getName()));
	}

	/**
	 * Adds a mapping.
	 * @param path
	 * @param type
	 * @param commands
	 */
	public static void addMapping(String path, String type, ActionCommand... commands){
		mappings.put(path, new ActionMapping(path, type, commands));
	}

	/**
	 * Adds a mapping.
	 * @param path
	 * @param type
	 * @param forwards
	 */
	public static void addMapping(String path, String type, ActionForward... forwards){
		mappings.put(path, new ActionMapping(path, type, forwards));
	}
	
	public static void addForward(String actionPath, String forwardPath){
		addMapping(actionPath, ForwardAction.class, new ActionForward("forward", forwardPath));
	}
	
	/**
	 * Adds a mapping.
	 * @param path
	 * @param type
	 * @param commands
	 */
	public static void addMapping(String path, Class<? extends Action> type, ActionCommand... commands){
		mappings.put(path, new ActionMapping(path, type.getName(), commands));
	}

	/**
	 * Adds an 1.0 style mapping.
	 * @param path
	 * @param type
	 * @param forwards
	 */
	public static void addMapping(String path, Class<? extends Action> type, ActionForward... forwards){
		mappings.put(path, new ActionMapping(path, type.getName(), forwards));
	}

	/**
	 * Adds an alias.
	 * @param sourcePath
	 * @param targetPath
	 */
	public static void addAlias(String sourcePath, String targetPath){
		aliases.put(sourcePath, targetPath);
	}
	
	public static ActionMapping findMapping(String actionPath){
		System.out.println("%%% SEARCHING "+actionPath+" in "+mappings);
		String alias = aliases.get(actionPath);
		if (alias!=null)
			return findMapping(alias);
		return mappings.get(actionPath);
	}
	
	public static Map<String, String> getAliases(){
		HashMap<String, String> ret = new HashMap<String, String>();
		ret.putAll(aliases);
		return ret;
	}
	
	//TODO this method allows indirect modification of action mappings, it should probably instead clone the mappings (TOFIX).
	public static Map<String, ActionMapping> getMappings(){
		HashMap<String, ActionMapping> ret = new HashMap<String, ActionMapping>();
		ret.putAll(mappings);
		return ret;
	}
	
	private ActionMappings(){
	}
}
