package restaurant.models.client;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

import static restaurant.common.ExceptionMessages.CLIENT_NAME_NULL_OR_EMPTY;

public class ClientImpl implements Client{

    private String name;
    private List<String> clientOrders;

    public ClientImpl(String name) {
        this.setName(name);
        this.clientOrders = new ArrayList<>();
    }
    @Override
    public Collection<String> getClientOrders() {
        return this.clientOrders;
    }
    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(CLIENT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }


}
