package com.example.util;

import java.util.ArrayList;
import java.util.List;

import com.example.bean.TrainTime;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TimeTableAPI {
	
	
	public static void main(String[] args) throws Exception {
		
		List<TrainTime> trainTimes = getTrainTimes("1000", "1070", "2024-01-12");
		 
		System.out.println(trainTimes);
	}
	
	public static List<TrainTime> getTrainTimes(String fromStation, String toStation,String departureDate) throws Exception {
		String timeTable = TimeTableAPI.timeTable(fromStation, toStation, departureDate);
		List<TrainTime> trainTimes = new ArrayList<>();
		String startingStationName = startingStationName(timeTable);
		String endingStationName = endingStationName(timeTable);	
		JsonArray jsonArray = JsonParser.parseString(timeTable).getAsJsonArray();
		 for (int i = 0; i < jsonArray.size(); i++) {
			 
			 JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
	         JsonObject dailyTrainInfo = jsonObject.getAsJsonObject("DailyTrainInfo");
	         JsonObject originStopTime = jsonObject.getAsJsonObject("OriginStopTime");
	         JsonObject destinationStopTime = jsonObject.getAsJsonObject("DestinationStopTime");
	        
	         String tranNumber = dailyTrainInfo.getAsJsonPrimitive("TrainNo").getAsString();
	         String departureTime = originStopTime.getAsJsonPrimitive("DepartureTime").getAsString();
	         String arrivalTime = destinationStopTime.getAsJsonPrimitive("ArrivalTime").getAsString();
	         
	         TrainTime trainTime = new TrainTime();
	         trainTime.setTranNo(tranNumber);
	         trainTime.setStartingStationName(startingStationName);
	         trainTime.setEndingStationName(endingStationName);
	         trainTime.setDepartureTime(departureTime);
	         trainTime.setArrivalTime(arrivalTime);
	         trainTime.setDepartureDate(departureDate);
			 trainTimes.add(trainTime);
		 }
		 return trainTimes;
	}
	
	public static  String timeTable(String fromStation, String toStation,String departureDate) throws Exception {
		
		return TimeTDXApi.getTimeTable(fromStation, toStation, departureDate);
	}
	
	public static String departureTime(String timeTable) throws Exception {
	    try {
			JsonArray jsonArray = JsonParser.parseString(timeTable).getAsJsonArray();
			JsonArray newJsonArray = new JsonArray();
	
		        for (int i = 0; i < jsonArray.size(); i++) {
		            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
		            JsonObject originStopTime = jsonObject.getAsJsonObject("OriginStopTime");
		            String departureTime = originStopTime.getAsJsonPrimitive("DepartureTime").getAsString();
	
//		            JsonObject newJsonObject = new JsonObject();
//		            newJsonObject.addProperty("DepartureTime", departureTime);
		            newJsonArray.add(departureTime);
		        }

		        // 将新的JsonArray转换为字符串并返回
		        return newJsonArray.toString();
		        
	    	} catch (Exception e) {
		        // 捕获任何异常并返回错误消息
		        return "Error extracting and processing timeTable. Reason: " + e.getMessage();
	    }
	}
	
	public static String arrivalTime(String timeTable) throws Exception {
	    try {
			JsonArray jsonArray = JsonParser.parseString(timeTable).getAsJsonArray();
			JsonArray newJsonArray = new JsonArray();
	
		        for (int i = 0; i < jsonArray.size(); i++) {
		            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
		            JsonObject originStopTime = jsonObject.getAsJsonObject("DestinationStopTime");
		            String arrivalTime = originStopTime.getAsJsonPrimitive("ArrivalTime").getAsString();
	
//		            JsonObject newJsonObject = new JsonObject();
//		            newJsonObject.addProperty("ArrivalTime", arrivalTime);
		            newJsonArray.add(arrivalTime);
		        }

		        // 将新的JsonArray转换为字符串并返回
		        return newJsonArray.toString();
		        
	    	} catch (Exception e) {
		        // 捕获任何异常并返回错误消息
		        return "Error extracting and processing timeTable. Reason: " + e.getMessage();
	    }
	}
	
	public static String tranNumber(String timeTable) throws Exception {
	    try {
			JsonArray jsonArray = JsonParser.parseString(timeTable).getAsJsonArray();
			JsonArray newJsonArray = new JsonArray();
	
		        for (int i = 0; i < jsonArray.size(); i++) {
		            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
		            JsonObject originStopTime = jsonObject.getAsJsonObject("DailyTrainInfo");
		            String tranNumber = originStopTime.getAsJsonPrimitive("TrainNo").getAsString();
	
//		            JsonObject newJsonObject = new JsonObject();
//		            newJsonObject.addProperty("TrainNo", tranNumber);
		            newJsonArray.add(tranNumber);
		        }

		        // 将新的JsonArray转换为字符串并返回
		        return newJsonArray.toString();
		        
	    	} catch (Exception e) {
		        // 捕获任何异常并返回错误消息
		        return "Error extracting and processing timeTable. Reason: " + e.getMessage();
	    }
	}
	
	public static String startingStationName(String timeTable) throws Exception {
		String startingStationName = JsonParser.parseString(timeTable).getAsJsonArray().get(0)
				.getAsJsonObject().get("OriginStopTime")
				.getAsJsonObject().get("StationName")
				.getAsJsonObject().get("Zh_tw")
				.getAsString();
		
		return startingStationName;
	}
	
	public static String endingStationName(String timeTable) throws Exception {
		String endingStationName = JsonParser.parseString(timeTable).getAsJsonArray().get(0)
				.getAsJsonObject().get("DestinationStopTime")
				.getAsJsonObject().get("StationName")
				.getAsJsonObject().get("Zh_tw")
				.getAsString();
		
		return endingStationName;
	}
	
}
