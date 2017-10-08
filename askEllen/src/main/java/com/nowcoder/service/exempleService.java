package com.nowcoder.service;

import org.springframework.stereotype.Service;

@Service
public class  exempleService {
    public String serviceTest(String id){
        return "hello "+ id;
    }
}
