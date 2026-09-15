package com.maya.finance.app;

import com.maya.finance.domain.Contract;
import com.maya.finance.domain.Customer;
import com.maya.finance.domain.Vehicle;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DemoStore {
    private final ConcurrentHashMap<String, Customer> customers = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Vehicle> vehicles = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Contract> contracts = new ConcurrentHashMap<>();
    public Customer create(Customer v) { if (customers.putIfAbsent(v.id(), v) != null) throw new IllegalArgumentException("customer already exists"); return v; }
    public Vehicle create(Vehicle v) { if (vehicles.putIfAbsent(v.id(), v) != null) throw new IllegalArgumentException("vehicle already exists"); return v; }
    public Contract create(Contract v) { if (!customers.containsKey(v.customerId())) throw new IllegalArgumentException("unknown customer"); if (v.vehicleId()!=null && !vehicles.containsKey(v.vehicleId())) throw new IllegalArgumentException("unknown vehicle"); if (contracts.putIfAbsent(v.id(), v)!=null) throw new IllegalArgumentException("contract already exists"); return v; }
    public Customer customer(String id) { return require(customers.get(id), "customer", id); }
    public Vehicle vehicle(String id) { return require(vehicles.get(id), "vehicle", id); }
    public Contract contract(String id) { return require(contracts.get(id), "contract", id); }
    public Collection<Customer> customers() { return List.copyOf(customers.values()); }
    public List<Vehicle> vehiclesForCustomer(String id) { return vehicles.values().stream().filter(v -> v.customerId().equals(id)).toList(); }
    public List<Contract> contractsForCustomer(String id) { return contracts.values().stream().filter(c -> c.customerId().equals(id)).toList(); }
    public Vehicle findVehicle(String id) { return id == null ? null : vehicles.get(id); }
    private static <T> T require(T v, String type, String id) { if (v == null) throw new NoSuchElementException(type + " not found: " + id); return v; }
}
