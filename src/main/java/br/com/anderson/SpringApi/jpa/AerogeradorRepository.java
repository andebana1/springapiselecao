package br.com.anderson.SpringApi.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository; 
import br.com.anderson.SpringApi.entity.Aerogerador;

@Repository
public interface AerogeradorRepository extends JpaRepository<Aerogerador, Long>{
	@Query(value="SELECT * FROM aerogerador WHERE parque_eolico_id = :parqueId", nativeQuery=true)
	List<Aerogerador> findByParqueEolicoId(@Param("parqueId") Long parqueId);
}
