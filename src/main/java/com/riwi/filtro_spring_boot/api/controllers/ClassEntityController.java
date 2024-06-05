package com.riwi.filtro_spring_boot.api.controllers;

import com.riwi.filtro_spring_boot.api.dto.request.ClassEntityRequest;
import com.riwi.filtro_spring_boot.api.dto.response.ClassEntityBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.ClassEntityResponse;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.IClassEntityService;
import com.riwi.filtro_spring_boot.utils.enums.SortType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path = "/Class")
@AllArgsConstructor
public class ClassEntityController {
    @Autowired
    private final IClassEntityService classEntityService;

     @GetMapping
     public ResponseEntity<Page<ClassEntityBasicResponse>> getAll(
             @RequestParam(defaultValue = "1") int page,
             @RequestParam(defaultValue = "10") int size,
             @RequestHeader(required = false) SortType sortType
     ) {
         if (Objects.isNull(sortType))
             sortType = SortType.NONE;
         return ResponseEntity.ok(this.classEntityService.getAllBasic(page - 1, size, sortType));
     }


     @GetMapping(path = "/{id}")
     public ResponseEntity<ClassEntityResponse> get(
             @PathVariable Long id
     ) {
         return ResponseEntity.ok(this.classEntityService.get(id));
     }

     @PostMapping
     public ResponseEntity<ClassEntityResponse> insert(
             @Validated @RequestBody ClassEntityRequest request) {
         return ResponseEntity.ok(this.classEntityService.create(request));
     }

     @PutMapping(path = "/{id}")
     public ResponseEntity<ClassEntityResponse> update(
             @Validated @RequestBody ClassEntityRequest request,
             @PathVariable Long id
     ) {
         return ResponseEntity.ok(this.classEntityService.update(request, id));
     }

     @DeleteMapping(path = "/{id}")
     public ResponseEntity<Void> delete(
             @PathVariable Long id
     ){
         this.classEntityService.delete(id);
         return ResponseEntity.noContent().build();
     }
}
