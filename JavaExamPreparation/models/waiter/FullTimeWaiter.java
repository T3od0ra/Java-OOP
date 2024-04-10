package restaurant.models.waiter;

import restaurant.models.orders.TakenOrders;

//TODO
public class FullTimeWaiter extends BaseWaiter{
    private static final int EFFICIENCY = 8;
    private static final int DECREASE_EFFICIENCY = 1;

    public FullTimeWaiter(String name) {
        super(name, EFFICIENCY);
    }


    @java.lang.Override
    public void work() {
        int newEfficiency = (int)getEfficiency();
        newEfficiency -= DECREASE_EFFICIENCY;
        if ( newEfficiency < 0) {
            newEfficiency = 0;
        }
        super.setEfficiency(newEfficiency);

    }
}
