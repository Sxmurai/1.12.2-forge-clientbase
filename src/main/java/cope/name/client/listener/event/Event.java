package cope.name.client.listener.event;

import cope.name.client.ClientBase;

public abstract class Event {
	protected boolean cancelled = false;
	
	public abstract boolean isCancellable();
	
	public void setCancelled(boolean state) {
		cancelled = state;
	}
	
	public boolean isCancelled() {
		return cancelled;
	}
	
	public void cancel() {
		if (!isCancellable()) {
			ClientBase.LOGGER.warn("Tried to cancel event {} when not cancellable!", getClass().getSimpleName());
			return;
		}
		
		setCancelled(!cancelled);
	}
}