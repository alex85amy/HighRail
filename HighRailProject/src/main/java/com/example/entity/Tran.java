package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tran {
    private Integer tranId; 
    private Integer tranNo; 
    private String date;
    private String departureStation;
    private String arrivalStation;
    private String departureTime;
    private String arrivalTime;
    
}
