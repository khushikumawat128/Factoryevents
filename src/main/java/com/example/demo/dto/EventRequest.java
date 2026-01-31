package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EventRequest {

    @NotBlank(message = "eventId is required")
    private String eventId;

    @NotBlank(message = "machineId is required")
    private String machineId;

    @NotNull(message = "eventTime is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant eventTime;

    @Min(value = 0, message = "durationMs must be >= 0")
    private long durationMs;

    private int defectCount;

    // getters and setters

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public Instant getEventTime() {
        return eventTime;
    }

    public void setEventTime(Instant eventTime) {
        this.eventTime = eventTime;
    }

    public long getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(long durationMs) {
        this.durationMs = durationMs;
    }

    public int getDefectCount() {
        return defectCount;
    }

    public void setDefectCount(int defectCount) {
        this.defectCount = defectCount;
    }
}
