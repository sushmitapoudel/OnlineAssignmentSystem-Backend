package com.sushmita.onlineassignmentsystem.service.impl;

import com.sushmita.onlineassignmentsystem.model.Report;
import com.sushmita.onlineassignmentsystem.repository.ReportRepository;
import com.sushmita.onlineassignmentsystem.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.time.LocalDate;
import java.util.List;
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public List<Report> findByCreatedDateBetween(LocalDate startDate, LocalDate endDate) {
        return reportRepository.findByCreatedDateBetween(startDate, endDate);
    }
}
