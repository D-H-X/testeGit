//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.commons;

import com.gsw.integradores.nfe.commons.LogUtil;
import com.sap.conn.jco.JCoRecord;
import com.sap.conn.jco.JCoTable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.text.Normalizer.Form;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;

public class Util {
    public Util() {
    }

    public static String removeAcentos(String str) {
        str = Normalizer.normalize(str, Form.NFD);
        return str.replaceAll("[^\\p{ASCII}]", "");
    }

    public static Properties getProperties() {
        Properties config = new Properties();

        try {
            FileInputStream e = new FileInputStream((new File("config/config.properties")).getAbsoluteFile());
            config.load(e);
            e.close();
            return config;
        } catch (Exception var2) {
            return null;
        }
    }

    public static String getTimeToXmlIn(String date) {
        if(StringUtils.isNotBlank(date)) {
            String unformattedDate = date.replaceAll("[^0-9]+", "");
            return unformattedDate.substring(8, 14);
        } else {
            return "";
        }
    }

    public static String getDateToXmlIn(String date) {
        if(StringUtils.isNotBlank(date)) {
            String unformattedDate = date.replaceAll("[^0-9]+", "");
            return unformattedDate.substring(0, 8);
        } else {
            return "";
        }
    }

    public static String formatDateFromStringSap(String dateTime) {
        StringBuilder builder = new StringBuilder(dateTime);
        builder.insert(4, "-");
        builder.insert(7, "-");
        return builder.toString();
    }

    public static String formatDateTimeFromStringSap(String dateTime) {
        StringBuilder builder = new StringBuilder(dateTime);
        builder.insert(4, "-");
        builder.insert(7, "-");
        builder.insert(10, "T");
        builder.insert(13, ":");
        builder.insert(16, ":");
        return builder.toString();
    }

    public static String preencheAEsquerdaCom(String linha_a_preencher, String letra, int tamanho) {
        return preencheCom(linha_a_preencher, letra, tamanho, 1);
    }

    public static String preencheADireitaCom(String linha_a_preencher, String letra, int tamanho) {
        return preencheCom(linha_a_preencher, letra, tamanho, 2);
    }

    private static String preencheCom(String linha_a_preencher, String letra, int tamanho, int direcao) {
        if(linha_a_preencher != null && !linha_a_preencher.trim().equals("")) {
            StringBuffer sb = new StringBuffer(linha_a_preencher);
            int i;
            if(direcao == 1) {
                for(i = sb.length(); i < tamanho; ++i) {
                    sb.insert(0, letra);
                }
            } else if(direcao == 2) {
                for(i = sb.length(); i < tamanho; ++i) {
                    sb.append(letra);
                }
            }

            return sb.toString();
        } else {
            return null;
        }
    }

    public static String formatAliquota(String aliquota) {
        if(!NumberUtils.isNumber(aliquota)) {
            return "";
        } else {
            BigDecimal decimal = new BigDecimal(aliquota);
            BigDecimal dividido = decimal.divide(new BigDecimal(100L));
            return dividido.toString();
        }
    }

    public static String getValueFromExt2(JCoRecord record, String param) {
        String result = "";
        LogUtil.info("getValueFromExt2: " + (record != null?record.toXML():"record é nulo!"));
        LogUtil.info("getValueFromExt2 param: " + param);
        JCoTable ext2 = (JCoTable)record;
        if(ext2 != null && ext2.getNumRows() > 0) {
            ext2.firstRow();

            do {
                LogUtil.info("getValueFromExt2 FIELD: " + ext2.getString("FIELD") + " VALUE: " + ext2.getString("VALUE"));
                if(param.equals(ext2.getString("FIELD"))) {
                    result = result + ext2.getString("VALUE");
                    LogUtil.info("getValueFromExt2 result: " + result);
                }
            } while(ext2.nextRow());
        }

        LogUtil.info("getValueFromExt2 retornado: " + result);
        return result;
    }

    public static String getValueFromExt2(JCoRecord record, String param, String defaultValue) {
        String result = getValueFromExt2(record, param);
        return result != null && !"".equals(result)?result:defaultValue;
    }

    public static String removeZeroEsquerda(String str) {
        return str.charAt(0) != 48?str:removeZeroEsquerda(str.substring(1));
    }

    public static String removeCaracteresEspeciais(String str) {
        if(str == null) {
            return null;
        } else {
            str = Normalizer.normalize(str, Form.NFD);
            return str.replaceAll("[^\\p{ASCII}]", "").replaceAll("\\p{Cntrl}", "").replaceAll("&", "").replaceAll("<", "").replaceAll(">", "").replaceAll("\'", "").replaceAll("\"", "").replaceAll("º", "").replaceAll("ª", "");
        }
    }

    public static void salvarRecord(JCoRecord record) {
        if(record != null) {
            try {
                SimpleDateFormat e = new SimpleDateFormat("yyyy.MM.dd_HHmm");
                String arquivo = e.format(new Date()) + "_JCoRecord.msaf";
                String dir = "record//";
                LogUtil.info("Salvando record SAP - " + arquivo);
                File file = new File(dir);
                if(!file.exists()) {
                    file.mkdir();
                }

                FileOutputStream fos = new FileOutputStream(dir + arquivo);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(record);
                oos.close();
            } catch (Exception var7) {
                LogUtil.error("Erro ao salvar record", var7);
            }
        }

    }
}
