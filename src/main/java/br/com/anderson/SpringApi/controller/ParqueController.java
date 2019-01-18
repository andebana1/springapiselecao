package br.com.anderson.SpringApi.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.anderson.SpringApi.entity.ParqueEolico;
import br.com.anderson.SpringApi.jpa.ComplexoRepository;
import br.com.anderson.SpringApi.jpa.ParqueRepository;
import br.com.anderson.SpringApi.exception.NotFoundException;


@RestController
@RequestMapping("/api")
public class ParqueController {
	
	@Autowired
	private ParqueRepository parqueRepository;
	
	@Autowired
	private ComplexoRepository complexoRepository;
	
	@GetMapping("/complexos/{compId}/parques")
	public List<ParqueEolico> parquePorComplexoId(@PathVariable Long compId) {
    	
        if(!complexoRepository.existsById(compId)) {
            throw new NotFoundException("Parque não encontrado!");
        }
    	
    	return parqueRepository.findByComplexoEolicoId(compId);
    }
	
	@PostMapping("/complexos/{compId}/parques")
    public ParqueEolico addParque(@PathVariable Long compId,
                            @Valid @RequestBody ParqueEolico parque) {
        return complexoRepository.findById(compId)
                .map(complexo -> {
                    parque.setComplexo_eolico(complexo);
                    return parqueRepository.save(parque);
                }).orElseThrow(() -> new NotFoundException("Complexo não encontrado!"));
    }
	
	@PutMapping("/complexos/{compId}/parques/{parqueId}")
    public ParqueEolico alterarParque(@PathVariable Long compId,
    								@PathVariable Long parqueId,
    								@Valid @RequestBody ParqueEolico parque) {
    	
    	if(!complexoRepository.existsById(compId)) {
    		throw new NotFoundException("Complexo não encontrado!");
    	}
    	
        return parqueRepository.findById(parqueId)
                .map(p -> {
                    p.setNome(parque.getNome());
                    p.setLatitude(parque.getLatitude());
                    p.setLongitude(parque.getLongitude());
                    p.setPotencia_instalada(parque.getPotencia_instalada());
                    return parqueRepository.save(p);
                }).orElseThrow(() -> new NotFoundException("Parque não encontrado!"));
    }
	
	@DeleteMapping("/complexos/{compId}/parques/{parqueId}")
    public String deleteAssignment(@PathVariable Long compId,
    							   @PathVariable Long parqueId) {
    	
    	if(!complexoRepository.existsById(compId)) {
    		throw new NotFoundException("Complexo não encontrado");
    	}
    	
        return parqueRepository.findById(parqueId)
                .map(p -> {
                    parqueRepository.delete(p);
                    return "Removido com sucesso!";
                }).orElseThrow(() -> new NotFoundException("Parque não encontrado!"));
    }
}
