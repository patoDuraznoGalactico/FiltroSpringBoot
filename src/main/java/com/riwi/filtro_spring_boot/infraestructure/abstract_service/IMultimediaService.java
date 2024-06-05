package com.riwi.filtro_spring_boot.infraestructure.abstract_service;

import com.riwi.filtro_spring_boot.api.dto.request.MultimediaRequest;
import com.riwi.filtro_spring_boot.api.dto.response.MultimediaResponse;

public interface IMultimediaService extends CrudService<MultimediaRequest, MultimediaResponse,Long>{
    public String FIELD_BY_SORT = "courseName";
}
