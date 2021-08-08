package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.repository.IMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaService implements  IMateriaService {

    @Autowired
    IMateriaRepository materiaRepository;


    @Override
    public List<MateriaEntity> buscarTodos() {
        try {
            return  this.materiaRepository.findAll();

        }catch (Exception e){
           return  Collections.emptyList();
        }
    }

    @Override
    public MateriaEntity buscarPorId(long id) {
        try {
            Optional<MateriaEntity> materiaEntityOptional = this.materiaRepository.findById(id);
            if (materiaEntityOptional.isPresent()){
                return materiaEntityOptional.get();
            }
            return  null;
        }catch ( Exception e){
             return null;
        }
    }

    @Override
    public boolean salvar(MateriaEntity materia) {
        try{
            this.materiaRepository.save(materia);
            return true;
        }catch ( Exception e){
            return false;
        }
    }

    @Override
    public Boolean atualizar(long id , MateriaEntity materia) {
        try{

            Optional<MateriaEntity> materiaEntityOptional =  this.materiaRepository.findById(id);

            if (materiaEntityOptional.isPresent()){
                MateriaEntity mt =  materiaEntityOptional.get();

                // receber valores passados pelo corpo
                mt.setName(materia.getName());
                mt.setHoras(materia.getHoras());
                mt.setCodigo(materia.getCodigo());
                mt.setFrequencia(materia.getFrequencia());

                // save entidade  atualizad
                this.materiaRepository.save(mt);
                // retorna  verdadeiro
                return true;
            }
            return  false;

        }catch ( Exception e){
            return false;
        }
    }


    @Override
    public Boolean excluir(long id) {
        try{

            this.materiaRepository.deleteById(id);

            // retorna  verdadeiro
            return true;
        }catch ( Exception e){
            return false;
        }
    }
}
