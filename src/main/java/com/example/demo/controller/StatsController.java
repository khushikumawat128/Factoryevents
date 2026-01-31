package com.example.demo.controller;

import com.example.demo.dto.StatsResponse;
import com.example.demo.service.StatsService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public StatsResponse getStats(
            @RequestParam String machineId,
            @RequestParam Instant start,
            @RequestParam Instant end
    ) {
        return statsService.getStats(machineId, start, end);
    }
}
