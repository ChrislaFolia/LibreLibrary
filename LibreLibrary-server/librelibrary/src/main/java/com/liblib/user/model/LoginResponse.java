package com.liblib.user.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class LoginResponse {

    private boolean status = false;
    private String token = "";
    private String phoneNumber = "";
    private int userId = -1;
    private String userName = "";
    
    public String toJSONString(){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(this);
        }catch (Exception exception){
            try {
                throw exception;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return json;
    }
	
}
