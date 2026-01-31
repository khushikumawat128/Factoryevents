package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.MachineEvent;
import com.example.demo.repository.MachineEventRepository;
import com.example.demo.util.PayloadComparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class EventIngestService {

    private static final long MAX_DURATION_MS = 6 * 60 * 60 * 1000; // 6 hours
    private static final long FUTURE_EVENT_LIMIT_MINUTES = 15;

    private final MachineEventRepository repository;

    public EventIngestService(MachineEventRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public BatchIngestResponse ingestBatch(List<EventRequest> events) {

        BatchIngestResponse response = new BatchIngestResponse();

        for (EventRequest req : events) {

            String validationError = validate(req);
            if (validationError != null) {
                response.incrementRejected();
                response.addRejection(new RejectionResponse(req.getEventId(), validationError));
                continue;
            }

            processEvent(req, response);
        }

        return response;
    }

    @Transactional
    void processEvent(EventRequest req, BatchIngestResponse response) {

        Optional<MachineEvent> existingOpt = repository.findByEventId(req.getEventId());

        // Case 1: New event
        if (existingOpt.isEmpty()) {
            repository.save(toEntity(req));
            response.incrementAccepted();
            return;
        }

        MachineEvent existing = existingOpt.get();

        // Case 2: Identical payload → dedupe
        if (PayloadComparator.isSamePayload(existing, req)) {
            response.incrementDeduped();
            return;
        }

        // Case 3: Update → only if incoming eventTime is newer
        Instant incomingEventTime = req.getEventTime();
        if (incomingEventTime.isAfter(existing.getEventTime())) {
            update(existing, req, Instant.now());
            response.incrementUpdated();
        }
    }

    private String validate(EventRequest r) {

        // Test case 4: invalid duration
        if (r.getDurationMs() < 0 || r.getDurationMs() > MAX_DURATION_MS) {
            return "INVALID_DURATION";
        }

        // Test case 5: future eventTime
        if (r.getEventTime()
                .isAfter(Instant.now().plus(FUTURE_EVENT_LIMIT_MINUTES, ChronoUnit.MINUTES))) {
            return "EVENT_TIME_IN_FUTURE";
        }

        return null;
    }

    private MachineEvent toEntity(EventRequest r) {
        MachineEvent e = new MachineEvent();
        e.setEventId(r.getEventId());
        e.setMachineId(r.getMachineId());
        e.setEventTime(r.getEventTime());
        e.setDurationMs(r.getDurationMs());
        e.setDefectCount(r.getDefectCount());
        e.setReceivedTime(Instant.now());
        return e;
    }

    private void update(MachineEvent e, EventRequest r, Instant receivedTime) {
        e.setMachineId(r.getMachineId());
        e.setEventTime(r.getEventTime());
        e.setDurationMs(r.getDurationMs());
        e.setDefectCount(r.getDefectCount());
        e.setReceivedTime(receivedTime);
    }
}
