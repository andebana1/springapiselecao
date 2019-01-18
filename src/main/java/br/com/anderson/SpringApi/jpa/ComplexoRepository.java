package br.com.anderson.SpringApi.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.anderson.SpringApi.entity.ComplexoEolico;

@Repository
public interface ComplexoRepository extends JpaRepository<ComplexoEolico, Long>{
	
}
