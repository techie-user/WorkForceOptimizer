package com.interview.serviceone.service;

import com.interview.serviceone.valueobject.OptimizedWorkForce;

import java.util.List;

public interface WorkForceOptimizerService {

    List<OptimizedWorkForce> getOptimizedWorkForce(Integer[]structures, int srCapacity, int jrCapacity);
}
