package com.example.enrollment_service.repositorys;

import com.example.enrollment_service.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<Enrollment> findByUserIdAndCourseId(Integer userId, Integer courseId);

    List<Enrollment> findByUserId(Integer userId);
    List<Enrollment> findByCourseId(Integer courseId);

    void deleteByUserIdAndCourseId(Long userId, Long courseId);
}

