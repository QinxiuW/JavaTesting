package com.qinxiu.jwtlogin;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

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
        //
        String password = "your password";

        // Use the Encryptors.standard factory method to construct a "standard" BytesEncryptor
        TextEncryptor textEncryptor = Encryptors.text(password, salt);

        String encryptedText = textEncryptor.encrypt("buenos días");
        System.out.println(encryptedText);
        System.out.println(textEncryptor.decrypt(encryptedText));
    }


    @Test
    public void keyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.genKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        String publicK = Base64.toBase64String(publicKey.getEncoded());
        String privateK = Base64.toBase64String(privateKey.getEncoded());

        System.out.println("public key: " + publicK);
        System.out.println("private key: " + privateK);
    }

    @Test
    public void checkKeyPair() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
      String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmeKsdE5ix6bl86T1lCLlRXU759gSDjb1jVAuX1K9wTcEjwD+FJRQF9+4ZxznqMJVtCQdfhlc1KpA3LmMwlqlawL9GMvp9/yK4WIwK9MDqLlA9bzAiEYvSPR8NeKSLBmpkUeL3QAMB/pIwdi36+QxxVbcnagJTR66QBqSKsWfVKSbgd0R8COPaQ6nfuMA+80gpkyePhcYzZY5vW7PalXWyrREU6ljXRGQhWqyZLKyI3wcAqoU2bA60PEXB88uz9q/0Wy5UXENp/i6k3u3ms3f17UfGE14IrpWcM+zI4taKgNOx7TwvamnAqr+qoFiH0cil3/t/3iidgOfsqPLOr5VmwIDAQAB";
      String privateKeyStr = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCZ4qx0TmLHpuXzpPWUIuVFdTvn2BIONvWNUC5fUr3BNwSPAP4UlFAX37hnHOeowlW0JB1+GVzUqkDcuYzCWqVrAv0Yy+n3/IrhYjAr0wOouUD1vMCIRi9I9Hw14pIsGamRR4vdAAwH+kjB2Lfr5DHFVtydqAlNHrpAGpIqxZ9UpJuB3RHwI49pDqd+4wD7zSCmTJ4+FxjNljm9bs9qVdbKtERTqWNdEZCFarJksrIjfBwCqhTZsDrQ8RcHzy7P2r/RbLlRcQ2n+LqTe7eazd/XtR8YTXgiulZwz7Mji1oqA07HtPC9qacCqv6qgWIfRyKXf+3/eKJ2A5+yo8s6vlWbAgMBAAECggEALHG7PRwmi2M4ptXN16r8bMMfOia9kD1vThEKU52iEFvDhUnUGD19pGeI6r7+aU83UX/cxL6ugP1rHlS4wqlLfUyvrKm4OVdHBTFGmGGpfOGEUXTiZnlezsNN3IJDlVkUEFGJo5qP3v36ZYIiHI8bkNM1hJMznIJzaHBEJvZMvjUY7ayJtHWSbi66eAQBH6Re63bA/AIlUbIooglDcqML/d7DwvBCCZMSqxiVmDMwrnS0gEdBn+op4YNmorwf/fB346DHdfi0CP3cFHTgXwqMiWYAS9R9P/i0q6yyaeYnEU+0Xi/3IJeEBwv5IOvoVy6757eCgVOVsGqkO0gLQqmAgQKBgQDgmzneeX+TJCaXml8JVXch9CkPqgIu4W6813fPoC0FQWmrM4d0nHRD2FFamv9Scwlgg7mD/2QkPKvIgyhcKH1Yc8fh4HoC/QXS4OJNzsjDg1dPmB3ChGMydoquJpQMMz/DLxv20kKxpHV/XHDBn3eri8zduiv2OaIQP4oqkQRlXwKBgQCvZPDJ2OU+pyLOO4dOYF1fNkgtKVrK7Oswc+Wc82dgWOOR9hjGzVAYjHypJK+TK4GfurkkVv//6m8tiuPPfPHGTz6NIAeRrYUfR+bRnZAZPyABdtEFMWlBWUARczhtdQycgPOTVsEiZf/T9X1ZsetWtwqRH/8baSvUnf9a1nTdRQKBgBD7S33Jm96pHg3aSxMulgpIJo6boPygaLn4ZIObLaw3DOE3zScA6T1z7nq2O1WlgvD8JJuZRJ4ExEwttnB5UzjL0DIAwdjV/om6HQIa/0zg8kn/S0i4r7NSCRwvqIPfoBOGzUt1DDvYHlYZzCCniOm8cSnWeJpuMxWdaDndqwJrAoGADWfIo5TJSLWbimmwWMQyvsE2WQVNJC9hdm2VQh60av/APPqzQBaXJZkvOfpM1RHBMYUi+20O2e+AGdoAuW4SSJbr7/tfuLmrcA2dza2eQt+5QYNkTGJPaig5JlxJFsyYbynH9B3wWwGywO+FaiKYGyOYUilA5UZvjCubRcUYX4UCgYEA2TxAcZe2pCz6Qlq/19WDCFjWpOfQ+R1V+0pA3e9vuZPws9x4tmJzmQ+eF+VVlI1EmwBTwJb/2U5mHhxsqQ474FDIAoZwFh3dWgniUcBIKssU4wBT6Xxi7VZP9hq4uLBo/t/ppHUkfD2x8qylJKcjzNQUFBvTuzZJHVd4s3wUQLI=";

      byte[] publicBytes = Base64.decode(publicKeyStr);
      X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicBytes);
      KeyFactory keyFactoryPub = KeyFactory.getInstance("RSA");
      PublicKey pubKey = keyFactoryPub.generatePublic(publicKeySpec);

      byte[] privateBytes = Base64.decode(privateKeyStr);
      PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateBytes);
      KeyFactory keyFactoryPri = KeyFactory.getInstance("RSA");
      PrivateKey prvKey = keyFactoryPri.generatePrivate(privateKeySpec);

      String text = "hola mundo";
      String encrypted =  encrypt(text,pubKey);
      System.out.println("encrypt: " + encrypted);
      System.out.println("decrypt: " + decrypt(encrypted,prvKey));
    }


  private String encrypt(String content, Key pubKey) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
    byte[] contentBytes = content.getBytes();
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, pubKey);
    byte[] cipherContent = cipher.doFinal(contentBytes);
    String encoded = Base64.toBase64String(cipherContent);
    return encoded;
  }

  public String decrypt(String cipherContent, Key privKey) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, privKey);
    byte[] cipherContentBytes = Base64.decode(cipherContent.getBytes());
    byte[] decryptedContent = cipher.doFinal(cipherContentBytes);
    return new String(decryptedContent);
  }
}
