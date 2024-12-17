package com.ferbator.shelterapi;

import com.ferbator.shelterapi.controller.RegistrationController;
import com.ferbator.shelterapi.services.RegistrationService;
import com.ferbator.shelterapi.services.WebSecurityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тест проверяет, что эндпоинт /registration доступен без авторизации и возвращает OK.
 */
@WebMvcTest(RegistrationController.class)
public class SimpleRegistrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WebSecurityService webSecurityService;

    @MockBean
    private RegistrationService service;

    @Test
    public void registrationEndpointShouldBeAccessibleWithoutAuth() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk());
    }
}
