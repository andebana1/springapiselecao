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

import br.com.anderson.SpringApi.entity.Aerogerador;
import br.com.anderson.SpringApi.entity.ComplexoEolico;
import br.com.anderson.SpringApi.entity.ParqueEolico;
import br.com.anderson.SpringApi.exception.NotFoundException;
import br.com.anderson.SpringApi.jpa.AerogeradorRepository;
import br.com.anderson.SpringApi.jpa.ComplexoRepository;
import br.com.anderson.SpringApi.jpa.ParqueRepository;

@RestController
@RequestMapping("/api")
public class AerogeradorController {
	
	@Autowired
	private ComplexoRepository complexoRepository;
	
	@Autowired
	private ParqueRepository parqueRepository;
	
	@Autowired
	private AerogeradorRepository aerogeradorRepository;
	
	@GetMapping("/complexos/{compId}/parques/{parqueId}/aerogeradores")
	public List<Aerogerador> aerogeradorPorComplexoId(@PathVariable Long compId, @PathVariable Long parqueId){
		if(!complexoRepository.existsById(compId)) {
            throw new NotFoundException("Complexo não encontrado!");
        }
		if(!parqueRepository.existsById(parqueId)) {
            throw new NotFoundException("Parque não encontrado!");
        }
		
		return aerogeradorRepository.findByParqueEolicoId(parqueId);
	}
	
	@PostMapping("/complexos/{compId}/parques/{parqueId}/aerogeradores")
    public Aerogerador addAerogerador(@PathVariable Long compId, @PathVariable Long parqueId,
                            @Valid @RequestBody Aerogerador aerogerador/*parque*/) {
		if(!complexoRepository.existsById(compId)) {
            throw new NotFoundException("Complexo não encontrado!");
        }
		if(!parqueRepository.existsById(parqueId)) {
            throw new NotFoundException("Parque não encontrado!");
        }
        return parqueRepository.findById(parqueId)
                .map(parque -> {
                    aerogerador.setParque(parque);
                    return aerogeradorRepository.save(aerogerador);
                }).orElseThrow(() -> new NotFoundException("Complexo não encontrado!"));
    }
	
	@PutMapping("/complexos/{compId}/parques/{parqueId}/aerogeradores/{aeroId}")
    public Aerogerador alterarAerogerador(@PathVariable Long compId,
    								@PathVariable Long parqueId,
    								@PathVariable Long aeroId,
    								@Valid @RequestBody Aerogerador aerogerador /*parque*/) {
    	
    	if(!complexoRepository.existsById(compId)) {
    		throw new NotFoundException("Complexo não encontrado!");
    	}
    	if(!parqueRepository.existsById(parqueId)) {
            throw new NotFoundException("Parque não encontrado!");
        }
    	
        return aerogeradorRepository.findById(aeroId)
                .map(p -> {
                    p.setNome(aerogerador.getNome());
                    p.setLatitude(aerogerador.getLatitude());
                    p.setLongitude(aerogerador.getLongitude());
                    p.setAltura_torre(aerogerador.getAltura_torre());
                    p.setDiametro_varredura(aerogerador.getDiametro_varredura());
                    p.setModelo(aerogerador.getModelo());
                    return aerogeradorRepository.save(p);
                }).orElseThrow(() -> new NotFoundException("Aerogerador não encontrado!"));
    }
	
	@DeleteMapping("/complexos/{compId}/parques/{parqueId}/aerogeradores/{aeroId}")
    public String deleteAerogerador(@PathVariable Long compId,
    							   @PathVariable Long parqueId,
    							   @PathVariable Long aeroId) {
    	
    	if(!complexoRepository.existsById(compId)) {
    		throw new NotFoundException("Complexo não encontrado");
    	}
    	if(!parqueRepository.existsById(parqueId)) {
            throw new NotFoundException("Parque não encontrado!");
        }
    	
        return aerogeradorRepository.findById(aeroId)
                .map(p -> {
                    aerogeradorRepository.delete(p);
                    return "Removido com sucesso!";
                }).orElseThrow(() -> new NotFoundException("Parque não encontrado!"));
    }
}
