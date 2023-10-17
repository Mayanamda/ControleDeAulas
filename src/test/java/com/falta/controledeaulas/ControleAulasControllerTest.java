package com.falta.controledeaulas;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.falta.controledeaulas.controller.ControleAulasController;
import com.falta.controledeaulas.entity.RegistroPresenca;
import com.falta.controledeaulas.repository.RegistroPresencaRepository;
import com.falta.controledeaulas.service.ControleAulasService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(ControleAulasController.class)
public class ControleAulasControllerTest {

	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private RegistroPresencaRepository controleAulaRepository;

	    @MockBean
	    private ControleAulasService controleAulasService;

	    @Autowired
	    private ObjectMapper objectMapper;

	    @BeforeEach
	    public void setUp() {
	        RegistroPresenca registroPresenca = new RegistroPresenca();
	        registroPresenca.setId(1);
	        registroPresenca.setAlunoId(1);
	        registroPresenca.setData(new Date());
	        registroPresenca.setParticipou(true);

	        when(controleAulaRepository.findById(1)).thenReturn(Optional.of(registroPresenca));

	        when(controleAulaRepository.save(Mockito.any(RegistroPresenca.class))).thenReturn(registroPresenca);

	        List<RegistroPresenca> registrosAula = new ArrayList<>();
	        registrosAula.add(registroPresenca);

	        when(controleAulaRepository.findAll()).thenReturn(registrosAula);
	    }

	    @Test
	    public void testRegistrarPresenca() throws Exception {
	        RegistroPresenca registroPresenca = new RegistroPresenca();
	        registroPresenca.setAlunoId(1);
	        registroPresenca.setData(new Date());
	        registroPresenca.setParticipou(true);

	        String requestBody = "{\"alunoId\": 1, \"data\": \"2023-10-10\", \"participou\": true}";

	        mockMvc.perform(MockMvcRequestBuilders
	                .post("/api/registros-aula")
	                .contentType("application/json")
	                .content(requestBody))
	                .andExpect(MockMvcResultMatchers.status().isCreated());
	    }

	    @Test
	    public void testListarRegistrosPresenca() throws Exception {
	    	mockMvc.perform(MockMvcRequestBuilders
	                .get("/api/registros-aula")
	                .contentType("application/json"))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	    }

	    @Test
	    public void testObterRegistroPresencaPorId() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders
	        	.get("/api/registros-aula/1"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.alunoId").value(1));
	    }

	    @Test
	    public void testAtualizarRegistroPresenca() throws Exception {
	        RegistroPresenca novoRegistroPresenca = new RegistroPresenca();
	        novoRegistroPresenca.setAlunoId(2);
	        novoRegistroPresenca.setData(new Date());
	        novoRegistroPresenca.setParticipou(true);

	        mockMvc.perform(MockMvcRequestBuilders
	        	.put("/api/registros-aula/1")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(novoRegistroPresenca)))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.alunoId").value(2));
	    }

	    @Test
	    public void testExcluirRegistroPresenca() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders
	        	.delete("/api/registros-aula/1"))
	            .andExpect(status().isNoContent());
	    }
}