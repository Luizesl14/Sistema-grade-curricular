package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.exception.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.repository.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@CacheConfig(cacheNames = "materia");
@Service
public class MateriaService implements IMateriaService {

    private static final String MESAGEM_ERRO = "Erro interno identificado, contate o suporte";
    private static final String MATERIA_NÃO_ENCONTRADA = "Máteria não encontrada";

    private final IMateriaRepository materiaRepository;
    private ModelMapper mapper;

    @Autowired
    public MateriaService(IMateriaRepository materiaRepository) {
        this.mapper = new ModelMapper();
        this.materiaRepository = materiaRepository;
    }

    @CachePut(unless = "#result.size() < 3")
    @Override
    public List<MateriaDto> buscarTodos() {
        try {
            return this.mapper.map(this.materiaRepository.findAll(), new TypeToken<List<MateriaDto>>() {}.getType());
        }
        catch (Exception e) {
            throw new MateriaException(MATERIA_NÃO_ENCONTRADA, HttpStatus.NOT_FOUND);
        }
    }

    @Cacheable(value = "materia", key = "#id")
    @Override
    public MateriaDto buscarPorId(long id) {
        try {
            Optional<MateriaEntity> materiaEntityOptional = this.materiaRepository.findById(id);
            if (materiaEntityOptional.isPresent()) {
                return this.mapper.map(materiaEntityOptional.get(), MateriaDto.class);
            }
            throw new MateriaException(MATERIA_NÃO_ENCONTRADA, HttpStatus.NOT_FOUND);
        } catch (MateriaException m) {
            throw m;
        } catch (Exception e) {
            throw new MateriaException(MESAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public boolean salvar(MateriaDto materia) {
        try {
            MateriaEntity materiaEntity = this.mapper.map(materia, MateriaEntity.class);
            this.materiaRepository.save(materiaEntity);
            return Boolean.TRUE;
        } catch (Exception e) {
            throw new MateriaException(MESAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CacheEvict(value = "materia", key = "#materia.id")
    @Override
    public Boolean atualizar(MateriaDto materia) {
        try {
            this.buscarPorId(materia.getId());
            MateriaEntity materiaAtualizada = this.mapper.map(materia, MateriaEntity.class);
            materiaRepository.save(materiaAtualizada);
            return Boolean.TRUE;

        }
        catch (MateriaException m) {
            throw m;

        } catch (Exception e) {
            throw new MateriaException(MATERIA_NÃO_ENCONTRADA, HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public Boolean excluir(long id) {
        try {
            this.buscarPorId(id);
            this.materiaRepository.deleteById(id);

            // retorna  verdadeiro
            return Boolean.TRUE;
        } catch (MateriaException m) {
            throw m;
        } catch (Exception e) {
            throw e;
        }
    }
}
