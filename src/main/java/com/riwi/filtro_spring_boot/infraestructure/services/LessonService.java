package com.riwi.filtro_spring_boot.infraestructure.services;

import com.riwi.filtro_spring_boot.api.dto.request.LessonRequest;
import com.riwi.filtro_spring_boot.api.dto.request.MultimediaRequest;
import com.riwi.filtro_spring_boot.api.dto.request.StudentRequest;
import com.riwi.filtro_spring_boot.api.dto.response.*;
import com.riwi.filtro_spring_boot.domain.entities.ClassEntity;
import com.riwi.filtro_spring_boot.domain.entities.Lesson;
import com.riwi.filtro_spring_boot.domain.entities.Multimedia;
import com.riwi.filtro_spring_boot.domain.entities.Student;
import com.riwi.filtro_spring_boot.domain.repositories.ClassEntityRepository;
import com.riwi.filtro_spring_boot.domain.repositories.LessonRepository;
import com.riwi.filtro_spring_boot.domain.repositories.MultimediaRepository;
import com.riwi.filtro_spring_boot.domain.repositories.StudentRepository;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.ILessonService;
import com.riwi.filtro_spring_boot.utils.enums.SortType;
import com.riwi.filtro_spring_boot.utils.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LessonService implements ILessonService {

    @Autowired
    private final LessonRepository lessonRepository;
    @Autowired
    private final ClassEntityRepository classEntityRepository;
    @Autowired
    private final MultimediaRepository multimediaRepository;
    @Override
    public LessonResponse create(LessonRequest request, List<MultimediaRequest> requestMultimedia) {
        Lesson lesson = this.requestToEntity(request);
        ClassEntity classEntity = this.classEntityRepository.findById(request.getClassEntity()).orElseThrow(()-> new BadRequestException("Class"));
        lesson.setCreated_at(LocalDateTime.now());
        lesson.setActive(true);
        lesson.setClassEntity(classEntity);


        Lesson lessonnew = this.lessonRepository.save(lesson);
        if (requestMultimedia != null){
            requestMultimedia.stream()
                    .forEach(requestMulti -> {
                        Multimedia multimedia = this.requestToEntity(requestMulti);

                        multimedia.setCreated_at(LocalDateTime.now());
                        multimedia.setActive(true);
                        multimedia.setLesson(lessonnew);
                        this.entityToResp(this.multimediaRepository.save(multimedia));
                    });
        }
        return this.entityToResp(lessonnew);

    }

    @Override
    public LessonResponse create(LessonRequest request) {
        return null;
    }

    @Override
    public LessonResponse get(Long aLong) {
        return this.entityToResp(this.find(aLong));
    }

    @Override
    public LessonResponse update(LessonRequest request, Long aLong) {
        Lesson lesson = this.find(aLong);
        BeanUtils.copyProperties(request,lesson);
        return this.entityToResp(this.lessonRepository.save(lesson));
    }

    @Override
    public void delete(Long aLong) {
        Lesson lesson = this.find(aLong);
        this.lessonRepository.delete(lesson);
    }

    @Override
    public Page<LessonResponse> getAll(int page, int size, SortType sort) {
        return null;
    }

    private LessonResponse entityToResp(Lesson entity){
        LessonResponse lessonResponse = new LessonResponse();
        BeanUtils.copyProperties(entity,lessonResponse);
        ClassEntityBasicResponse classEntityResponse = new ClassEntityBasicResponse();
        BeanUtils.copyProperties(entity.getClassEntity(),classEntityResponse);
        lessonResponse.setClassEntity(classEntityResponse);
        List<MultimediaBasicResponse> multimediaBasicResponses = new ArrayList<>();
        lessonResponse.setMultimedia(multimediaBasicResponses);
        if(entity.getMultimedia() != null){
            List<MultimediaBasicResponse> multimediaBasicList = new ArrayList<>();
            multimediaBasicList = entity.getMultimedia().stream()
                    .map(multimedia -> {
                        MultimediaBasicResponse multimediaBasicResponse = new MultimediaBasicResponse();
                        BeanUtils.copyProperties(multimedia, multimediaBasicResponse);
                        return multimediaBasicResponse;
                    })
                    .toList();
            lessonResponse.setMultimedia(multimediaBasicList);
        }
        return lessonResponse;
    }
    private LessonBasicResponse entityToRespBasic(Lesson entity) {
        LessonBasicResponse lessonBasicResponse = new LessonBasicResponse();
        BeanUtils.copyProperties(entity, lessonBasicResponse);
        return lessonBasicResponse;
    }
    private Lesson requestToEntity(LessonRequest request){
        Lesson lesson = new Lesson();
        BeanUtils.copyProperties(request,lesson);
        return lesson;
    }
    private Lesson find(Long id){
        return this.lessonRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("Lesson"));
    }

    public Page<LessonBasicResponse> getAllBasic(int page, int size, SortType sort, String title, String content){
        if (page<0)
            page=0;
        PageRequest pagination= null;
        switch (sort){
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).descending());
        }
        Page<LessonBasicResponse> pageResponse = this.lessonRepository.findAll(pagination).map(this::entityToRespBasic);
        pageResponse = pageResponse
                .map(response -> {
                    if (response.getActive() == true){
                        if (response.getTitle().contains(title) && (response.getContent().contains(content))){
                            return response;
                        }
                    }
                    return null;
                });
        return pageResponse;
    }
    public LessonResponse disable(Long aLong) {
        Lesson lesson = this.find(aLong);
        lesson.setActive(false);
        return this.entityToResp(this.lessonRepository.save(lesson));
    }

    //------------------------------------- MULTIMEDIA--------------------------------

    private MultimediaResponse entityToResp(Multimedia entity){
        MultimediaResponse multimediaResponse = new MultimediaResponse();
        BeanUtils.copyProperties(entity,multimediaResponse);
        LessonBasicResponse lessonBasicResponse = new LessonBasicResponse();
        BeanUtils.copyProperties(entity.getLesson(),lessonBasicResponse);
        multimediaResponse.setLesson(lessonBasicResponse);
        return multimediaResponse;
    }

    private Multimedia requestToEntity(MultimediaRequest request){
        Multimedia multimedia = new Multimedia();
        BeanUtils.copyProperties(request,multimedia);
        return multimedia;
    }
}
