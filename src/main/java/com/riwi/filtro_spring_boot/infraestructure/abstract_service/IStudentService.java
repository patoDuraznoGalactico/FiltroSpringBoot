package com.riwi.filtro_spring_boot.infraestructure.abstract_service;

import com.riwi.filtro_spring_boot.api.dto.request.StudentRequest;
import com.riwi.filtro_spring_boot.api.dto.response.StudentResponse;

public interface IStudentService extends CrudService<StudentRequest, StudentResponse,Long>{
    public String FIELD_BY_SORT = "courseName";
}
