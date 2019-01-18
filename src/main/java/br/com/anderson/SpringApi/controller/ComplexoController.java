package br.com.anderson.SpringApi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.catalina.Contained;
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

import br.com.anderson.SpringApi.entity.ComplexoEolico;
import br.com.anderson.SpringApi.jpa.ComplexoRepository;
import br.com.anderson.SpringApi.exception.NotFoundException;

@RestController
@RequestMapping("/api")
public class ComplexoController {
	@Autowired
	private ComplexoRepository complexoRepository;
	
	@GetMapping("/complexos")
	public List<ComplexoEolico> todosOsEstudantes(){
		return complexoRepository.findAll();
	}
	
	@GetMapping("/complexos/{id}")
	public ComplexoEolico complexoPorId(@PathVariable Long id) {
		Optional<ComplexoEolico> optional = complexoRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new NotFoundException("Complexo não encontrado, id = " + id);
		}
	}
	
	@PostMapping("/complexos")
    public ComplexoEolico criarComplexo(@Valid @RequestBody ComplexoEolico complexoEolico) {
        return complexoRepository.save(complexoEolico);
    }
	
	@PutMapping("/complexos/{id}")
	public ComplexoEolico alterarComplexo(@PathVariable Long id, @Valid @RequestBody ComplexoEolico complexoEolico) {
		return complexoRepository.findById(id).map(
			complexo-> {
				complexo.setNome(complexoEolico.getNome());
				complexo.setUf(complexoEolico.getUf());
				complexo.setIdentificador(complexoEolico.getIdentificador());
				return complexoRepository.save(complexo);
			}
		).orElseThrow(()->new NotFoundException("Complexo não encontrado, id = " + id));
	}
	
	@DeleteMapping("complexos/{id}")
	public String deletarComplexo(@PathVariable Long id) {
		return complexoRepository.findById(id).map(
			complexo -> {
				complexoRepository.delete(complexo);
				return "Deletado com sucesso!";
			}
		).orElseThrow(()->new NotFoundException("Complexo não encontrado, id = " + id));
	}
}
