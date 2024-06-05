package com.riwi.filtro_spring_boot.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
//@Tag(name = "Order Controller", description = "Order Management System")
@RestController
@RequestMapping(path = "/user")
@AllArgsConstructor
public class ControllerGeneric {
    //http://localhost:8080/swagger-ui.html
    // @Autowired
    // private final IUsersServices userService;
    
    //@Operation(summary = "Get all users", description = "Retrieve a list of all users")
    // @GetMapping
    // public ResponseEntity<Page<GenericResponse>> getAll(
    //         @RequestParam(defaultValue = "1") int page,
    //         @RequestParam(defaultValue = "10") int size,
    //         @RequestHeader(required = false) SortType sortType
    // ) {
    //     if (Objects.isNull(sortType))
    //         sortType = SortType.NONE;
    //     return ResponseEntity.ok(this.userService.getAll(page - 1, size, sortType));
    // }

    // @GetMapping(path = "/{id}")
    // public ResponseEntity<UsersResponse> get(
    //         @PathVariable int id
    // ) {
    //     return ResponseEntity.ok(this.userService.get(id));
    // }

    // @PostMapping
    // public ResponseEntity<UsersResponse> insert(
    //         @Validated @RequestBody UsersRequest request) {
    //     System.out.println(request);
    //     return ResponseEntity.ok(this.userService.create(request));
    // }

    // @PutMapping(path = "/{id}")
    // public ResponseEntity<UsersResponse> update(
    //         @Validated @RequestBody UsersRequest request,
    //         @PathVariable int id
    // ) {
    //     return ResponseEntity.ok(this.userService.update(request, id));
    // }

    // @DeleteMapping(path = "/{id}")
    // public ResponseEntity<Void> delete(
    //         @PathVariable int id
    // ){
    //     this.userService.delete(id);
    //     return ResponseEntity.noContent().build();
    // }
}
