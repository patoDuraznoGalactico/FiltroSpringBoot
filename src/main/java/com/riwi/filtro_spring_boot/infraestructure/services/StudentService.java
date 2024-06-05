package com.riwi.filtro_spring_boot.infraestructure.services;

import com.riwi.filtro_spring_boot.api.dto.request.ClassEntityRequest;
import com.riwi.filtro_spring_boot.api.dto.request.StudentRequest;
import com.riwi.filtro_spring_boot.api.dto.response.*;
import com.riwi.filtro_spring_boot.domain.entities.ClassEntity;
import com.riwi.filtro_spring_boot.domain.entities.Student;
import com.riwi.filtro_spring_boot.domain.repositories.ClassEntityRepository;
import com.riwi.filtro_spring_boot.domain.repositories.StudentRepository;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.IStudentService;
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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService implements IStudentService {

    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final ClassEntityRepository classEntityRepository;
    @Override
    public StudentResponse create(StudentRequest request) {
        Student student = this.requestToEntity(request);
        ClassEntity classEntity = this.classEntityRepository.findById(request.getClassEntity()).orElseThrow(()-> new BadRequestException("Class"));
        student.setCreated_at(LocalDateTime.now());
        student.setActive(true);
        student.setClassEntity(classEntity);
        return this.entityToResp(this.studentRepository.save(student));
    }

    @Override
    public StudentResponse get(Long aLong) {
        return this.entityToResp(this.find(aLong));
    }

    @Override
    public StudentResponse update(StudentRequest request, Long aLong) {
        Student student = this.find(aLong);
        BeanUtils.copyProperties(request,student);
        return this.entityToResp(this.studentRepository.save(student));
    }

    @Override
    public void delete(Long aLong) {
        Student student = this.find(aLong);
        this.studentRepository.delete(student);
    }

    @Override
    public Page<StudentResponse> getAll(int page, int size, SortType sort) {
        return null;
    }

    private StudentResponse entityToResp(Student entity){
        StudentResponse studentResponse = new StudentResponse();
        BeanUtils.copyProperties(entity,studentResponse);
        ClassEntityBasicResponse classEntityResponse = new ClassEntityBasicResponse();
        BeanUtils.copyProperties(entity.getClassEntity(),classEntityResponse);
        studentResponse.setClassEntity(classEntityResponse);
        return studentResponse;
    }
    private StudentBasicResponse entityToRespBasic(Student entity) {
        StudentBasicResponse studentBasicResponse = new StudentBasicResponse();
        BeanUtils.copyProperties(entity, studentBasicResponse);
        return studentBasicResponse;
    }
    private Student requestToEntity(StudentRequest request){
        Student student = new Student();
        BeanUtils.copyProperties(request,student);
        return student;
    }
    private Student find(Long id){
        return this.studentRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("Student"));
    }


    @Override
    public Page<StudentBasicResponse> getAllBasic(int page, int size, SortType sort, String name, String email) {
        if (page<0)
            page=0;
        PageRequest pagination= null;
        switch (sort){
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).descending());
        }
        Page<StudentBasicResponse> pageResponse = this.studentRepository.findAll(pagination).map(this::entityToRespBasic);
        pageResponse = pageResponse
                .map(response -> {
                    if (response.getActive() == true){
                        if (response.getName().contains(name) && (response.getEmail().contains(email))){
                            return response;
                        }
                    }
                    return null;
                });
        return pageResponse;
    }

    public StudentResponse disable(Long aLong) {
        Student student = this.find(aLong);
        student.setActive(false);
        return this.entityToResp(this.studentRepository.save(student));
    }
}
