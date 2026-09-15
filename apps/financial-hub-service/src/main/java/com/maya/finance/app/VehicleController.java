package com.maya.finance.app;

import com.maya.finance.domain.ResidualValueCalculator;
import com.maya.finance.domain.Vehicle;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@RestController
@RequestMapping("/v1/vehicles")
public class VehicleController {
    private final DemoStore store;
    private final ResidualValueCalculator calculator = new ResidualValueCalculator();
    public VehicleController(DemoStore store) { this.store = store; }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Vehicle create(@Valid @RequestBody VehicleRequest request) {
        store.customer(request.customerId());
        return store.create(new Vehicle(request.id(), request.customerId(), request.brand(), request.model(), request.registrationDate(), request.mileage(), request.initialValue()));
    }
    @GetMapping("/{id}") public Vehicle get(@PathVariable String id) { return store.vehicle(id); }
    @GetMapping("/{id}/residual-value")
    public ResidualValueCalculator.Result residual(@PathVariable String id, @RequestParam(defaultValue="0.12") double depreciation) {
        var vehicle = store.vehicle(id);
        int age = Math.max(0, Period.between(vehicle.registrationDate(), LocalDate.now()).getYears());
        return calculator.calculate(vehicle, age, depreciation);
    }
    public record VehicleRequest(@NotBlank String id, @NotBlank String customerId, String brand, String model, @NotNull LocalDate registrationDate, int mileage, @NotNull BigDecimal initialValue) {}
}
