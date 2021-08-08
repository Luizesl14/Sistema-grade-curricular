package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.entity.MateriaEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMateriaService {

    public  List<MateriaEntity> buscarTodos();

    public MateriaEntity buscarPorId(long id);

    public boolean salvar(MateriaEntity materia);

    public  Boolean atualizar(final long id, final MateriaEntity materia);

    public  Boolean excluir(final long id);
}
