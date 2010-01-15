package net.anotheria.maf.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Marker interface used to define form backing bean
 */
public class RequestMapBean implements FormBean, Map<String, Object> {
	private Map<String, Object> params = new HashMap<String, Object>();

	@Override
	public int size() {
		return params.size();
	}

	@Override
	public boolean isEmpty() {
		return params.isEmpty();
	}

	@Override
	public boolean containsKey(Object o) {
		return params.containsKey(o);
	}

	@Override
	public boolean containsValue(Object o) {
		return params.containsValue(o);
	}

	@Override
	public Object get(Object o) {
		return params.get(o);
	}

	@Override
	public Object put(String s, Object o) {
		return params.put(s, o);
	}


	@Override
	public Object remove(Object o) {
		return params.remove(o);
	}

	@Override
	public void putAll(Map map) {
		params.putAll(map);
	}

	@Override
	public void clear() {
		params.clear();
	}

	@Override
	public Set keySet() {
		return params.keySet();
	}

	@Override
	public Collection values() {
		return params.values();
	}

	@Override
	public Set entrySet() {
		return params.entrySet();
	}
}