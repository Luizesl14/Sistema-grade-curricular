package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entity.MateriaEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMateriaService {

    public  List<MateriaDto> buscarTodos();

    public MateriaDto buscarPorId(long id);

    public boolean salvar(MateriaDto materia);

    public  Boolean atualizar(final MateriaDto materia);

    public  Boolean excluir(final long id);
}
