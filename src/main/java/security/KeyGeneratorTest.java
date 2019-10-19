package security;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class KeyGeneratorTest
{
    public static void main(String[] args)
    {
        try {
            Key key;
            SecureRandom secureRandom = new SecureRandom();
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256, secureRandom);
            key = keyGenerator.generateKey();

            byte[] bkey = key.getEncoded();
            CharSequence b64ScanStreamKey = Base64.getEncoder().encode(bkey).toString();
            System.out.println("key: " + Base64.getEncoder().encodeToString(bkey));
            System.out.println("DYOUM try");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("DYOUM catch");
            e.printStackTrace();
        }
        System.out.println("DYOUM out");
    }
}
