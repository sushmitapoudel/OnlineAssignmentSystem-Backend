package com.sushmita.onlineassignmentsystem.service.impl;

import com.sushmita.onlineassignmentsystem.model.Answer;
import com.sushmita.onlineassignmentsystem.model.Feedback;
import com.sushmita.onlineassignmentsystem.model.Report;
import com.sushmita.onlineassignmentsystem.model.Teacher;
import com.sushmita.onlineassignmentsystem.repository.*;
import com.sushmita.onlineassignmentsystem.service.FeedbackService;
import com.sushmita.onlineassignmentsystem.util.NameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback saveOrUpdateFeedback(Feedback feedback) {
        addReport(feedback);
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback getByAnswerId(long id) {
        return feedbackRepository.findByAnswer_Id(id).orElseThrow(() -> new RuntimeException("Feedback Not Found"));
    }

    private void addReport(Feedback feedback){
        String content = NameUtil.getFullName(feedback.getAnswer().getQuestion().getTeacher().getUser()) + " gave feedback for assignment submitted by "
                + NameUtil.getFullName(feedback.getAnswer().getStudent().getUser()) ;
        reportRepository.save(new Report(content, "FeedBack"));
    }
}
