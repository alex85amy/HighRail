package com.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainTime {

	private String tranNo;
	
	private String startingStationName;
	
	private String endingStationName;
	
	private String departureDate;
	
	private String departureTime;
	
	private String arrivalTime;
	
	private String price;

}
