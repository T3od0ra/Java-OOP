package restaurant.models.waiter;

import restaurant.models.orders.TakenOrders;

public interface Waiter {
    String getName();

    TakenOrders getTakenOrders();

    int getEfficiency();

    boolean canWork();


    void work();
}
