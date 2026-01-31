package com.example.demo.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(
    name = "machine_events",
    uniqueConstraints = @UniqueConstraint(columnNames = "eventId")
)
public class MachineEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String eventId;

    @Column(nullable = false)
    private String machineId;

    @Column(nullable = false)
    private Instant eventTime;

    @Column(nullable = false)
    private Instant receivedTime;

    private long durationMs;

    private int defectCount;

    // getters & setters

    public Long getId() { return id; }

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public String getMachineId() { return machineId; }
    public void setMachineId(String machineId) { this.machineId = machineId; }

    public Instant getEventTime() { return eventTime; }
    public void setEventTime(Instant eventTime) { this.eventTime = eventTime; }

    public Instant getReceivedTime() { return receivedTime; }
    public void setReceivedTime(Instant receivedTime) { this.receivedTime = receivedTime; }

    public long getDurationMs() { return durationMs; }
    public void setDurationMs(long durationMs) { this.durationMs = durationMs; }

    public int getDefectCount() { return defectCount; }
    public void setDefectCount(int defectCount) { this.defectCount = defectCount; }
}
