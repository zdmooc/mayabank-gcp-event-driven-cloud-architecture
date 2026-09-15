package com.maya.finance.app;

import com.maya.finance.domain.RelationshipView;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/v1/customers")
public class RelationshipController {
    private final RelationshipService service;
    public RelationshipController(RelationshipService service) { this.service = service; }
    @GetMapping("/{id}/relationship")
    public RelationshipView relationship(@PathVariable String id, @RequestParam(required=false) LocalDate asOf) {
        return service.relationship(id, asOf == null ? LocalDate.now() : asOf);
    }
    @GetMapping("/{id}/recommendations")
    public Object recommendations(@PathVariable String id, @RequestParam(required=false) LocalDate asOf) {
        return relationship(id, asOf).recommendations();
    }
}
