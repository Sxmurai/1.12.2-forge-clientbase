package cope.name.client.listener;

import cope.name.client.listener.event.Event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ListenerHandler {
	private final Set<Listener> listeners = new HashSet<>();
		
	public void dispatch(Event event) {
		for (Listener listener : listeners) {
			if (!listener.getFeature().isToggled()) {
				continue;
			}
			
			Type generic = listener.getClass().getGenericSuperclass();
			if (generic instanceof ParameterizedType) {
				ParameterizedType param = (ParameterizedType) generic;
				
				try {
					if (((Class) param.getActualTypeArguments()[1]).isAssignableFrom(event.getClass())) {
						listener.call(event);
					}
				} catch (Exception ignored) {
					ignored.printStackTrace();
				}
			}
		}
	}
	
	public void register(Listener... all) {
		Arrays.stream(all).forEach(listeners::add);
	}
}
