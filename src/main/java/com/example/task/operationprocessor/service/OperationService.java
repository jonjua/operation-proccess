package com.example.task.operationprocessor.service;


import com.example.task.operationprocessor.dto.Operation;
import com.example.task.operationprocessor.rules.IRule;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

@Service
public class OperationService {

    private final Map<Integer, LinkedBlockingQueue<Operation>> storage = new ConcurrentHashMap<>();
    private final List<IRule> rules;

    public OperationService(List<IRule> rules) {
        this.rules = rules;
    }

    public List<Integer> addOperationToStorage(@NonNull final Operation operation) {
        var accountNumber = operation.getAccountNumber();
        if (storage.containsKey(accountNumber)) {
            var operations = storage.get(accountNumber);
            var collect = operations.stream().
                    flatMap(o -> rules.stream().map(r -> r.check(operation, o))).filter(Optional::isPresent).
                    map(Optional::get).
                    collect(Collectors.toList());
            if (collect.isEmpty()) operations.offer(operation);
            return collect;
        } else {

            var queue = new LinkedBlockingQueue<Operation>();
            queue.add(operation);
            storage.put(accountNumber, queue);

            return List.of();
        }


    }
}
