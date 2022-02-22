package cope.name.client.listener;

import cope.name.client.features.ToggleableFeature;
import cope.name.client.listener.event.Event;
import cope.name.util.internal.Wrapper;

public abstract class Listener<F extends ToggleableFeature, E extends Event> implements Wrapper {
	protected final F feature;
	
	public Listener(F feature) {
		this.feature = feature;
	}
	
	public abstract void call(E event);
	
	public F getFeature() {
		return feature;
	}
}
