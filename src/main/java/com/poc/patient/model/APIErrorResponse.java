package com.poc.patient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class APIErrorResponse {
    private String message;
    private Integer statusCode;
    private String path;
}
