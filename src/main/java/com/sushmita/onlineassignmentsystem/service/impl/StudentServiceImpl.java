package com.sushmita.onlineassignmentsystem.service.impl;

import com.sushmita.onlineassignmentsystem.model.Answer;
import com.sushmita.onlineassignmentsystem.model.Report;
import com.sushmita.onlineassignmentsystem.model.Student;
import com.sushmita.onlineassignmentsystem.model.User;
import com.sushmita.onlineassignmentsystem.repository.ReportRepository;
import com.sushmita.onlineassignmentsystem.repository.StudentRepository;
import com.sushmita.onlineassignmentsystem.repository.UserRepository;
import com.sushmita.onlineassignmentsystem.service.StudentService;
import com.sushmita.onlineassignmentsystem.util.NameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveOrUpdateStudent(Student student) {
        User user = userRepository.save(student.getUser());
        student.setUser(user);
        String action = student.getId() == null ? "Added" : "Edited";
        addReport(student, action);
        return studentRepository.save(student);
    }

    @Override
    public Student getById(long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student Not Found"));
    }

    @Override
    public void delete(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public double countAll() {
        return studentRepository.count();
    }

    @Override
    public Student findById(long id) {
        return studentRepository.findById(id).orElseThrow(() ->  new RuntimeException("Not Found"));

    }

    private void addReport(Student student, String action) {
        String content = NameUtil.getFullName(student.getUser()) + " was " + action ;
        reportRepository.save(new Report(content, "Student"));
    }
}
