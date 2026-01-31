package com.example.demo.service;

import com.example.demo.dto.StatsResponse;
import com.example.demo.model.MachineEvent;
import com.example.demo.repository.MachineEventRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class StatsService {

    private final MachineEventRepository repository;

    public StatsService(MachineEventRepository repository) {
        this.repository = repository;
    }

    //Test Case 7: Start/End boundary correctness
     public StatsResponse getStats(String machineId, Instant start, Instant end) {

        List<MachineEvent> events =
                repository.findByMachineIdAndEventTimeGreaterThanEqualAndEventTimeLessThan(
                        machineId, start, end
                );

        long eventsCount = events.size();

        long defectsCount = events.stream()
                .filter(e -> e.getDefectCount() != -1)
                .mapToLong(MachineEvent::getDefectCount)
                .sum();

        double windowHours =
                Duration.between(start, end).toSeconds() / 3600.0;

        double avgDefectRate =
                windowHours == 0 ? 0 : defectsCount / windowHours;

        String status =
                avgDefectRate < 2.0 ? "Healthy" : "Warning";

        return new StatsResponse(
                machineId,
                start,
                end,
                eventsCount,
                defectsCount,
                avgDefectRate,
                status
        );
    }
}
