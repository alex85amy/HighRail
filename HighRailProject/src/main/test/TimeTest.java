
import java.util.List;

import com.example.bean.TrainTime;
import com.example.util.TimeTableAPI;


public class TimeTest {

	 public static void main(String[] args) throws Exception {
//		 String timeTable = TimeTDXApi.getTimeTable("1000", "1070", "2024-01-19");		 
//		    JsonArray jsonArray = JsonParser.parseString(timeTable).getAsJsonArray();
//
//		        for (int i = 0; i < jsonArray.size(); i++) {
//		            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
//		            JsonObject originStopTime = jsonObject.getAsJsonObject("OriginStopTime");
//		            String departureTime = originStopTime.getAsJsonPrimitive("DepartureTime").getAsString();
//
//		            System.out.println("Departure Time: " + departureTime);
//		        }
			List<TrainTime> timeTable = TimeTableAPI.getTrainTimes("1000", "1070", "2024-02-19");
		//	String test = TimeTableAPI.departureTime(timeTable);
			System.out.println(timeTable);
	}
}
