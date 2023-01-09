//package com.ferbator.shelterapi;
//
//import com.ferbator.shelterapi.controller.OwnerController;
//import com.ferbator.shelterapi.dao.daoImpl.CatRepository;
//import com.ferbator.shelterapi.dao.daoImpl.FriendshipCatRepository;
//import com.ferbator.shelterapi.dao.daoImpl.OwnerRepository;
//import com.ferbator.shelterapi.dao.daoImpl.OwnershipCatRepository;
//import com.ferbator.shelterapi.dao.dto.OwnerDto;
//import com.ferbator.shelterapi.dao.entities.Owner;
//import com.ferbator.shelterapi.services.ShelterService;
//import com.ferbator.shelterapi.services.WebSecurityService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@WebMvcTest(OwnerController.class)
//class ShelterApplicationTests {
//    @MockBean
//    ShelterService service;
//
//    @MockBean
//    OwnerRepository ownerRepository;
//
//    @MockBean
//    CatRepository catRepository;
//
//    @MockBean
//    FriendshipCatRepository friendshipCatRepository;
//
//    @MockBean
//    OwnershipCatRepository ownershipCatRepository;
//
//    @MockBean
//    WebSecurityService webSecurityService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private static Owner owner;
//    private static OwnerDto ownerDto;
//
//    @BeforeEach
//    public void setUp() {
//        owner = new Owner();
//        owner.setLogin("Denis");
//        owner.setPassword("$2a$12$QslpGcxXWuH3TS9dUirDB.NtB6IFiqbvq89zJfRCdWILHsuQF9Uz6");
//        owner.setRole("ROLE_USER");
//        ownerDto = new OwnerDto(owner);
//
//        List<SimpleGrantedAuthority> tmpList = Stream.of(ownerDto.getRole()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//
//        Mockito.when(webSecurityService.mapOwnerDtoToUserDetails(ownerDto)).thenReturn(new User(ownerDto.getLogin(), ownerDto.getPassword(), tmpList));
//
//        Mockito.when(webSecurityService.loadUserByUsername("Denis")).thenReturn(webSecurityService.mapOwnerDtoToUserDetails(ownerDto));
//        Mockito.when(service.findOwnerByLogin("Denis")).thenReturn(ownerDto);
//        Mockito.when(ownerRepository.findByLogin("Denis")).thenReturn(owner);
//    }
//
//    @Test
//    public void shouldAllowPageWhenUserIsAdmin() throws Exception {
//        String url = "http://localhost:8080/admin";
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get(url)
//                        .with(SecurityMockMvcRequestPostProcessors.user("Denis")
//                                .password("0000")
//                                .roles("USER")))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    void contextLoads() {
//    }
//
//}
