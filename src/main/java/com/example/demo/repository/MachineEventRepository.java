package com.example.demo.repository;

import com.example.demo.model.MachineEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MachineEvent;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface MachineEventRepository extends JpaRepository<MachineEvent, String> {

    Optional<MachineEvent> findByEventId(String eventId);

    List<MachineEvent> findByMachineIdAndEventTimeGreaterThanEqualAndEventTimeLessThan(
            String machineId,
            Instant start,
            Instant end
    );
}
