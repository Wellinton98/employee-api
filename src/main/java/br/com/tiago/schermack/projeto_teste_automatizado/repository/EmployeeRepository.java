package br.com.tiago.schermack.projeto_teste_automatizado.repository;

import br.com.tiago.schermack.projeto_teste_automatizado.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
