package com.trackwise;

import java.util.HashMap;
import java.util.Map;

/**
 * TaskStatusChecker simulates a simplified OnTrack function
 * where a student can check the status of a submitted task.
 * 
 * This class validates input and retrieves task status
 * from an in-memory dataset.
 */
public class TaskStatusChecker {

    // Simulated database (StudentID-TaskID → Status)
    private final Map<String, String> taskDatabase = new HashMap<>();

    /**
     * Constructor initializes predefined task records.
     * In a real system, this would come from a database.
     */
    public TaskStatusChecker() {
        taskDatabase.put("S101-T1", "Completed");
        taskDatabase.put("S101-T2", "Submitted");
        taskDatabase.put("S102-T1", "Under Review");
        taskDatabase.put("S103-T3", "Need Resubmission");
    }

    
    public String checkStatus(String studentId, String taskId) {

        // ===== Step 1: Validate empty inputs =====
        if (studentId == null || studentId.trim().isEmpty()) {
            return "Student ID cannot be empty";
        }

        if (taskId == null || taskId.trim().isEmpty()) {
            return "Task ID cannot be empty";
        }

        // ===== Step 2: Normalize input =====
        studentId = studentId.trim().toUpperCase();
        taskId = taskId.trim().toUpperCase();

        // ===== Step 3: Validate formats =====
        if (!studentId.matches("S\\d{3}")) {
            return "Invalid Student ID";
        }

        if (!taskId.matches("T\\d+")) {
            return "Invalid Task ID";
        }

        // ===== Step 4: Lookup task record =====
        String key = studentId + "-" + taskId;

        if (!taskDatabase.containsKey(key)) {
            return "Task record not found";
        }

        // ===== Step 5: Return status =====
        return taskDatabase.get(key);
    }
}