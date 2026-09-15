package com.maya.finance.app;

import com.maya.finance.domain.RelationshipView;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/v1/me")
public class MobileController {
    private final RelationshipService service;
    public MobileController(RelationshipService service) { this.service = service; }
    private RelationshipView view(String customerId) { return service.relationship(customerId, LocalDate.now()); }
    @GetMapping public RelationshipView me(@RequestHeader("X-Demo-Customer-Id") String customerId) { return view(customerId); }
    @GetMapping("/contracts") public Object contracts(@RequestHeader("X-Demo-Customer-Id") String customerId) { return view(customerId).contracts(); }
    @GetMapping("/vehicles") public Object vehicles(@RequestHeader("X-Demo-Customer-Id") String customerId) { return view(customerId).vehicles(); }
    @GetMapping("/recommendations") public Object recommendations(@RequestHeader("X-Demo-Customer-Id") String customerId) { return view(customerId).recommendations(); }
}
