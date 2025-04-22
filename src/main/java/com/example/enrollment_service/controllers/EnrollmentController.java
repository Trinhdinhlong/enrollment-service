package com.example.enrollment_service.controllers;

import com.example.enrollment_service.dto.EnrollmentRequest;
import com.example.enrollment_service.models.Enrollment;
import com.example.enrollment_service.services.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    @GetMapping()
    public ResponseEntity<List<Enrollment>> getEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }
    @PostMapping
    public ResponseEntity<Enrollment> enroll(@RequestBody EnrollmentRequest request) {
        return ResponseEntity.ok(enrollmentService.enroll(request));
    }

    @DeleteMapping
    public ResponseEntity<String> unenroll(@RequestParam Integer userId, @RequestParam Integer courseId) {
        enrollmentService.unenroll(userId, courseId);
        return ResponseEntity.ok("Unenrolled successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(enrollmentService.getByUserId(userId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourse(@PathVariable Integer courseId) {
        return ResponseEntity.ok(enrollmentService.getByCourseId(courseId));
    }
}
