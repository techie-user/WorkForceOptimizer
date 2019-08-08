package com.interview.serviceone.service;

import com.interview.serviceone.valueobject.OptimizedWorkForce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkForceOptimizerServiceImpl implements WorkForceOptimizerService {

    private static final Logger logger = LoggerFactory.getLogger(WorkForceOptimizerServiceImpl.class);

    private static final int FORCED_EVALUATION_INDEX = 1;
    private static final int INITIAL_INDEX = 1;

    @Override
    public List<OptimizedWorkForce> getOptimizedWorkForce(Integer[] structures, int srCapacity, int jrCapacity) {

        List<OptimizedWorkForce> results = new ArrayList<>();

        for(int rooms: structures) {
           results.add(findOptimizedWorkForce(rooms, srCapacity, jrCapacity, INITIAL_INDEX));
        }
        logger.debug("Optimized Workforces :: " + results);
        return results;
    }

    private OptimizedWorkForce findOptimizedWorkForce(int rooms, int srCapacity, int jrCapacity, int reservedSr) {

        OptimizedWorkForce result  = null;

        int remainingRooms = rooms - (srCapacity*reservedSr);
        int maximumRequiredSeniors = (remainingRooms/srCapacity) + FORCED_EVALUATION_INDEX;
        int maximumRequiredJuniors = (remainingRooms/jrCapacity) + FORCED_EVALUATION_INDEX;

        int maximumCapacity = (maximumRequiredSeniors * srCapacity) + (maximumRequiredJuniors * jrCapacity);

        for(int senior = 0; senior <= maximumRequiredSeniors; senior++) {
            for(int junior = 0; junior <= maximumRequiredJuniors; junior++) {

                int allottedCapacity = (senior*srCapacity) + (junior * jrCapacity);

                if(allottedCapacity >= remainingRooms) {
                    if(allottedCapacity <= maximumCapacity) {
                        maximumCapacity = allottedCapacity;
                        result = new OptimizedWorkForce(rooms, (senior+reservedSr), junior);
                    }
                }
            }
        }
        return result;
    }
}
