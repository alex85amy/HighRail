import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.dao.DaoImplMySQL;
import com.example.entity.Ticket;

public class SpringTest {

	public static void main(String[] args) {
	
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/WEB-INF/springmvc-servlet.xml");

		DaoImplMySQL dao = ctx.getBean("daoImplMySQL", DaoImplMySQL.class);
		
		List<Ticket> tickets = dao.findAllTicketsByUserId(501);
		
		System.out.println(tickets);
	}

}
