package com.example.enrollment_service.services;

import com.example.enrollment_service.dto.EnrollmentRequest;
import com.example.enrollment_service.exceptions.ResourceNotFoundException;
import com.example.enrollment_service.models.Enrollment;
import com.example.enrollment_service.repositorys.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }


    public Enrollment enroll(EnrollmentRequest request) {
        boolean exists = enrollmentRepository.findByUserIdAndCourseId(request.getUserId(), request.getCourseId()).isPresent();
        if (exists) {
            throw new IllegalArgumentException("User already enrolled in this course.");
        }

        Enrollment enrollment = Enrollment.builder()
                .userId(request.getUserId())
                .courseId(request.getCourseId())
                .enrolledAt(LocalDateTime.now())
                .build();

        return enrollmentRepository.save(enrollment);
    }

    public void unenroll(Integer userId, Integer courseId) {
        Enrollment enrollment = enrollmentRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));
        enrollmentRepository.delete(enrollment);
    }

    public List<Enrollment> getByUserId(Integer userId) {
        return enrollmentRepository.findByUserId(userId);
    }

    public List<Enrollment> getByCourseId(Integer courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }
}
