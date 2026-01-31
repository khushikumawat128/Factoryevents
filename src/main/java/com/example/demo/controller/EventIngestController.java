package com.example.demo.controller;

import com.example.demo.dto.BatchIngestResponse;
import com.example.demo.dto.EventRequest;
import com.example.demo.service.EventIngestService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventIngestController {

    private final EventIngestService eventIngestService;

    public EventIngestController(EventIngestService eventIngestService) {
        this.eventIngestService = eventIngestService;
    }

    @PostMapping("/batch")
    public BatchIngestResponse ingestEvents(
            @RequestBody @Valid List<@Valid EventRequest> events) {

        return eventIngestService.ingestBatch(events);
    }
}
