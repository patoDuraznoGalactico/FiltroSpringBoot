package com.riwi.filtro_spring_boot.infraestructure.abstract_service;

import com.riwi.filtro_spring_boot.api.dto.request.ClassEntityRequest;
import com.riwi.filtro_spring_boot.api.dto.response.ClassEntityBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.ClassEntityResponse;
import com.riwi.filtro_spring_boot.utils.enums.SortType;
import org.springframework.data.domain.Page;

public interface IClassEntityService extends CrudService<ClassEntityRequest, ClassEntityResponse,Long>{
    public Page<ClassEntityBasicResponse> getAllBasic(int page, int size, SortType sort, String name,String description);
    public String FIELD_BY_SORT = "courseName";
}
