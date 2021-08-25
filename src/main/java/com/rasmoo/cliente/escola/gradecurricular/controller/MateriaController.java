package com.rasmoo.cliente.escola.gradecurricular.controller;


import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.repository.IMateriaRepository;
import com.rasmoo.cliente.escola.gradecurricular.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private IMateriaRepository materiaRepository;

    @Autowired
    private MateriaService materiaService;


    @GetMapping
    public ResponseEntity<List<MateriaDto>> listarMaterias() {
        return  ResponseEntity.status(HttpStatus.OK).body(this.materiaService.buscarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<MateriaDto> consultaMateria(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.buscarPorId(id));
    }


    @PostMapping
    public ResponseEntity<Boolean> cadastrarMateria(@Valid @RequestBody MateriaDto materia){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.materiaService.salvar(materia));
    }

    @PutMapping()
    public ResponseEntity<Boolean> atualizarMateria(@Valid @RequestBody MateriaDto materia){
            return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.atualizar(materia));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> apagaMateria(@PathVariable long id){
            return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.excluir(id));
    }


}