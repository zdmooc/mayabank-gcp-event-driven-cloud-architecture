package com.maya.finance.app;

import com.maya.finance.domain.CustomerIdentity;
import com.maya.finance.domain.CustomerMatcher;
import com.maya.finance.domain.MatchingDecision;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/matching")
public class MatchingController {
    private final DemoStore store;
    private final CustomerMatcher matcher = new CustomerMatcher();
    public MatchingController(DemoStore store) { this.store = store; }
    @PostMapping("/evaluate")
    public MatchingDecision evaluate(@Valid @RequestBody MatchRequest request) {
        var identity = new CustomerIdentity(request.externalCustomerId(), request.firstName(), request.lastName(), request.birthDate(), request.postalCode(), request.email(), request.phone());
        return matcher.evaluate(request.requestId(), identity, List.copyOf(store.customers()));
    }
    public record MatchRequest(@NotBlank String requestId, String externalCustomerId, String firstName, String lastName, LocalDate birthDate, String postalCode, String email, String phone) {}
}
