package com.example.task.operationprocessor.rules;

import com.example.task.operationprocessor.dto.Operation;

import java.util.List;
import java.util.Optional;

public interface IRule {
    Optional<Integer> check(Operation current, Operation previous);
}
