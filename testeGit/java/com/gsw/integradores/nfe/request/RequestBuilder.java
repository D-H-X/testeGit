//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.request;

import com.gsw.integradores.nfe.commons.IntegradorUtil;
import com.gsw.integradores.nfe.commons.PackageSend;
import com.gsw.integradores.nfe.vo.CancelamentoVO;
import com.gsw.integradores.nfe.vo.DeducaoVO;
import com.gsw.integradores.nfe.vo.ItemVO;
import com.gsw.integradores.nfe.vo.NfsVO;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RequestBuilder {
    private static final Logger logger = LoggerFactory.getLogger(RequestBuilder.class);

    public RequestBuilder() {
    }

    public String build(NfsVO nfsVO) throws Exception {
        StringBuilder txt = new StringBuilder();
        StringBuilder itemDeducoes = new StringBuilder();
        Field[] fields = NfsVO.class.getDeclaredFields();
        txt.append("__rps__|");
        Field[] nomeArquivo = fields;
        int json = fields.length;

        for(int mapper = 0; mapper < json; ++mapper) {
            Field packageSend = nomeArquivo[mapper];
            packageSend.setAccessible(true);
            if(packageSend.getType().isAssignableFrom(List.class)) {
                itemDeducoes.append(this.createItemDeducao(packageSend, nfsVO));
            } else {
                this.generateFildAndValue(nfsVO, txt, packageSend);
            }
        }

        txt.append(itemDeducoes);
        txt.append("__arquivo_fim__|");
        PackageSend var9 = new PackageSend();
        var9.setAction("EnviarPacote");
        var9.setContents(txt.toString());
        ObjectMapper var10 = new ObjectMapper();
        String var11 = var10.writeValueAsString(var9);
        String var12 = nfsVO.getDocNum();
        logger.debug("envio/" + var12);
        logger.info("DOCNUM " + var12 + " envio Portal: " + var11);
        IntegradorUtil.salvarJsonNFS("envio", var12, var11);
        return var11;
    }

    public String buildCancellation(CancelamentoVO cancelamentoVO) throws Exception {
        StringBuilder txt = new StringBuilder();
        txt.append("__cancelamento__|");
        Field[] fields = CancelamentoVO.class.getDeclaredFields();
        Field[] nomeArquivo = fields;
        int json = fields.length;

        for(int mapper = 0; mapper < json; ++mapper) {
            Field packageSend = nomeArquivo[mapper];
            packageSend.setAccessible(true);
            this.generateFildAndValue(cancelamentoVO, txt, packageSend);
        }

        txt.append("__arquivo_fim__|");
        PackageSend var8 = new PackageSend();
        var8.setAction("Cancelar");
        var8.setContents(txt.toString());
        ObjectMapper var9 = new ObjectMapper();
        String var10 = var9.writeValueAsString(var8);
        String var11 = cancelamentoVO.getDocNum();
        logger.debug("cancelamento/" + var11);
        logger.debug(var10);
        IntegradorUtil.salvarJsonNFS("cancelamento", var11, var10);
        return var10;
    }

    private void generateFildAndValue(Object vo, StringBuilder txt, Field field) throws IllegalAccessException {
        Object value = field.get(vo);
        if(value != null && field.getName() != "transactionId") {
            txt.append(field.getName()).append("=").append(value).append("|");
        }

    }

    private String createItemDeducao(Field field, Object nfsVo) throws Exception {
        StringBuilder builder = new StringBuilder();
        List deducoes;
        if(field.getName().equals("itensVO")) {
            deducoes = (List)field.get(nfsVo);
            if(deducoes != null) {
                this.genarateKeyValueItemDeducao(builder, deducoes, ItemVO.class, "__item__|");
            }
        } else if(field.getName().equals("deducoesVO")) {
            deducoes = (List)field.get(nfsVo);
            if(deducoes != null) {
                this.genarateKeyValueItemDeducao(builder, deducoes, DeducaoVO.class, "__deducao__|");
            }
        }

        return builder.toString();
    }

    private void genarateKeyValueItemDeducao(StringBuilder builder, List<?> list, Class<?> clazz, String chaveInicial) throws IllegalAccessException {
        Iterator var6 = list.iterator();

        while(var6.hasNext()) {
            Object deducao = var6.next();
            builder.append(chaveInicial);
            Field[] fields = clazz.getDeclaredFields();
            Field[] var11 = fields;
            int var10 = fields.length;

            for(int var9 = 0; var9 < var10; ++var9) {
                Field item = var11[var9];
                item.setAccessible(true);
                this.generateFildAndValue(deducao, builder, item);
            }
        }

    }
}
