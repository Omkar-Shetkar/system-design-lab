package com.example.web;

import com.example.model.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")
public class CoordinatorController {

    private final CoordinatorService coordinatorService;

    public CoordinatorController(CoordinatorService coordinatorService) {
        this.coordinatorService = coordinatorService;
    }

    @PostMapping
    public void addData(@RequestBody Data data) {
        coordinatorService.add(data);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteData(@RequestParam("key") String key) {
        if (coordinatorService.delete(key)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
