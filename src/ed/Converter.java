/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed;


public class Converter {
    
    // método para converter de ASCII para byte, com ou sem padding
    public static byte[] converteASCIIparaByte (String s, boolean padding)
    {
        int tamanho=0;
        
        if(padding)
        {
            tamanho = s.length() % 16;
            if(tamanho == 0)
            {
                tamanho = 16;
            }
            else
                tamanho = 0;
            
        }
        
        byte[] arrayResult = new byte[s.length()+tamanho];
        
        for (int i = 0; i < s.length(); i++)
        {
            arrayResult[i] = (byte) s.charAt(i);
        }
        
        byte pad = (byte) tamanho;
        for (int i = s.length(); i < s.length()+tamanho; i++)
        {
            arrayResult[i] = pad;
        }
                
        return arrayResult;
    }

    // método para converter de byte para hexadecimal
    public static String converteByteParaHex (byte[] a)
    {
        char hexDig[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 
                        'C', 'D', 'E', 'F'};
    
        StringBuilder buf =  new StringBuilder();
    
        for (int i = 0; i < a.length; i++)
        {
        buf.append(hexDig[(a[i] >> 4 ) & 0x0f]);
        buf.append(hexDig[a[i] & 0x0f]);
        }
    
       return buf.toString();   
    }

    // método para converter de hexadecimal para byte
    public static byte[] converteHexParaByte (String s)
    {
        int tamanho = s.length() / 2;
        byte[] arrayResult = new byte[tamanho];
        for (int i = 0; i < tamanho; i++)
        {
            String hex = s.substring(i*2, i*2+2);
            Integer valor = Integer.parseInt(hex, 16);
            arrayResult[i] = valor.byteValue();
        }
    
        return arrayResult;
    }

    // método para converter de hexadecimal para ASCII
    public static String converteHexParaString(String arg) 
    {        

        String str = "";
        for(int i=0;i<arg.length();i+=2)
        {
            String s = arg.substring(i, (i + 2));
            int decimal = Integer.parseInt(s, 16);
            str = str + (char) decimal;
        }       
        
        return str;
    }

}
