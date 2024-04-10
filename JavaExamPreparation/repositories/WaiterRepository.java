package restaurant.repositories;

import restaurant.models.waiter.Waiter;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class WaiterRepository implements Repository<Waiter>{

    private Map<String, Waiter> waiters;
    @Override
    public Collection<Waiter> getCollection() {
        return Collections.unmodifiableCollection(waiters.values());
    }

    @Override
    public void add(Waiter entity) {
        waiters.put(entity.getName(), entity);
    }

    @Override
    public boolean remove(Waiter entity) {
        if (waiters.containsKey(entity.getName())) {
            waiters.remove(entity.getName());
            return true;
        }
        return false;
    }

    @Override
    public Waiter byName(String name) {
        if (waiters.containsKey(name)) {
            return waiters.get(name);
        }
        return null;
    }
}
