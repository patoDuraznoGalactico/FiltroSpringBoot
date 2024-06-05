package com.riwi.filtro_spring_boot.api.controllers;

import com.riwi.filtro_spring_boot.api.dto.request.LessonRequest;
import com.riwi.filtro_spring_boot.api.dto.request.MultimediaRequest;
import com.riwi.filtro_spring_boot.api.dto.request.StudentRequest;
import com.riwi.filtro_spring_boot.api.dto.response.LessonBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.LessonResponse;
import com.riwi.filtro_spring_boot.api.dto.response.StudentBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.StudentResponse;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.ILessonService;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.IStudentService;
import com.riwi.filtro_spring_boot.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/lessons")
@AllArgsConstructor
public class LessonController {

    @Autowired
    private final ILessonService lessonService;

    @GetMapping
    public ResponseEntity<Page<LessonBasicResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType,
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content
    ) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;
        return ResponseEntity.ok(this.lessonService.getAllBasic(page - 1, size, sortType, title,content));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<LessonResponse> get(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(this.lessonService.get(id));
    }

    @PostMapping
    public ResponseEntity<LessonResponse> insert(
            @Validated @RequestBody LessonRequest request,
            @RequestBody List<MultimediaRequest> requestMultimedia
    ) {
        return ResponseEntity.ok(this.lessonService.create(request, requestMultimedia));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<LessonResponse> update(
            @Validated @RequestBody LessonRequest request,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(this.lessonService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ){
        this.lessonService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/{id}/disable")
    public ResponseEntity<LessonResponse> disable(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.lessonService.disable(id));
    }
}
