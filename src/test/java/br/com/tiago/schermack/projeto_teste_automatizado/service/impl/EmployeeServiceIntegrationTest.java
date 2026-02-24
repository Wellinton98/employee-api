package br.com.tiago.schermack.projeto_teste_automatizado.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tiago.schermack.projeto_teste_automatizado.dto.EmployeeRequestDTO;
import br.com.tiago.schermack.projeto_teste_automatizado.dto.EmployeeResponseDTO;
import br.com.tiago.schermack.projeto_teste_automatizado.entity.Employee;
import br.com.tiago.schermack.projeto_teste_automatizado.repository.EmployeeRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional

public class EmployeeServiceIntegrationTest {

    @Autowired
     
    private EmployeeService employeeService;

    @Autowired

    private EmployeeRepository employeeRepository;


    @Test
    @DisplayName("Deve criar funcinario e retorna sucesso")
    public void deveCriarFuncionarioERetornarSucesso(){
        //Arrange
        var requestDTO = new EmployeeRequestDTO(
                "Wellinton",
                "wellinton@email.com");
        //Act
        var responseDTO = employeeService.create(requestDTO);

        //Assert
       assertEquals(responseDTO.firstName(),"Wellinton");
       assertEquals(responseDTO.email(),"wellinton@email.com");
       assertEquals(responseDTO.id(),1L);
       
    }

    @Test
    @DisplayName("Deve atualizar fucionario existente eretornar com sucesso")

    public void deveAtualizarFuncionarioExistenteERetornarSucesso(){
        //Arrange
        var requestDTO = new EmployeeRequestDTO(
                "Wellinton",
                "wellinton@email.com");
                
    var responseDTO = employeeService.create(requestDTO);

    EmployeeRequestDTO updateRequesteDTO = new EmployeeRequestDTO(
            "Wellinton Braz",
            "test@gmal");
        //Act
        EmployeeResponseDTO response = employeeService.update(responseDTO.id(), updateRequesteDTO);

        //Assert
        assertEquals(response.firstName(),"Wellinton Braz");
        assertEquals(response.email(),"test@gmal");
        assertEquals(response.id(), 1L);

        }
        @Test
        @DisplayName("Deve deletar o funcinario existente e retonar sucesso")
                //arrange
        public void deveDeletarFuncinarioExistenteERetonarSucesso(){
                var requestDTO = new EmployeeRequestDTO(
                        "Wellinton Braz",
                        "test@gmal");
                var responseDTO = employeeService.create(requestDTO);

                //act
                employeeService.delete(responseDTO.id());

                //assert
                Employee employeeDeleted = employeeRepository.findById(responseDTO.id()).orElse(null);
                assertNull(employeeDeleted);
        }

}
