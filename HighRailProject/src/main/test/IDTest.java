import org.springframework.beans.factory.annotation.Autowired;

import com.example.dao.DaoImplMySQL;
import com.example.entity.Tran;

public class IDTest {
	
	public static void main(String[] args) {
		
		
		
		DaoImplMySQL dao = null;
		
		Tran tran = new Tran();
     	tran.setTranNo(011);
     	tran.setDepartureStation("南港");
     	tran.setArrivalStation("彰化");
     	tran.setDate("2024-01-30");
     	tran.setDepartureTime("07:00");
     	tran.setArrivalTime("08:27");
     	
//		int tranID = dao.addTran(tran);
		
//		System.out.println(tranID);
	}

}
