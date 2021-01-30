package com.example.task.operationprocessor.rules;

import com.example.task.operationprocessor.dto.Operation;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class IRule2Impl implements IRule {
    @Override
    public Optional<Integer> check(Operation current, Operation previous) {

        if (current.getAccountNumber().equals(previous.getAccountNumber()) &&
                current.getRecipientNumber().equals(previous.getRecipientNumber()) &&
                Duration.between(current.getDate(), previous.getDate()).getSeconds() < 600 &&
                current.getIp().equals(previous.getIp())
        ) {
            // Rule “Подмена ip-адреса”
            return Optional.of(-2);
        } else
            return Optional.empty();
    }

}
