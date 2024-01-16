import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import com.example.util.KeyUtil;

public class Test {

	// 建立一個 AES key(256bits, 32bytes)
	private static final String KEY = "0123456789abcdef0123456789abcdef"; // 32 個字

	// 建立 SecureRandom
	private static final SecureRandom SECURE_RANDOM = new SecureRandom();

	public static void main(String[] args) throws Exception {

		System.out.println("AES-256 加密範例:");
		String originalText = "pass789"; // 原始明文
		System.out.println("原始明文: " + originalText);
		System.out.println("------------------------------------------------------------");

		// 利用 AES 進行加密
		// 1. 建立 AES 密鑰規範
		SecretKeySpec aesKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
		
		// CBC 模式

		System.out.println("CBC 模式");
		// 建立隨機 IV
		byte[] ivCBC = new byte[16];
		SECURE_RANDOM.nextBytes(ivCBC); // 產生一個隨機的 byte[] 資訊當作 IV
		System.out.println("隨機 CBC IV:" + Base64.getEncoder().encodeToString(ivCBC));

		// 進行資料加密(將明文進行 AES-CBC 加密)
		byte[] encryptedCBC = KeyUtil.encryptWithAESKeyAndIV(aesKeySpec, originalText, ivCBC);
		System.out.println("加密後的訊息(Base64):" + Base64.getEncoder().encodeToString(encryptedCBC));

		// 進行資料解密
		String decryptedCBC = KeyUtil.decryptWithAESKeyAndIV(aesKeySpec, encryptedCBC, ivCBC);
		System.out.println("解密後的訊息:" + decryptedCBC);

	}
}
