package com.example.task.operationprocessor.controller;

import com.example.task.operationprocessor.dto.Operation;
import com.example.task.operationprocessor.service.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("api/operation")
public class OperationRestController {

    private OperationService service;

    public OperationRestController(OperationService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<List<Integer>> addOperation(Operation operation){

        return ResponseEntity.ok(service.addOperationToStorage(operation));
    }
}
