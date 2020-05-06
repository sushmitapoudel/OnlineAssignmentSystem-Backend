package com.sushmita.onlineassignmentsystem.service.impl;

import com.sushmita.onlineassignmentsystem.model.Question;
import com.sushmita.onlineassignmentsystem.model.Report;
import com.sushmita.onlineassignmentsystem.repository.QuestionRepository;
import com.sushmita.onlineassignmentsystem.repository.ReportRepository;
import com.sushmita.onlineassignmentsystem.service.QuestionService;
import com.sushmita.onlineassignmentsystem.util.NameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getAllQuestion(long teacherId) {
        return questionRepository.findAllByTeacher_Id(teacherId);
    }

    @Override
    public Question saveOrUpdateQuestion(Question question) {
        String action = question.getId() == null ? "Added" : "Edited";
        addReport(question, action);
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getBySubjectAndClass(String subject, String grade) {
        return questionRepository.findAllBySubject_NameAndSubject_GradeAndDeadLineAfter(subject, grade, LocalDate.now());
    }

    @Override
    public void delete(long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public double countAll() {
        return questionRepository.count();
    }

    @Override
    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getAllQuestionForStudent(String grade, String faculty) {
        return questionRepository.findAllBySubject_GradeAndSubject_Faculty(grade, faculty);
    }

    private void addReport(Question question, String action) {
        String content = NameUtil.getFullName(question.getTeacher().getUser()) + " " + action + " question of subject "
                + question.getSubject().getName() + " for Grade " + question.getSubject().getGrade() + " of " + question.getSubject().getFaculty();
        reportRepository.save(new Report(content, "Answer"));
    }

}
