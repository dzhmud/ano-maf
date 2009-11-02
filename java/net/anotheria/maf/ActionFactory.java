package net.anotheria.maf;

import java.util.HashMap;
import java.util.Map;

public final class ActionFactory {
	
	private static final Map<String, Action> instances = new HashMap<String, Action>();
	
	static Action getInstanceOf(String actionType) throws ActionFactoryException{
		Action action = instances.get(actionType);
		if (action!=null)
			return action;
		synchronized(instances){
			action = instances.get(actionType);
			if (action!=null)
				return action;
			try{
				action = (Action) Class.forName(actionType).newInstance();
			}catch(Exception e){
				throw new ActionFactoryException(e);
			}
			instances.put(actionType, action);
		}
		return action;
	}
}
