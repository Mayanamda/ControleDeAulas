package com.falta.controledeaulas;

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

    @Test
    public void testCadastrarAluno() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/controle-aulas/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"João\",\"matricula\":\"12345\",\"dataNascimento\":\"1990-01-01\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testRegistrarPresenca() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/controle-aulas/registro-presenca")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"alunoId\":1,\"participou\":true}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testEncontrarAlunosPorNome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/controle-aulas/alunos?nome=João"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testObterRegistrosPorAluno() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/controle-aulas/alunos/1/registros-presenca"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testContarFaltasPorAluno() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/controle-aulas/alunos/1/faltas"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}