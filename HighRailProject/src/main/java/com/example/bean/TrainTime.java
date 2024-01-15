package com.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.google.gson.Gson;

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
	

	public String getJson() {
		return new Gson().toJson(this);
	}
}
