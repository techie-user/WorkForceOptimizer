package com.interview.serviceone.controller;

import com.interview.serviceone.errors.InvalidValueException;
import com.interview.serviceone.service.WorkForceOptimizerService;
import com.interview.serviceone.valueobject.OptimizedWorkForce;
import com.interview.serviceone.valueobject.StructureWorkForce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class WorkForceOptimizeController {

    final static int LOWER_BOUND = 1;
    final static int UPPER_BOUND = 100;

    @Autowired
    private WorkForceOptimizerService workForceOptimizerService;

    @PostMapping(value = "/workforce", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<OptimizedWorkForce>> optimizeWorkers(@RequestBody @Valid StructureWorkForce input) {
        Integer[] rooms = input.getRooms();
        int srCapacity = input.getSeniorCapacity();
        int jrCapacity = input.getJuniorCapacity();

        //Fail fast - Validate input
        boolean isValid =  Arrays.stream(rooms).allMatch(n -> (n >= LOWER_BOUND && n <= UPPER_BOUND));
        if(!isValid) {
            throw new InvalidValueException("Rooms Range should be between 1-100");
        }
        List<OptimizedWorkForce> result = workForceOptimizerService.getOptimizedWorkForce(rooms, srCapacity, jrCapacity);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}