package com.example.task.operationprocessor.controller;


import com.example.task.operationprocessor.OperationProcessorApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = OperationProcessorApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(printOnlyOnFailure = false)

public class OperationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addNewOperation_ReturnEmptyListWithHTTPStatusOk() throws Exception {



        this.mockMvc.perform(
                post("/api/operation/add")
                        .param("accountNumber", "1")
                        .param("recipientNumber", "3")
                        .param("ip", "192.168.0.2")
                        .param("amount", "10")

                        .param("date", "2020-12-03 01:15:30")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                // then
                .andExpect(status().isOk())
                .andExpect(content().string("[]"))
                .andReturn();


    }

    @Test
    public void addTwoIdenticalOperation_ReturnListOfErrorStatus() throws Exception {

        this.mockMvc.perform(
                post("/api/operation/add")
                        .param("accountNumber", "1")
                        .param("recipientNumber", "2")
                        .param("ip", "192.168.0.1")
                        .param("amount", "100")

                        .param("date", "2020-12-03 01:15:30")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                // then
                .andExpect(status().isOk())
                .andExpect(content().string("[]"))
                .andReturn();

        this.mockMvc.perform(
                post("/api/operation/add")
                        .param("accountNumber", "1")
                        .param("recipientNumber", "2")
                        .param("ip", "192.168.0.1")
                        .param("amount", "100")

                        .param("date", "2020-12-03 01:15:30")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                // then
                .andExpect(status().isOk())
                .andExpect(content().string("[-1,-2]"))
                .andReturn();

    }



}