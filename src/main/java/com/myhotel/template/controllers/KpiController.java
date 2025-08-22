package com.myhotel.template.controllers;

import com.myhotel.template.services.KpiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kpi")
public class KpiController {

    private final KpiService service;

    public KpiController(KpiService service) {
        this.service = service;
    }

    @GetMapping("/average-score")
    public ResponseEntity<?> getWeightedAverageScore() {
        return ResponseEntity.ok(service.getWeightedAverageScore());
    }
}
