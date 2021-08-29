package com.sb.spring.loanapplicationservice.controller;

import com.sb.spring.loanapplicationservice.model.LoanApplicationDTO;
import com.sb.spring.loanapplicationservice.service.ILoanApplicationService;
import com.sb.spring.loanapplicationservice.util.LoanStatus;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


/**
 * TODO need to resolve eureka service mocking
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class LoanApplicationControllerTest {

    @MockBean
    ILoanApplicationService loanApplicationService;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    /**
     * TODO Write negative test and exception tests
     *
     * @throws Exception
     */
    @Test
    void getAllLoans() throws Exception {

        // prepare mock result
        List<LoanApplicationDTO> loanApplicationDTOS = new ArrayList<LoanApplicationDTO>() {{
            add(new LoanApplicationDTO(1, "1", 1000, "24", LoanStatus.CREATED.name()));
            add(new LoanApplicationDTO(2, "1", 1200, "48", LoanStatus.CREATED.name()));

        }};

        // mock service
        when(loanApplicationService.getAllLoans()).thenReturn(loanApplicationDTOS);

        //execute and validate
        MvcResult mvcResult = mockMvc.perform(get(("/api/loanapplications/all"))).andReturn();

        //validate
        assert (mvcResult.getResponse().getStatus() == 200);
        assert (mvcResult.getResponse().getContentAsString().contains("1000") && mvcResult.getResponse().getContentAsString().contains("1200"));

    }

    // TODO write more positive and negative scenarios
    @Test
    void getAllLoansByCustomerId() {
    }

    @Test
    void register() {
    }
}