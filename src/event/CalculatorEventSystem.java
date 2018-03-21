package event;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import log.Log;

public class CalculatorEventSystem {
	private Map<Class<? extends CalculatorEvent>, List<CalculatorEventHandler<? extends CalculatorEvent>>> eventTypeToHandlerList;
	
	@SuppressWarnings("unchecked")
	public <T extends CalculatorEvent> void dispatchEvent(T event) {
		if (!eventTypeToHandlerList.containsKey(event.getClass())) {
			return;
		}
		
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
		
		eventTypeToHandlerList.get(c).add(h);
	}
	
	public <T extends CalculatorEvent> void unregisterEventHandler(Class<T> c, CalculatorEventHandler<T> h) {
		if (!eventTypeToHandlerList.containsKey(c)) {
			Log.err.println("CalculatorEventSystem: TRIED TO UNREGISTER " + c.getName() + " EVENT HANDLER, BUT IT WAS NOT REGISTERED");
			return;
		}
		
		eventTypeToHandlerList.get(c).remove(h);
	}
}