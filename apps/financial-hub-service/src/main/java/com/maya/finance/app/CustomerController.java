package com.maya.finance.app;

import com.maya.finance.domain.Customer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
    private final DemoStore store;
    public CustomerController(DemoStore store) { this.store = store; }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@Valid @RequestBody CustomerRequest request) {
        return store.create(new Customer(request.id(), request.firstName(), request.lastName(), request.birthDate(), request.postalCode(), request.email(), request.phone(), request.externalReferences()));
    }
    @GetMapping("/{id}") public Customer get(@PathVariable String id) { return store.customer(id); }
    @GetMapping("/{id}/contracts") public Object contracts(@PathVariable String id) { store.customer(id); return store.contractsForCustomer(id); }
    public record CustomerRequest(@NotBlank String id, String firstName, String lastName, LocalDate birthDate, String postalCode, String email, String phone, Map<String,String> externalReferences) {}
}
