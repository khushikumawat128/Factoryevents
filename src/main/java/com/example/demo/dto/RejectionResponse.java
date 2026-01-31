package com.example.demo.dto;

public class RejectionResponse {

    private String eventId;
    private String reason;

    public RejectionResponse(String eventId, String reason) {
        this.eventId = eventId;
        this.reason = reason;
    }

    public String getEventId() { return eventId; }
    public String getReason() { return reason; }
}
