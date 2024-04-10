package restaurant.models.orders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TakenOrdersImpl implements TakenOrders{

    private List<String> orders;

    public TakenOrdersImpl() {
        this.orders = new ArrayList<>();
    }
    @Override
    public Collection<String> getOrdersList() {
        return null;
    }
}
