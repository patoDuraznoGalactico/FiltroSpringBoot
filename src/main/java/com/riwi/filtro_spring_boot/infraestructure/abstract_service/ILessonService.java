package com.riwi.filtro_spring_boot.infraestructure.abstract_service;

import com.riwi.filtro_spring_boot.api.dto.request.LessonRequest;
import com.riwi.filtro_spring_boot.api.dto.request.MultimediaRequest;
import com.riwi.filtro_spring_boot.api.dto.response.LessonBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.LessonResponse;
import com.riwi.filtro_spring_boot.api.dto.response.StudentBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.StudentResponse;
import com.riwi.filtro_spring_boot.utils.enums.SortType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ILessonService extends CrudService<LessonRequest, LessonResponse,Long>{
    public LessonResponse create(LessonRequest request, List<MultimediaRequest> requestMultimedia);
    public Page<LessonBasicResponse> getAllBasic(int page, int size, SortType sort, String title, String content);
    public LessonResponse disable(Long aLong);
    public String FIELD_BY_SORT = "courseName";
}
