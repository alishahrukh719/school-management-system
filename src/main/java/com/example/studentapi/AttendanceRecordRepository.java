package com.example.studentapi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, String> {
    List<AttendanceRecord> findByDate(String date);
}
