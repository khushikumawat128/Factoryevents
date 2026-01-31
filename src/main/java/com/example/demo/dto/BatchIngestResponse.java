package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class BatchIngestResponse {

    private int accepted;
    private int deduped;
    private int updated;
    private int rejected;

    private List<RejectionResponse> rejections = new ArrayList<>();


    public int getAccepted() {
        return accepted;
    }

    public int getDeduped() {
        return deduped;
    }

    public int getUpdated() {
        return updated;
    }

    public int getRejected() {
        return rejected;
    }

    public List<RejectionResponse> getRejections() {
        return rejections;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public void setDeduped(int deduped) {
        this.deduped = deduped;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }

    public void setRejected(int rejected) {
        this.rejected = rejected;
    }

    public void setRejections(List<RejectionResponse> rejections) {
        this.rejections = rejections;
    }

    public void incrementAccepted() {
        this.accepted++;
    }

    public void incrementDeduped() {
        this.deduped++;
    }

    public void incrementUpdated() {
        this.updated++;
    }

    public void incrementRejected() {
        this.rejected++;
    }

    public void addRejection(RejectionResponse rejection) {
        this.rejections.add(rejection);
    }
}
