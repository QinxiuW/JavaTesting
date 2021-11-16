package com.qinxiu.jwtlogin;

import com.auth0.jwt.algorithms.Algorithm;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import org.junit.Test;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.rsa.crypto.RsaAlgorithm;
import org.springframework.security.rsa.crypto.RsaSecretEncryptor;
import java.security.PrivateKey;
import java.security.PublicKey;

public class encryptTest {

  @Test
  public void encriptTest() {

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
  public void directEncryptTest() {

    // generates a random 8-byte salt that is then hex-encoded
    String salt = KeyGenerators.string().generateKey();
    System.out.println(salt);
    //
    String password = "your password";

    // Use the Encryptors.standard factory method to construct a "standard" BytesEncryptor
    TextEncryptor textEncryptor = Encryptors.text(password, salt);

    String encryptedText = textEncryptor.encrypt("buenos días");
    System.out.println(encryptedText);
    System.out.println(textEncryptor.decrypt(encryptedText));
  }

  @Test
  public void keyPairGenerate() throws NoSuchAlgorithmException, IOException {
    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
    kpg.initialize(2048);
    KeyPair keypair = kpg.generateKeyPair();
    Base64.Encoder encoder = Base64.getEncoder();

    System.out.println("privateKey: " + encoder.encodeToString(keypair.getPrivate().getEncoded()));
    System.out.println("privatekey format: " + keypair.getPrivate().getFormat());
    System.out.println("publicKey: " + encoder.encodeToString(keypair.getPublic().getEncoded()));
    System.out.println("publicKey format: " + keypair.getPublic().getFormat());
  }

  private TextEncryptor buildEncryptor() throws NoSuchAlgorithmException, InvalidKeySpecException {

    Base64.Decoder decoder = Base64.getDecoder();
    byte[] publickeyBytes = decoder.decode(
        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0/J45kLpm6BYLZ0sEDY2QAfo8EVnaxeb8ERMt3isDT4NsuyCYWtRyXb/lPnC+UNYCqfWl9lH965HAR+cOq2kjXbZMxg77bdN+WM6gwv90RchCLdJ7aXS6F87IAOxzt8f8pNqlW0sr06MTiFPmIwdmyp3yZJk6bUZID/DkjoLE2c2W0rioW/4ZurfnZ9jXnLj0ifr0R80duk6Jvca/PvTcTLCurssFaRUENCobeKJJlbC0/P4jaaEuGlwSqzEfl06fDQgCx8ACQCbtVOTs3VgnOsZ2MGYTG9fPCySMxSrlM5Ig6PaWQ03UdyQ1QEk3xyhy2nlVg1Sa8kaL/2k0H1G8QIDAQAB");
    byte[] privatekeyBytes = decoder.decode(
        "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDT8njmQumboFgtnSwQNjZAB+jwRWdrF5vwREy3eKwNPg2y7IJha1HJdv+U+cL5Q1gKp9aX2Uf3rkcBH5w6raSNdtkzGDvtt035YzqDC/3RFyEIt0ntpdLoXzsgA7HO3x/yk2qVbSyvToxOIU+YjB2bKnfJkmTptRkgP8OSOgsTZzZbSuKhb/hm6t+dn2NecuPSJ+vRHzR26Tom9xr8+9NxMsK6uywVpFQQ0Kht4okmVsLT8/iNpoS4aXBKrMR+XTp8NCALHwAJAJu1U5OzdWCc6xnYwZhMb188LJIzFKuUzkiDo9pZDTdR3JDVASTfHKHLaeVWDVJryRov/aTQfUbxAgMBAAECggEADEhjBUUfcKtsMMiJAy+BGVjcAQCkq6okmVn78C4IGoRcGuBl9etYRAqnaSTITdW/Rm7PmuE2sH3jE4eXcDcb8C7RC4r9/hq/ZBKCE5IgVXu3qF+m4yTXAixyqrpMFacMnOw6Iwq+qjItI2H+0OJd3N4rLv1S59Bc8md/l/vRJICdvY/6yohW+xkMr3G0LteW8kN8WaT7N7RNcgy8xJz9+CsMbDfVOCD5Rtasa65/U//Gy5Oszs/zRGs60rurZp+UD9mvEQS0pnymH5/Y9qKLVBYBXpIdAvK5VEgUMbwdX8CBAH3v+6sOnBunZadCj7vsGvgYRu9bIeTvSEp+8PNjGQKBgQDveYDvFpaRtZgfuYIVhyDQrSmNHpd9kYPg9NwyVB3Y2uZmQOFgEiCirFRr79dDYJuLPdkwndvPtwB/9WsXxD/vda8vmzUWUy9KWdw99/tKTJbbzObVhGKC22GIWW8Pfca7C3V9glhrb/c14RiJbE6bbEq16jYPc0p4+Z83I+TYhwKBgQDikqzxntv+yzRMMVVGQUt7w+ckP9qQKPeJkWbioxYniOI6SMN07jOoPP1une59jKIW3yOEf/wtGCivwzn7TNqm3jqZK0/ZlnRk4tCsPxUeXBotSpM0wd3t1difOtKPj9NUyHaWbZ7wVjefE9BTzGtbPYqLBWljgoiGtRpm+JraxwKBgBapjvQRVSpw3vaRnaff49EbsgTy0i9F6A8P3Q6+OxzcilU+6pUm4gPC/zI/vkVYDSA+OCGLrtOXY+q4FOxo3AwTPQLqUznLoXwNyPPsVdGQMe6XE3k9T/1GOZKntATpSDdHPFUmUqvIKsLkjGfk4gN/WgqhIYCQzoloRhL3yJ5dAoGAMM4RjNaAYwkYkdUKnmCQRu+zCX7rsKPydmKou+cPbaKxwIZN/BEXdYT1vm8soUS64ZxdRTxgcMJEeV/fTH+IletGyhmnUKJ1HgoxkvjfrrN+PAVVYkbkbEflKp98HcqIZjnsOYkRdInN32lgNbzR30yJXFrlEbdbe1DliArDKJ8CgYBtA0xJVWmQJX6zz1CJHvU+EoJ7o1FRGC4QG6r/MIJOjcPHUtumEt3iBr+z6v/BB+oV9nlLqLPWDJokjX6AiKClkpHXFvXlTfEsDwyJO/QA2wzO8iZZPTsb9Md2mulkTPGb5bDHsGsJpiMd/uVwxTnVbYWVVWie2jmsBsjxk5ix3A==");
    KeyFactory kf = KeyFactory.getInstance("RSA");

    PublicKey publicKey = kf.generatePublic(new X509EncodedKeySpec(publickeyBytes));

    PrivateKey privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(privatekeyBytes));

    return new RsaSecretEncryptor("UTF-8", publicKey, privateKey,
        RsaAlgorithm.DEFAULT);
  }


  @Test
  public void asyncEncrypt() throws NoSuchAlgorithmException, InvalidKeySpecException {
    TextEncryptor encryptor = buildEncryptor();
    String text = "hello world";
    System.out.println(encryptor.encrypt(text));
  }

  @Test
  public void asyncDecrypt() throws InvalidKeySpecException, NoSuchAlgorithmException {
    TextEncryptor encryptor = buildEncryptor();
    String text = "AQAMuHBsve+p15OnmvFwKQ76mjYoGpMmK4pnI2LJe6Y5zzHyR8WoebpBZvy47KamL3AXLWjN1qTS7GkgcSjTQt5q94uc5ql7OdMwLsmOharDAS4plmQNO1vXygt9iyQrLbpCn7T0zL0cxStM8k4NIDmcMYbvF9TjZhdj1lx2QsSxWI9w3ppbAGL2cyT4Ce9rz3NiBcxS4lcJyQYITYAzQ8uEw0vcycp0NSbB8N80IXdgIa2ggWCSlyVKl4Cit3YK9f6XtB2PdrpaSw6KOly2OhLjfAuCzoTTMlM7mOP2cvKtJqsgZ0gcBvgey4fQu/td+7m1dUgeKCIpN3mfY57W8eHK+3OnWgw+0K/Azt/psMSEbxuJ/LF2afv3SPbD2ZZ2YkM=";
    System.out.println(encryptor.decrypt(text));
  }

}
