//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.commons;

import com.gsw.integradores.nfe.commons.Util;
import com.gsw.integradores.nfe.custom.LogUtilInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LogUtil {
    private static Appender appender;
    private static Logger logger = Logger.getLogger("INTEGRAÇÃO NFE");
    private static String pattern = "[%-2d{dd/MM/yy HH:mm:ss}].[%-5p]: %m%n";
    private static String tagMark = "================== |? ==================";
    private static String errorMark = "===*****=== |?";
    private static String dataAtual;
    private static boolean acentuacaoHabilitada = true;

    static {
        logger.setLevel(Level.ALL);
        refreshAppender();
    }

    public LogUtil() {
    }

    private static void refreshAppender() {
        if(logger != null) {
            dataAtual = obterDataAtual();
            logger.removeAllAppenders();

            try {
                appender = new FileAppender(new PatternLayout(pattern), getCaminhoLog());
                logger.addAppender(appender);
            } catch (IOException var1) {
                var1.printStackTrace();
            }

        }
    }

    private static String obterDataAtual() {
        return (new SimpleDateFormat("yyyy-MM-dd")).format(Calendar.getInstance().getTime());
    }

    public static void closeAppender() {
        appender.close();
    }

    public static String getDataLog() {
        DateFormat df = DateFormat.getDateTimeInstance(2, 2);
        return df.format(new Date(System.currentTimeMillis()));
    }

    public static void info(StringBuffer log) {
        info(log.toString());
    }

    public static void info(String msg) {
        info(msg, (LogUtil.Marcacao)null);
    }

    public static void info(String className, String msg) {
        info(className + " - " + msg);
    }

    public static void info(String msg, LogUtil.Marcacao mark) {
        log(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LogUtil.logger.info(e.getActionCommand());
                System.out.println(e.getActionCommand());
            }
        }, msg, mark);
    }

    public static void error(String msg, Throwable e) {
        error(msg);
        error(e);
    }

    public static void error(String msg) {
        error(msg, (LogUtil.Marcacao)null);
    }

    public static void error(String className, String msg) {
        error(className + " - " + msg);
    }

    public static void error(String msg, LogUtil.Marcacao mark) {
        log(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LogUtil.logger.error(e.getActionCommand());
                System.err.println(e.getActionCommand());
            }
        }, msg, mark);
    }

    public static void error(Throwable e) {
        String msg = "\r\n" + e.toString() + "\r\n";
        StackTraceElement[] var5;
        int var4 = (var5 = e.getStackTrace()).length;

        for(int var3 = 0; var3 < var4; ++var3) {
            StackTraceElement stack = var5[var3];
            msg = msg + "\tat " + stack.toString() + "\r\n";
        }

        error(msg);
    }

    private static void log(ActionListener acao, String msg, LogUtil.Marcacao mark) {
        if(!obterDataAtual().equals(dataAtual)) {
            refreshAppender();
        }

        if(!acentuacaoHabilitada) {
            msg = Util.removeAcentos(msg);
        }

        if(mark == null) {
            acao.actionPerformed(new ActionEvent(acao, 0, msg));
        }

        if(mark == LogUtil.Marcacao.TAG_MARK) {
            acao.actionPerformed(new ActionEvent(acao, 0, getTagMark(msg)));
        }

        if(mark == LogUtil.Marcacao.ERROR_MARK) {
            acao.actionPerformed(new ActionEvent(acao, 0, getErrorMark(msg)));
        }

    }

    public static String getTagMark(String msg) {
        return tagMark.replace("|?", msg);
    }

    public static String getErrorMark(String msg) {
        return errorMark.replace("|?", msg);
    }

    public static String getCaminhoLog() {
        return "logs/integracaoNFe." + obterDataAtual() + ".log";
    }

    public static String getPattern() {
        return pattern;
    }

    public static String getTagMark() {
        return tagMark;
    }

    public static String getErrorMark() {
        return errorMark;
    }

    public static boolean isAcentuacaoHabilitada() {
        return acentuacaoHabilitada;
    }

    public static LogUtilInterface getLogUtilCustomizacoes() {
        return new LogUtilInterface() {
            public void info(String msg) {
                LogUtil.info(msg);
            }

            public void info(StringBuffer log) {
                LogUtil.info(log);
            }

            public String getTagMark(String msg) {
                return LogUtil.getTagMark(msg);
            }

            public String getErrorMark(String msg) {
                return LogUtil.getErrorMark(msg);
            }

            public void error(String msg, Throwable e) {
                LogUtil.error(msg, e);
            }

            public void error(Throwable e) {
                LogUtil.error(e);
            }

            public void error(String msg) {
                LogUtil.error(msg);
            }
        };
    }

    public static enum Marcacao {
        TAG_MARK,
        ERROR_MARK;

        private Marcacao() {
        }
    }
}
