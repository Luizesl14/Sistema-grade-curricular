package com.rasmoo.cliente.escola.gradecurricular.model;

import lombok.Builder;
import lombok.Getter;

@Getter   // somente Getters
@Builder   // valores imutaveis
public class ErrorResponse {

    private  String mensagem;
    private  int httpStatus;
    private  long timeStamp;
}
