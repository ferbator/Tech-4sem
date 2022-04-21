package com.ferbator.controller;

import com.ferbator.dao.daoImpl.CatDAO;
import com.ferbator.dao.daoImpl.FriendshipCatDAO;
import com.ferbator.dao.daoImpl.OwnerDAO;
import com.ferbator.dao.daoImpl.OwnershipCatDAO;
import com.ferbator.dao.entities.Cat;
import com.ferbator.dao.entities.FriendshipCat;
import com.ferbator.dao.entities.Owner;
import com.ferbator.dao.entities.OwnershipCat;
import com.ferbator.services.ShelterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatController.class)
public class CatControllerTest {
    @MockBean
    CatDAO catDAO;

    @MockBean
    OwnerDAO ownerDAO;

    @MockBean
    FriendshipCatDAO friendshipCatDAO;

    @MockBean
    OwnershipCatDAO ownershipCatDAO;

    @MockBean
    Cat cat;

    @MockBean
    Owner owner;

    @MockBean
    FriendshipCat friendshipCat;

    @MockBean
    OwnershipCat ownershipCat;

    @MockBean
    ShelterService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCatsByColor_ThrowException() throws Exception {
        String url = "http://localhost:8080/cat/findAllOneColorCats/Grey";
        mockMvc.perform(get(url)).andExpect(status().is4xxClientError());
    }
}
