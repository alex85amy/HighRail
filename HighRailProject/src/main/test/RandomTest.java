import java.util.Random;

public class RandomTest {
	public static void main(String[] args) {
		
		int i;
		i = (int) (Math.random()*10)+1;
		Integer iInteger = Integer.valueOf(i);
		
		Random r = new Random();
		char c = (char)(r.nextInt(5) + 'a');
		String s = Character.toString(c);
		
		System.out.println(iInteger);
	}
}
