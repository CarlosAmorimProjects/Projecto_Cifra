package ed;


import java.util.Base64;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;



public class ED {

      private static KeyPair key = null;
                    
    public static void main (String[] args) throws Exception {
        
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        String iv = "1234567890123456";
        int opcao2;
      
               
        do
        {
           System.out.println("");
           System.out.println("------------------------------------"); 
           System.out.println("Programa de Encriptaçao/Decriptaçao");
           System.out.println("------------------------------------");
           System.out.println("");
           System.out.println("Introduza o número da opçao desejada ou qualquer outro algarismo superior a 5 para sair: ");
           System.out.println("");
           System.out.println("1 - Encriptar usando uma cifra simétrica");
           System.out.println("2 - Desencriptar usando uma cifra simétrica");
           System.out.println("3 - Gerar um par chave privada/chave pública para cifra assimétrica");
           System.out.println("4 - Encriptar usando uma cifra assimétrica (RSA)");
           System.out.println("5 - Desencriptar usando uma cifra assimétrica (RSA)");
           System.out.println("");
           System.out.println("------------------------------------");
        
        
        
        Scanner opcao = new Scanner(System.in);
        opcao2 = opcao.nextInt();
                       
        switch (opcao2)
        {
            case 1: 
                    System.out.println("Opçao 1");
                    System.out.println("");
                        
            System.out.println("Introduza a mensagem a encriptar: ");
            Scanner msg = new Scanner(System.in);
            String msg2 = msg.nextLine();
            
            System.out.println("");
            System.out.println("Introduza uma chave secreta (32 bits): ");
            Scanner chaveS = new Scanner (System.in);
            
            String chave = chaveS.nextLine();
            System.out.println("");
            System.out.println("A encriptar a mensagem...");
            String textoEmHexa = Converter.converteByteParaHex (
            Converter.converteASCIIparaByte(msg2, true));
            AES aes = new AES(chave,iv);
            
            String textoEncriptado = aes.EncriptarAES(textoEmHexa);
            System.out.println("");
            System.out.println("Texto encriptado com AES: ");
            System.out.println(textoEncriptado);
            break;
            
            case 2: 
                    System.out.println("Opçao 2");
                    System.out.println("");
            
            
            System.out.println("Introduza a mensagem a desencriptar: ");
            Scanner msgD = new Scanner(System.in);
            String msgD2 = msgD.next();
            
            System.out.println("");
            System.out.println("Introduza a chave: ");
            Scanner chave2 = new Scanner(System.in);
            String chaveD = chave2.next();         
        
            System.out.println("");
            System.out.println("A desencriptar a mensagem...");
            AES aes2 = new AES(chaveD,iv);
            String textoDesencriptado = aes2.DesencriptarAES(msgD2);
            
            System.out.println("");
            System.out.println("Texto Desencriptado: ");
            System.out.println(Converter.converteHexParaString(textoDesencriptado));
            break;
            
            
            case 3: 
                    System.out.println("Opçao 3");
            
            RSA rsaGerar = new RSA();
            rsaGerar.keyGenerator();
            System.out.println("");
            System.out.println("Par chave pública/chave privada criado e gravado na pasta do programa.");
            break;
                        
            case 4: 
                    System.out.println("Opçao 4");
                    System.out.println("");
            
            RSA rsa = new RSA();
            RSA rsaPubl = new RSA();
                        
            System.out.println("Introduza a mensagem a encriptar: ");
            Scanner msgAss = new Scanner (System.in);
            String msgRsa = msgAss.nextLine();
            System.out.println("");
            System.out.println("Introduza o caminho da chave publica: (exemplo: C:\\Pasta\\publicKey.txt)");
            Scanner chaveRSA = new Scanner(System.in);
            String chaveRSAKPubli = chaveRSA.next();
            byte[] publicBytes = Base64.getDecoder().decode(rsaPubl.abrirKeyFicheiro(chaveRSAKPubli));
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicBytes);
            KeyFactory keyFact = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFact.generatePublic(x509KeySpec);
        
            rsa.encriptarRSA(msgRsa, pubKey);
    
            break;
            
            case 5: 
                    System.out.println("Opçao 5"); 
                    System.out.println("");
                    
            RSA rsaD = new RSA();
            RSA rsaPriv = new RSA();
            
            System.out.println("Introduza a mensagem a desencriptar: ");
            Scanner msgASSD = new Scanner(System.in);
            String msgASSD2 = msgASSD.next();
            
            System.out.println("");
            System.out.println("Introduza o caminho da chave privada: (exemplo: C:\\Pasta\\privateKey.txt)");
            Scanner chaveRSAPriv = new Scanner(System.in);
            String chaveRSAKPriv = chaveRSAPriv.next();
            
            byte[] privateBytes = Base64.getDecoder().decode(rsaPriv.abrirKeyFicheiro(chaveRSAKPriv));
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);
            PrivateKey privKey = keyFactory.generatePrivate(keySpec);
            
            byte [] msgbyte = Converter.converteHexParaByte(msgASSD2);
            System.out.println("");
            System.out.println("Texto Desencriptado: ");
            System.out.println(rsaD.desencriptarRSA(msgbyte, privKey));
            break;                                                    
        }
        
    } while (opcao2 <= 5);      
                     
    }
   

}
