package com.example.enrollment_service.dto;

import lombok.Data;

@Data
public class EnrollmentRequest {
    private Integer userId;
    private Integer courseId;
}
