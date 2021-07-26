package com.qinxiu.jwtlogin;

import org.junit.Test;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class encryptTest {

  @Test
  public void encriptTest(){

    // generates a random 8-byte salt that is then hex-encoded
    String salt = KeyGenerators.string().generateKey();
    //
    String password = "your password";

    // Use the Encryptors.standard factory method to construct a "standard" BytesEncryptor
    BytesEncryptor bytesEncryptor = Encryptors.standard(password, salt);

    byte[] text = "0123456789中文汉字，这是待加密的文字。".getBytes();

    byte[] encrypted = bytesEncryptor.encrypt(text); // 加密
    System.out.println(new String(encrypted)); // 密文

    byte[] decrypted = bytesEncryptor.decrypt(encrypted); // 解密
    System.out.println(new String(decrypted)); // 明文
  }


  @Test
  public void directEncryptTest(){

    // generates a random 8-byte salt that is then hex-encoded
    String salt = KeyGenerators.string().generateKey();
    //
    String password = "your password";

    // Use the Encryptors.standard factory method to construct a "standard" BytesEncryptor
    TextEncryptor textEncryptor = Encryptors.text(password, salt);

    String encryptedText = textEncryptor.encrypt("buenos días");
    System.out.println(encryptedText);
    System.out.println(textEncryptor.decrypt(encryptedText));
  }

}
