package com.example.task.operationprocessor.rules;

import com.example.task.operationprocessor.dto.Operation;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class IRule1Impl implements IRule {
    @Override
    public Optional<Integer> check(Operation current, Operation previous) {
        if (current.getAccountNumber().equals(previous.getAccountNumber()) &&
                current.getRecipientNumber().equals(previous.getRecipientNumber()) &&
                current.getAmount().equals(previous.getAmount()) &&
                Duration.between(current.getDate(), previous.getDate()).getSeconds() < 600
        ) {
            //Rule “Повторное ошибочное перечисление средств”
            return Optional.of(-1);
        } else return Optional.empty();
    }
}
