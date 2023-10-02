package com.falta.controledeaulas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ControleAulasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
   
    }

    @Test
    public void testCadastrarAluno() throws Exception {
        String requestBody = "{\"nome\": \"João\", \"matricula\": \"12345\", \"dataNascimento\": \"1990-01-01\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testRegistrarPresenca() throws Exception {
        String requestBody = "{\"alunoId\": 1, \"data\": \"2023-09-28\", \"participou\": true}";
        mockMvc.perform(MockMvcRequestBuilders.post("/registro-presenca")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testListarAlunos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/alunos"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testListarRegistrosPresenca() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/registro-presenca"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}