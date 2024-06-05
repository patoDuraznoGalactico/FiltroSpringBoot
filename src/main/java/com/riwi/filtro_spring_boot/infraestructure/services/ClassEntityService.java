package com.riwi.filtro_spring_boot.infraestructure.services;

import com.riwi.filtro_spring_boot.api.dto.request.ClassEntityRequest;
import com.riwi.filtro_spring_boot.api.dto.response.ClassEntityBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.ClassEntityResponse;
import com.riwi.filtro_spring_boot.api.dto.response.LessonBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.StudentBasicResponse;
import com.riwi.filtro_spring_boot.domain.entities.ClassEntity;
import com.riwi.filtro_spring_boot.domain.repositories.ClassEntityRepository;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.IClassEntityService;
import com.riwi.filtro_spring_boot.utils.enums.SortType;
import com.riwi.filtro_spring_boot.utils.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ClassEntityService implements IClassEntityService {

    @Autowired
    private final ClassEntityRepository classEntityRepository;
    @Override
    public ClassEntityResponse create(ClassEntityRequest request) {
        ClassEntity classEntity = this.requestToEntity(request);
        classEntity.setCreated_at(LocalDateTime.now());
        classEntity.setActive(true);
        return this.entityToResp(this.classEntityRepository.save(classEntity));
    }

    @Override
    public ClassEntityResponse get(Long aLong) {
        return this.entityToResp(this.find(aLong));
    }

    @Override
    public ClassEntityResponse update(ClassEntityRequest request, Long aLong) {
        ClassEntity classEntity = this.find(aLong);
        BeanUtils.copyProperties(request,classEntity);
        return this.entityToResp(this.classEntityRepository.save(classEntity));
    }

    @Override
    public void delete(Long aLong) {
        ClassEntity classEntity = this.find(aLong);
        this.classEntityRepository.delete(classEntity);
    }

    @Override
    public Page<ClassEntityResponse> getAll(int page, int size, SortType sort) {
        if (page<0)
            page=0;
        PageRequest pagination= null;
        switch (sort){
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.classEntityRepository.findAll(pagination).map(this::entityToResp);
    }


    private ClassEntityResponse entityToResp(ClassEntity entity){
        ClassEntityResponse classEntityResponse = new ClassEntityResponse();
        BeanUtils.copyProperties(entity,classEntityResponse);

        List<StudentBasicResponse> studentBasicResponseList = new ArrayList<>();
        List<LessonBasicResponse> lessonBasicResponseList = new ArrayList<>();
        classEntityResponse.setStudents(studentBasicResponseList);
        classEntityResponse.setLessons(lessonBasicResponseList);

        if(entity.getStudents() != null){

        }
        if(entity.getLessons() != null){

        }
        return classEntityResponse;
    }

    private ClassEntityBasicResponse entityToRespBasic(ClassEntity entity) {
        ClassEntityBasicResponse classEntityResponse = new ClassEntityBasicResponse();
        BeanUtils.copyProperties(entity, classEntityResponse);
        return classEntityResponse;
    }
    private ClassEntity requestToEntity(ClassEntityRequest request){
        ClassEntity classEntity = new ClassEntity();
        BeanUtils.copyProperties(request,classEntity);
        return classEntity;
    }
    private ClassEntity find(Long id){
        return this.classEntityRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("Clases"));
    }


    @Override
    public Page<ClassEntityBasicResponse> getAllBasic(int page, int size, SortType sort) {
        if (page<0)
            page=0;
        PageRequest pagination= null;
        switch (sort){
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.classEntityRepository.findAll(pagination).map(this::entityToRespBasic);
    }
}
