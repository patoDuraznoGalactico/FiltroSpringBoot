package com.riwi.filtro_spring_boot.infraestructure.abstract_service;

import com.riwi.filtro_spring_boot.api.dto.request.StudentRequest;
import com.riwi.filtro_spring_boot.api.dto.response.ClassEntityBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.StudentBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.StudentResponse;
import com.riwi.filtro_spring_boot.utils.enums.SortType;
import org.springframework.data.domain.Page;

public interface IStudentService extends CrudService<StudentRequest, StudentResponse,Long>{
    public Page<StudentBasicResponse> getAllBasic(int page, int size, SortType sort, String name, String email);
    public StudentResponse disable(Long aLong);
    public String FIELD_BY_SORT = "courseName";
}
