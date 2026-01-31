package com.example.demo.util;

import com.example.demo.dto.EventRequest;
import com.example.demo.model.MachineEvent;

public class PayloadComparator {

    public static boolean isSamePayload(MachineEvent e, EventRequest r) {
        return e.getMachineId().equals(r.getMachineId())
                && e.getEventTime().equals(r.getEventTime())
                && e.getDurationMs() == r.getDurationMs()
                && e.getDefectCount() == r.getDefectCount();
    }
}
