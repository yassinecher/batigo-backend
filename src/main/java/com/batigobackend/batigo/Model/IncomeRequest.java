package com.batigobackend.batigo.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class IncomeRequest {

    private Float amount ;

    private Date date;

    private String source;

    int projetId;
}
