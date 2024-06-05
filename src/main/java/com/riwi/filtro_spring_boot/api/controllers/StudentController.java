package com.riwi.filtro_spring_boot.api.controllers;

import com.riwi.filtro_spring_boot.api.dto.request.ClassEntityRequest;
import com.riwi.filtro_spring_boot.api.dto.request.StudentRequest;
import com.riwi.filtro_spring_boot.api.dto.response.ClassEntityBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.ClassEntityResponse;
import com.riwi.filtro_spring_boot.api.dto.response.StudentBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.StudentResponse;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.IClassEntityService;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.IStudentService;
import com.riwi.filtro_spring_boot.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path = "/Students")
@AllArgsConstructor
public class StudentController {
    @Autowired
    private final IStudentService studentService;

    @GetMapping
    public ResponseEntity<Page<StudentBasicResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String email
    ) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;
        return ResponseEntity.ok(this.studentService.getAllBasic(page - 1, size, sortType, name,email));
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentResponse> get(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(this.studentService.get(id));
    }

    @PostMapping
    public ResponseEntity<StudentResponse> insert(
            @Validated @RequestBody StudentRequest request) {
        return ResponseEntity.ok(this.studentService.create(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<StudentResponse> update(
            @Validated @RequestBody StudentRequest request,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(this.studentService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ){
        this.studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/{id}/disable")
    public ResponseEntity<StudentResponse> disable(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.studentService.disable(id));
    }
}
