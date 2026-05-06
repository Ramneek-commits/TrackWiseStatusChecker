package com.trackwise;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for TaskStatusChecker
 * 
 * Follows TDD approach:
 * - Tests written first
 * - Implementation adjusted to pass tests
 */
public class TaskStatusCheckerTest {

    private TaskStatusChecker checker;

    /**
     * Runs before each test
     */
    @Before
    public void setUp() {
        checker = new TaskStatusChecker();
    }

    // ===== VALID CASES =====

    @Test
    public void testCompletedTaskStatus() {
    	assertEquals("Completed", checker.checkStatus("S101", "T1"));    }

    @Test
    public void testSubmittedTaskStatus() {
        assertEquals("Submitted", checker.checkStatus("S101", "T2"));
    }

    @Test
    public void testUnderReviewTaskStatus() {
        assertEquals("Under Review", checker.checkStatus("S102", "T1"));
    }

    @Test
    public void testNeedResubmissionTaskStatus() {
        assertEquals("Need Resubmission", checker.checkStatus("S103", "T3"));
    }

    // ===== VALIDATION CASES =====

    @Test
    public void testEmptyStudentId() {
        assertEquals("Student ID cannot be empty", checker.checkStatus("", "T1"));
    }

    @Test
    public void testEmptyTaskId() {
        assertEquals("Task ID cannot be empty", checker.checkStatus("S101", ""));
    }

    @Test
    public void testInvalidStudentId() {
        assertEquals("Invalid Student ID", checker.checkStatus("ABC", "T1"));
    }

    @Test
    public void testInvalidTaskId() {
        assertEquals("Invalid Task ID", checker.checkStatus("S101", "TASK1"));
    }

    // ===== EDGE CASES =====

    @Test
    public void testTaskNotFound() {
        assertEquals("Task record not found", checker.checkStatus("S999", "T9"));
    }

    @Test
    public void testLowerCaseInputHandling() {
        assertEquals("Completed", checker.checkStatus("s101", "t1"));
    }
}