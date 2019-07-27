package com.oocl.packagebooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.packagebooking.model.PackageOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PackageOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void should_return_packageOrder_when_given_new_packageOrder() throws Exception {
        PackageOrder packageOrder = new PackageOrder("2019070080001", "test", "15622222", 1, 6, new Date());
        String content = mapper.writeValueAsString(packageOrder);
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/packageOrders")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['name']", is("test")))
                .andExpect(jsonPath("$['phone']", is("15622222")));
    }

    @Test
    public void should_return_updated_packageOrder_when_update_packageOrder() throws Exception {
        PackageOrder packageOrder = new PackageOrder("2019070080001", "test", "15622222", 1, 6, new Date());
        String content = mapper.writeValueAsString(packageOrder);
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/packageOrders")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]['companyName']", is("test")))
                .andExpect(jsonPath("$[0]['employeesNumber']").value(300));
    }

}