//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.parser;

import com.gsw.integradores.nfe.client.ClientPool;
import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.commons.Properties;
import com.gsw.integradores.nfe.commons.Util;
import com.gsw.integradores.nfe.custom.Customizacoes;
import com.gsw.integradores.nfe.custom.CustomizacoesManager;
import com.gsw.integradores.nfe.parser.LerNumeroEvent;
import com.sap.conn.jco.JCoRecord;
import com.sap.conn.jco.JCoTable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.apache.velocity.tools.generic.EscapeTool;

public class VelocityParserUtil {
    private Customizacoes custom;

    public VelocityParserUtil(JCoRecord record) {
        this.custom = CustomizacoesManager.getCusotmizacoes(Util.getProperties(), record, ClientPool.getDestination(Properties.sap_destinationName.getValue()), LogUtil.getLogUtilCustomizacoes());
        LogUtil.info("=========== VERSAO DAS CUSTOMIZACOES:" + this.custom.getVersaoCustomizacoes());
    }

    public String removeAcentos(JCoRecord record, String campoSAP, String campoXML) {
        return this.removeAcentos(record, campoSAP, campoXML, (Object[])null);
    }

    public String removeAcentos(JCoRecord record, String campoSAP, String campoXML, Object... contadores) {
        String str = this.lerString(record, campoSAP, campoXML, contadores);
        return Util.removeCaracteresEspeciais(str);
    }

    public String removeZeroEsquerda(JCoRecord record, String campoSAP, String campoXML, Object... contadores) {
        String str = this.lerString(record, campoSAP, campoXML, contadores);
        return Util.removeZeroEsquerda(str.trim());
    }

    public String obterNumeroAleatorioTamanho8(JCoRecord record, String campoSAP, String campoXML) {
        String randonNumber = this.lerString(record, campoSAP, campoXML);
        return randonNumber.length() == 9?randonNumber.substring(1):randonNumber;
    }

    public String lerStringSemCTRL(JCoRecord record, String campoSAP, String campoXML) {
        return this.lerStringSemCTRL(record, campoSAP, campoXML, (Object[])null);
    }

    public String lerStringSemCTRL(JCoRecord record, String campoSAP, String campoXML, Object... contadores) {
        String texto = this.lerString(record, campoSAP, campoXML, contadores);
        return texto == null?null:(new EscapeTool()).xml(texto).replaceAll("\\p{Cntrl}", "");
    }

    public JCoRecord obterVolume(JCoTable vol, JCoRecord header) {
        return (JCoRecord)(vol == null?null:(vol.getNumRows() != 0?vol:header));
    }

    public String lerEspecie(JCoRecord record, String campoSAP, String campoXML) {
        return this.lerString(record, campoSAP, campoXML).replace("/", "").trim();
    }

    public String lerString(JCoRecord record, String campoSAP, String campoXML) {
        return this.lerString(record, campoSAP, campoXML, (Object[])null);
    }

    public String lerString(String campoXML, Object... contadores) {
        return this.lerString((JCoRecord)null, (String)null, campoXML, contadores);
    }

    public String lerString(JCoRecord record, String campoSAP, String campoXML, Object... contadores) {
        String valorCustomizado = this.obterValorCustomizado(campoXML, contadores);
        if("__null".equals(valorCustomizado)) {
            return "";
        } else if(valorCustomizado != null) {
            return valorCustomizado;
        } else if(campoSAP != null && record != null) {
            try {
                String e = record.getString(campoSAP);
                return e != null && !"".equals(e)?e:this.obterValorPadrao(campoXML);
            } catch (Exception var7) {
                return "";
            }
        } else {
            return "";
        }
    }

    public Integer lerInt(JCoRecord record, String campoSAP, String campoXML) {
        return this.lerInt(record, campoSAP, campoXML, (Object[])null);
    }

    public Integer lerInt(JCoRecord record, String campoSAP, String campoXML, Object... contadores) {
        LerNumeroEvent event = new LerNumeroEvent() {
            public String lerNumero(String valor, int casasDecimais) {
                return Integer.valueOf(valor).toString();
            }
        };
        return Integer.valueOf(this.lerNumero(record, campoSAP, campoXML, 0, event, contadores));
    }

    public String lerDecimal(JCoRecord record, String campoSAP, String campoXML, int casasDecimais) {
        return this.lerDecimal(record, campoSAP, campoXML, casasDecimais, (Object[])null);
    }

    public String lerDecimal(JCoRecord record, String campoSAP, String campoXML, int casasDecimais, Object... contadores) {
        LerNumeroEvent event = new LerNumeroEvent() {
            public String lerNumero(String valor, int casasDecimais) {
                DecimalFormat nf = (DecimalFormat)NumberFormat.getInstance(Locale.US);
                nf.setMinimumFractionDigits(casasDecimais);
                nf.setMaximumFractionDigits(casasDecimais);
                nf.setGroupingUsed(false);
                return nf.format(Double.valueOf(valor));
            }
        };
        return this.lerNumero(record, campoSAP, campoXML, casasDecimais, event, contadores);
    }

    public boolean isCFOPImportacao(JCoRecord record, String campoSAP, String campoXML, Object... contadores) {
        String cfop = this.lerString(record, campoSAP, campoXML, contadores);
        return !"".equals(cfop) && cfop.charAt(0) == 51?!cfop.equals("3201") && !cfop.equals("3202") && !cfop.equals("3211") && !cfop.equals("3503") && !cfop.equals("3553"):false;
    }

    public int getNumeroLinhas(JCoTable table, String campoXML) {
        return this.getNumeroLinhas(table, campoXML, (Object[])null);
    }

    public int getNumeroLinhas(JCoTable table, String campoXML, Object... contadores) {
        String valorCustomizado = this.obterValorCustomizado(campoXML, contadores);

        try {
            return valorCustomizado != null && !"__null".equals(valorCustomizado)?Integer.valueOf(valorCustomizado).intValue():table.getNumRows();
        } catch (Exception var6) {
            return table.getNumRows();
        }
    }

    public boolean isVazio(JCoRecord record, String campoSAP, String campoXML) {
        return this.isVazio(record, campoSAP, campoXML, (Object[])null);
    }

    public boolean isVazio(JCoRecord record, String campoSAP, String campoXML, Object... contadores) {
        String valor = this.lerString(record, campoSAP, campoXML, contadores);
        return valor == null || "".equals(valor);
    }

    public boolean isVazioOuZero(JCoRecord record, String campoSAP, String campoXML) {
        return this.isVazioOuZero(record, campoSAP, campoXML, (Object[])null);
    }

    public boolean isVazioOuZero(JCoRecord record, String campoSAP, String campoXML, Object... contadores) {
        try {
            return Double.parseDouble(this.lerString(record, campoSAP, campoXML, contadores)) == 0.0D;
        } catch (Exception var6) {
            return true;
        }
    }

    public static boolean isCampoValido(JCoRecord record, String key) {
        try {
            record.getString(key);
            return true;
        } catch (Exception var3) {
            return false;
        }
    }

    public static boolean isCampoValidoETemValor(JCoRecord record, String key) {
        try {
            String e = record.getString(key);
            return e != null && !"".equals(e);
        } catch (Exception var3) {
            return false;
        }
    }

    public boolean isDataVazia(JCoRecord record, String campoSAP, String campoXML) {
        return this.isDataVazia(record, campoSAP, campoXML, (Object[])null);
    }

    public boolean isDataVazia(JCoRecord record, String campoSAP, String campoXML, Object... contadores) {
        String data = this.lerString(record, campoSAP, campoXML, contadores);
        return data == null || "".equals(data) || data.contains("0000-00-00");
    }

    public String obterDataAtual() {
        Date dt = Calendar.getInstance().getTime();
        return (new SimpleDateFormat("yyyy-MM-dd")).format(dt) + "T00:00:00-02:00";
    }
    
    public String obterDataHoraAtual() {
    	Date data = Calendar.getInstance().getTime();
    	return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")).format(data);
    }

    public String obterDataHoraSAP(JCoRecord record, String campoDataSAP, String campoXML) {
        return this.obterDataHoraSAP(record, campoDataSAP, (String)null, campoXML);
    }

    public String obterDataHoraSAP(JCoRecord record, String campoDataSAP, String campoHoraSAP, String campoXML) {
        return this.obterDataHoraSAP(record, campoDataSAP, campoHoraSAP, campoXML, (Object[])null);
    }

    public String obterDataHoraSAP(JCoRecord record, String campoDataSAP, String campoHoraSAP, String campoXML, Object... contadores) {
        String data = this.lerString(record, campoDataSAP, campoXML, contadores);
        String hora = this.lerString(record, campoHoraSAP, campoXML, contadores);
        if(data != null && !"__null".equals(data) && data.length() == 10) {
            if(hora == null || "__null".equals(hora) || hora.length() != 8) {
                hora = "00:00:00";
            }

            return data + "T" + hora + "-02:00";
        } else {
            return null;
        }
    }

    private String lerNumero(JCoRecord record, String campoSAP, String campoXML, int casasDecimais, LerNumeroEvent event, Object... contadores) {
        String valor = this.lerString(record, campoSAP, campoXML, contadores);
        if(valor != null && !"".equals(valor) && !"__null".equals(valor)) {
            try {
                return event.lerNumero(valor, casasDecimais);
            } catch (Exception var9) {
                return "";
            }
        } else {
            return valor;
        }
    }

    private String obterValorCustomizado(String campoXML, Object... contadores) {
        try {
            return this.custom.getValorCustomizado(this.obterCampoXML(campoXML, contadores));
        } catch (Exception var4) {
        	var4.printStackTrace();
            LogUtil.error("Erro ao obter o valor customizado de \'" + campoXML + "\' (" + var4.getMessage() + ")");
            return "";
        }
    }

    private String obterValorPadrao(String campoXML) {
        try {
            return this.custom.getValorPadrao(campoXML).toString();
        } catch (Exception var3) {
            return "";
        }
    }

    private String obterCampoXML(String campoXML, Object... contadores) {
        return contadores != null && contadores.length != 0 && campoXML != null?String.format(campoXML, contadores):campoXML;
    }
}
