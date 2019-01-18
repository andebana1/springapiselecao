package br.com.anderson.SpringApi.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository; 
import br.com.anderson.SpringApi.entity.ParqueEolico;

@Repository
public interface ParqueRepository extends JpaRepository<ParqueEolico, Long>{
	@Query(value="SELECT * FROM parque_eolico WHERE complexo_eolico_id = :complexoId", nativeQuery=true)
	List<ParqueEolico> findByComplexoEolicoId(@Param("complexoId") Long complexoId);
}
