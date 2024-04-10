package restaurant.repositories;

import restaurant.models.client.Client;
import restaurant.models.waiter.Waiter;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class ClientRepository implements Repository<Client> {

    private Map<String, Client> clients;
    @Override
    public Collection<Client> getCollection() {
        return Collections.unmodifiableCollection(clients.values());
    }

    @Override
    public void add(Client entity) {
        clients.put(entity.getName(), entity);
    }

    @Override
    public boolean remove(Client entity) {
        if (clients.containsKey(entity.getName())) {
            clients.remove(entity.getName());
            return true;
        }
        return false;
    }

    @Override
    public Client byName(String name) {
        if (clients.containsKey(name)) {
            return clients.get(name);
        }
        return null;
    }
}
