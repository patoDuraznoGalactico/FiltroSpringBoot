package com.riwi.filtro_spring_boot.infraestructure.abstract_service;

import com.riwi.filtro_spring_boot.api.dto.request.LessonRequest;
import com.riwi.filtro_spring_boot.api.dto.response.LessonResponse;

public interface ILessonService extends CrudService<LessonRequest, LessonResponse,Long>{
    public String FIELD_BY_SORT = "courseName";
}
