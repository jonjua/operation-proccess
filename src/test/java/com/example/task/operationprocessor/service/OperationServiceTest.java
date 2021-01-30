package com.example.task.operationprocessor.service;

import com.example.task.operationprocessor.rules.IRule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OperationServiceTest {
    private final IRule rule = mock(IRule.class);
    private final OperationService service = new OperationService(List.of(rule));

    @Test
    public void addOperationToStorage_returnListOfInteger() {


    }

}