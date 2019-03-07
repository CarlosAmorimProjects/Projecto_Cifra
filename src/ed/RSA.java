/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import sun.misc.BASE64Encoder;


public class RSA {
    
     
    
    
    
    
    KeyPair key = null;
    
    // método para gerar o par chave publica / chave privada
     public void keyGenerator () throws IOException
             
    {
        KeyPairGenerator keygen = null;
        BASE64Encoder encoder = new BASE64Encoder();
        RSA rsaGravar = new RSA();
        
        try {
            keygen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) 
        {
            ex.printStackTrace();
        }
        keygen.initialize(1024);
        KeyPair kp = keygen.generateKeyPair();
        
        Key publicKey = kp.getPublic();
        Key privateKey = kp.getPrivate();
        String publicString = Base64.encode(publicKey.getEncoded());
        rsaGravar.gravarKeyFicheiro(publicString, "publicKey.txt");
        String privateString = Base64.encode(privateKey.getEncoded());
        rsaGravar.gravarKeyFicheiro(privateString, "privateKey.txt");
                           
    }
     
     
     // método para gravar as chaves 
     public void gravarKeyFicheiro (String key, String path)
     {
       try {
             
         
           File file = new File(path);

            if (!file.exists()) 
            {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(key);

            bw.close();
         }
            catch(Exception e)
         {
            e.printStackTrace();
         }
        
     }
     
     
     // método para ler as chaves de um ficheiro
     public String abrirKeyFicheiro (String path) throws IOException
     {
        BufferedReader br = new BufferedReader(new FileReader(path));
         try {
             StringBuilder sb = new StringBuilder();
             String line = br.readLine();

         while (line != null) {
            sb.append(line);
            line = br.readLine();
             }
            return sb.toString();
    } finally {
        br.close();
    }
}
     
     // método para encriptar em RSA
    public byte[] encriptarRSA (String mensagem, PublicKey pk)
    {
        Cipher cipher = null;
     
        
        try 
        {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pk);
            
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        } catch (NoSuchPaddingException e)
        
        {
            e.printStackTrace();   
        } catch (InvalidKeyException e)
        
        {
           e.printStackTrace();
        }
        
        byte [] mensagemCifrada = null;
        
        try
        {
           mensagemCifrada = cipher.doFinal(mensagem.getBytes());           
        } catch (IllegalBlockSizeException | BadPaddingException e)
            
        {
            e.printStackTrace();
        }
        
        String mensagemCifradaHex =  Converter.converteByteParaHex(mensagemCifrada);
        System.out.println("");
        System.out.println("Mensagem Encriptada: ");
        System.out.println(mensagemCifradaHex);
        
        return mensagemCifrada;
     
    }
    
    // método para desencriptar em RSA
    public String desencriptarRSA (byte [] mensagemCifrada, PrivateKey sk)
    {
        byte [] mensagemDecriptada = null;
        Cipher cipher = null;
        
        try 
        {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, sk);
        } 
        catch (NoSuchAlgorithmException ex) 
        {
            ex.printStackTrace();
        
        } 
        catch (NoSuchPaddingException ex) 
        {
            
            ex.printStackTrace();
        }
            
         catch (InvalidKeyException e) 
        {
          
            e.printStackTrace();
        }
        
        try 
        {
            mensagemDecriptada = cipher.doFinal(mensagemCifrada);
        } 
        catch (IllegalBlockSizeException ex) 
        {
            ex.printStackTrace();
        } 
        catch (BadPaddingException ex) 
        {
            ex.printStackTrace();
        }
        
        
        String mensagemDecriptadaHex = Converter.converteByteParaHex(mensagemDecriptada);
        String mensagemDecriptadaStr = Converter.converteHexParaString(mensagemDecriptadaHex);
        
       return mensagemDecriptadaStr;
        
    }
}
