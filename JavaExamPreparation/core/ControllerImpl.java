package restaurant.core;

import restaurant.models.client.Client;
import restaurant.models.client.ClientImpl;
import restaurant.models.waiter.FullTimeWaiter;
import restaurant.models.waiter.HalfTimeWaiter;
import restaurant.models.waiter.Waiter;
import restaurant.models.working.Working;
import restaurant.models.working.WorkingImpl;
import restaurant.repositories.ClientRepository;
import restaurant.repositories.Repository;
import restaurant.repositories.WaiterRepository;

import java.util.Collection;

import static restaurant.common.ConstantMessages.*;
import static restaurant.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private final Repository<Waiter> waiterRepository;
    private final Repository<Client> clientRepository;

    private int curvedClients;



    public ControllerImpl() {
        this.waiterRepository = new WaiterRepository();
        this.clientRepository = new ClientRepository();
        this.curvedClients = 0;
    }
    @Override
    public String addWaiter(String type, String waiterName) {
        Waiter waiter;
        if (type.equals("FullTimeWaiter")) {
            waiter = new FullTimeWaiter(waiterName);
        } else if (type.equals("HalfTimeWaiter")) {
            waiter = new HalfTimeWaiter(waiterName);
        } else {
            throw new IllegalArgumentException(WAITER_INVALID_TYPE);
        }
        this.waiterRepository.add(waiter);
        return String.format(WAITER_ADDED, type, waiterName);
    }

    @Override
    public String addClient(String clientName, String... orders) {
        Client client = new ClientImpl(clientName);
        for (String order : orders) {
            client.getClientOrders().add(order);
        }
        this.clientRepository.add(client);
        return String.format(CLIENT_ADDED, clientName);
    }

    @Override
    public String removeWaiter(String waiterName) {
        Waiter waiter = this.waiterRepository.byName(waiterName);
        if (waiter == null) {
            throw new IllegalArgumentException(String.format(WAITER_DOES_NOT_EXIST, waiterName));
        }
        this.waiterRepository.remove(waiter);
        return String.format(WAITER_REMOVE, waiterName);
    }

    @Override
    public String removeClient(String clientName) {
       Client client = this.clientRepository.byName(clientName);
        if (client == null) {
            throw new IllegalArgumentException(String.format(CLIENT_DOES_NOT_EXIST, clientName));
        }
        this.clientRepository.remove(client);
        return String.format(CLIENT_REMOVE, clientName);
    }

    @Override
    public String startWorking(String clientName) {
        if (this.waiterRepository.getCollection().isEmpty()) {
            throw new IllegalArgumentException(THERE_ARE_NO_WAITERS);
        }
        Client client = this.clientRepository.byName(clientName);
        WorkingImpl working = new WorkingImpl();
        working.takingOrders(client, this.waiterRepository.getCollection());
        this.curvedClients++;
        return String.format(ORDERS_SERVING, clientName);
            }

            @Override
            public String getStatistics () {
                StringBuilder statistics = new StringBuilder();
                statistics.append(String.format(FINAL_CLIENTS_COUNT, curvedClients)).append(System.lineSeparator());
                statistics.append(String.format(FINAL_WAITERS_STATISTICS)).append(System.lineSeparator());
                for (Waiter waiter : this.waiterRepository.getCollection()) {
                    statistics.append(String.format(FINAL_WAITER_NAME, waiter.getName())).append(System.lineSeparator());
                    statistics.append(String.format(FINAL_WAITER_EFFICIENCY, waiter.getEfficiency())).append(System.lineSeparator());
                    if (waiter.getTakenOrders().getOrdersList().isEmpty()) {
                        statistics.append(String.format(FINAL_WAITER_ORDERS, "none")).append(System.lineSeparator());
                    } else {
                        statistics.append(String.format(FINAL_WAITER_ORDERS, String.join(FINAL_WAITER_ORDERS_DELIMITER, waiter.getTakenOrders().getOrdersList()))).append(System.lineSeparator());
                    }
                }
                return statistics.toString().trim();

            }
        }
