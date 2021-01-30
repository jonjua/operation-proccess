package com.example.task.operationprocessor.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Operation {
    private Integer accountNumber;
    private Integer recipientNumber;
    private String ip;
    private Integer amount;
    private LocalDateTime date;
}
