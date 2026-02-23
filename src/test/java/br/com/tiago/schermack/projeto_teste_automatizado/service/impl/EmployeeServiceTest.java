package br.com.tiago.schermack.projeto_teste_automatizado.service.impl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tiago.schermack.projeto_teste_automatizado.dto.EmployeeRequestDTO;
import br.com.tiago.schermack.projeto_teste_automatizado.dto.EmployeeResponseDTO;
import br.com.tiago.schermack.projeto_teste_automatizado.entity.Employee;
import br.com.tiago.schermack.projeto_teste_automatizado.repository.EmployeeRepository;

@SpringBootTest
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;


    @Test
    @DisplayName("Deve criar um funcionário e retornar sucesso")
    public void deveCriarFuncionarioERetornarSucesso() {
        // Arrange

        EmployeeRequestDTO requestDTO = new EmployeeRequestDTO("Wellinton", "Wellinton.braz29@gmail.com");

        Employee employeeSaved = new Employee(requestDTO.firstName(), requestDTO.email());
        employeeSaved.setId(1L);

        when(employeeRepository.save(any(Employee.class)))
        .thenReturn(employeeSaved);


        //Act
        EmployeeResponseDTO responseDTO = employeeService.create(requestDTO);

        //Assert
        assertEquals(1L, responseDTO.id());
        assertEquals("Wellinton", responseDTO.firstName());
        assertEquals("Wellinton.braz29@gmail.com", responseDTO.email());

        verify(employeeRepository, times(1)).
save(any(Employee.class));


    }
    @Test
    @DisplayName("Deve atualizar um funcionário existente e retornar sucesso")
    public void shouldUpdateEmployeeAndReturnResponseDTO() {
        // Arrange
        Long id = 1L;
        EmployeeRequestDTO requestDTO = new EmployeeRequestDTO("Wellinton", "Wellinton.braz29@gmail.com");

        Employee existingEmployee = new Employee("AntigoNome", "antigo.email@gmail.com");
        existingEmployee.setId(id);

        when(employeeRepository.findById(id)).thenReturn(java.util.Optional.of(existingEmployee));

        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EmployeeResponseDTO responseDTO = employeeService.update(id, requestDTO);

        assertEquals(id, responseDTO.id());
        assertEquals("Wellinton", responseDTO.firstName());
        assertEquals("Wellinton.braz29@gmail.com", responseDTO.email());

    }
    @Test
    @DisplayName("Deve deletar um funcionário existente e retornar sucesso")
    public void shouldDeleteEmployee() {

        Long id = 1L;

        Employee existingEmployee = new Employee("Wellinton", "wellinton@email.com");
        existingEmployee.setId(id);

        when(employeeRepository.findById(id))
                .thenReturn(Optional.of(existingEmployee));

        employeeService.delete(id);

        verify(employeeRepository, times(1)).delete(existingEmployee);
    }
@Test
@DisplayName("update Deve lançar quando o funcionário não existir")
public void shouldThrowEntityNotFoundExceptionWhenUpdatingNonExistingEmployee() {

    Long id = 1L;

    EmployeeRequestDTO requestDTO =
            new EmployeeRequestDTO("Wellinton", "wellinton@email.com");

    when(employeeRepository.findById(id))
            .thenReturn(Optional.empty());

    assertThrows(jakarta.persistence.EntityNotFoundException.class,
            () -> employeeService.update(id, requestDTO));
    }
}