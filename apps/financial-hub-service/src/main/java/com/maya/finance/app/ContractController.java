package com.maya.finance.app;

import com.maya.finance.domain.Contract;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/v1/contracts")
public class ContractController {
    private final DemoStore store;
    public ContractController(DemoStore store) { this.store = store; }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Contract create(@Valid @RequestBody ContractRequest request) {
        return store.create(new Contract(request.id(), request.customerId(), request.vehicleId(), request.type(), request.startDate(), request.endDate(), request.status(), request.monthlyPayment()));
    }
    @GetMapping("/{id}") public Contract get(@PathVariable String id) { return store.contract(id); }
    public record ContractRequest(@NotBlank String id, @NotBlank String customerId, String vehicleId, @NotNull Contract.ContractType type, @NotNull LocalDate startDate, @NotNull LocalDate endDate, @NotNull Contract.ContractStatus status, BigDecimal monthlyPayment) {}
}
