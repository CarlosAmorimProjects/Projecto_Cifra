/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AES {
   
    private final String ALGORITMO = "AES/CTR/NoPadding";
    private final Key chaveAES;
    private final IvParameterSpec ivps;
    
    // método para gerar a Chave Secreta
    public AES (String chave, String iv)
    {
        // converte de ASCII para byte
        byte[] ivArray = Converter.converteASCIIparaByte (iv, false);
        ivps = new IvParameterSpec (ivArray);
        // converter de hexadecimal para byte
        byte[] chaveArray = Converter.converteHexParaByte(chave);
        chaveAES = new SecretKeySpec(chaveArray, "AES");   
        
    }

    // método para encriptar mensagem
    public String EncriptarAES (String msg) throws Exception
    {
        Cipher c = Cipher.getInstance(ALGORITMO);
        c.init(Cipher.ENCRYPT_MODE, chaveAES, ivps);
        
        byte[] textoArray = Converter.converteHexParaByte(msg);
        byte[] cipherText = c.doFinal(textoArray);
        return Converter.converteByteParaHex(cipherText);
                
        
    }
    
    // método para desencriptar mensagem
    public String DesencriptarAES (String msg2) throws Exception
            
    {
        Cipher c = Cipher.getInstance(ALGORITMO);
        c.init(Cipher.DECRYPT_MODE, chaveAES, ivps);
        
        byte[] textoArray = Converter.converteHexParaByte(msg2);
        byte[] mensagem = c.doFinal(textoArray);
        return Converter.converteByteParaHex(mensagem);
       
        
    }  
    
}
   
