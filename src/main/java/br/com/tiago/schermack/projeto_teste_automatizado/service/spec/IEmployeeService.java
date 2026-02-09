package br.com.tiago.schermack.projeto_teste_automatizado.service.spec;

import java.util.List;

import br.com.tiago.schermack.projeto_teste_automatizado.dto.EmployeeRequestDTO;
import br.com.tiago.schermack.projeto_teste_automatizado.dto.EmployeeResponseDTO;

public interface IEmployeeService {

    EmployeeResponseDTO create(EmployeeRequestDTO dto);

    EmployeeResponseDTO update(Long id, EmployeeRequestDTO dto);

    void delete(Long id);

    EmployeeResponseDTO findById(Long id);

    List<EmployeeResponseDTO> findAll();
}
