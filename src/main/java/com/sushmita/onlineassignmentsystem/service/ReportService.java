package com.sushmita.onlineassignmentsystem.service;

import com.sushmita.onlineassignmentsystem.model.Report;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<Report> findByCreatedDateBetween(LocalDate startDate, LocalDate endDate);

}
