package com.falta.controledeaulas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListarAlunos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/alunos"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCadastrarAluno() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/alunos")
               .contentType("application/json")
               .content("{\"nome\":\"João\",\"matricula\":\"12345\",\"dataNascimento\":\"1990-01-01\"}"))
               .andExpect(MockMvcResultMatchers.status().isOk());	
    }
}