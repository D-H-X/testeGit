//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.commons;

import com.gsw.integradores.nfe.commons.LogUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IntegradorUtil {
    public static final String ENVIO = "envio";
    public static final String CANCELAMENTO = "cancelamento";
    public static final String INUTILIZACAO = "inutilizacao";
    public static final String CCE = "cce";
    public static final String CONSULTADO = "consultado";
    public static final String IMPRESSAO = "impressao";

    public IntegradorUtil() {
    }

    public static void salvarJsonNFE(String acao, String arquivo, String json) {
        try {
            String e = "json//nfe//" + acao;
            File file = new File(e);
            if(!file.exists()) {
                file.mkdirs();
            }

            salvarJson(e, arquivo, json);
        } catch (Exception var5) {
            LogUtil.error("Erro ao criar diretorio NF-e do JSON", var5);
        }

    }

    public static void salvarJsonINV(String arquivo, String json) {
        try {
            String e = "json//inv//notas";
            File file = new File(e);
            if(!file.exists()) {
                file.mkdirs();
            }

            salvarJsonInv(e, arquivo, json);
        } catch (Exception var5) {
            LogUtil.error("Erro ao criar diretorio NF-e do JSON", var5);
        }

    }
    
    
    public static void main(String[] args) {
		
    	String a =  lerArq("");
    	
    	
    	System.out.println(a);
	}
    
    
    public static String lerArq(String chave)  {
    	try {
    		 FileInputStream stream = new FileInputStream("C:\\Users\\supor\\OneDrive\\Área de Trabalho\\PROJETO JAVA ESTUDOS\\integrador-hercules\\integrador-nfe-sap-pepsico\\json\\inv\\notas\\" + chave + ".txt");
//    		 FileInputStream stream = new FileInputStream("json//inv//notas" +  chave + ".txt") ;
    		 InputStreamReader reader = new InputStreamReader(stream);
 	        BufferedReader br = new BufferedReader(reader);
 	        String linha = br.readLine();
 	        int cont = 0;
 	        while(linha != null) {
 	            cont = cont + 1;
 	            if (cont < 2) {
 	        	System.out.println(linha);
// 	            linha = br.readLine();
 	           return linha;
 	        }    	
 	        }
    	
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
		return null;
    	
    	
    	}
    
    
    
    public static void salvarJsonNFS(String acao, String arquivo, String json) {
        try {
            String e = "json//nfs//" + acao;
            File file = new File(e);
            if(!file.exists()) {
                file.mkdirs();
            }

            salvarJson(e, arquivo, json);
        } catch (Exception var5) {
            LogUtil.error("Erro ao criar diretorio NFS-e do JSON", var5);
        }

    }

    private static void salvarJson(String dir, String arquivo, String json) {
        if(arquivo != null && json != null) {
            try {
                SimpleDateFormat e = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
                Date data = new Date();
                FileWriter out = new FileWriter(dir + "//" + arquivo + ".txt", true);
                BufferedWriter bw = new BufferedWriter(out);
                bw.append(e.format(data));
                bw.newLine();
                bw.append(json);
                bw.newLine();
                bw.flush();
                bw.close();
                out.close();
            } catch (Exception var7) {
                LogUtil.error("Erro ao salvar log do JSON", var7);
            }
        }

    }
    
    private static void salvarJsonInv(String dir, String arquivo, String json) {
        if(arquivo != null && json != null) {
            try {
                FileWriter out = new FileWriter(dir + "//" + arquivo + ".txt", true);
                BufferedWriter bw = new BufferedWriter(out);
                bw.append(json);
                bw.newLine();
                bw.flush();
                bw.close();
                out.close();
            } catch (Exception var7) {
                LogUtil.error("Erro ao salvar log do JSON", var7);
            }
        }

    }
    
    
}
