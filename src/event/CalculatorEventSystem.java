package event;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import log.Log;

public class CalculatorEventSystem {
	private static final CalculatorEventSystem instance = new CalculatorEventSystem();
	
	private Map<Class<? extends CalculatorEvent>, List<CalculatorEventHandler<? extends CalculatorEvent>>> eventTypeToHandlerList;
	
	private CalculatorEventSystem() {
		eventTypeToHandlerList = new HashMap<Class<? extends CalculatorEvent>, List<CalculatorEventHandler<? extends CalculatorEvent>>>();
	}
	
	public static CalculatorEventSystem getInstance() {
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends CalculatorEvent> void dispatchEvent(T event) {
		if (!eventTypeToHandlerList.containsKey(event.getClass())) {
			Log.out.println("CalculatorEventSystem: dispatched event " + event.getClass() + ", but nobody handled it");
			return;
		}
		
		Log.out.println("CalculatorEventSystem: handling event " + event.getClass());
		
		List<CalculatorEventHandler<? extends CalculatorEvent>> handlers = eventTypeToHandlerList.get(event.getClass());
		
		for (int i = 0; i < handlers.size(); i++) {
			CalculatorEventHandler<? extends CalculatorEvent> h = handlers.get(i);
			((CalculatorEventHandler<T>)h).handle(event);
			if (h.isOneShot()) {
				handlers.remove(i--);
			}
		}
		
		if (handlers.isEmpty()) {
			eventTypeToHandlerList.remove(event.getClass());
		}
	}
	
	public <T extends CalculatorEvent> void registerEventHandler(Class<T> c, CalculatorEventHandler<T> h) {
		if (!eventTypeToHandlerList.containsKey(c)) {
			eventTypeToHandlerList.put(c, new ArrayList<CalculatorEventHandler<? extends CalculatorEvent>>());
		}
		
		Log.out.println("CalculatorEventSystem: registering handler " + h.getClass().getName() + " for event " + c.getName());
		
		eventTypeToHandlerList.get(c).add(h);
	}
	
	public <T extends CalculatorEvent> void unregisterEventHandler(Class<T> c, CalculatorEventHandler<T> h) {
		if (!eventTypeToHandlerList.containsKey(c)) {
			Log.err.println("CalculatorEventSystem: TRIED TO UNREGISTER " + c.getName() + " EVENT HANDLER, BUT IT WAS NOT REGISTERED");
			return;
		}
		
		Log.out.println("CalculatorEventSystem: unregistering handler " + h.getClass().getName() + " for event " + c.getName());
		
		eventTypeToHandlerList.get(c).remove(h);
	}
}