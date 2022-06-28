//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.depara;

public enum ZXmlOutEnum {
    TIPO_RPS("TIPO_RPS", "tipoRps"),
    DATA_EMISSAO_RPS("DT_EMI_RPS", "dataEmissaoRps"),
    ITEM_LISTA_SERVICO("LIST_SERV", "itemListaServico"),
    OPTANTE_SIMPLES_NACIONAL("OPT_SIMPLES", "optanteSimplesNacional"),
    CODIGO_TRIBUTACAO_MUNICIPIO("TRIBUT_MUNIC", "codigoTributacaoMunicipio"),
    NATUREZA_OPERACAO("NAT_OPE", "naturezadaOperacao"),
    TIPO_RECOLHIMENTO("TP_RECO", "tipoRecolhimento"),
    SITUACAO_RPS("SIT_RPS", "situacaoRps"),
    NUMERO_NFE("NUMERO_NFE", "numeroNfe"),
    COD_VERIFICACAO("COD_VERIF", "codigoVerificacao"),
    COD_CANCELAMENTO("COD_CANCEL", "codigoCancelamento"),
    INCENTIVADOR_CULTURAL("INCENT_CULT", "incentivadorCultural"),
    INDICACAO_CPF_CNPJ("INDIC_CPF_CNPJ", "indicacaoCpfCnpj"),
    VALOR_INSS("VLR_INSS", "valorInss"),
    VALOR_IR("VLR_IR", "valorIr"),
    VALOR_CSLL("VLR_CSL", "valorCsll"),
    VALOR_ISS_RETIRDO("VLR_ISS_RET", "valorIssRetido"),
    VALOR_LIQUIDO_NFSE("VLR_LIQ_NFSE", "valorLiquidoNfse"),
    VALOR_PISS("VLR_PIS", "valorPis"),
    VALOR_COFINS("VLR_COFINS", "valorCofins"),
    VALOR_ISS("VLR_ISS", "valorIss"),
    MUNICIPIO_PRESTACAO("CITYC", "municipioPrestacao"),
    VALOR_DEDUZIR("VLR_DEDUZIR", "valorDeduzir"),
    DATA_COMPETENCIA("DATA_COMPETENCIA", "dataCompetencia"),
    VALOR_FATURA("VLR_LIQ_NFSE", "valorFatura"),
    E1_IM_TOMADOR("E1_IM_TOMADOR", "inscricaoMunicipalTomador");

    private String sapKey;
    private String fieldMsaf;

    private ZXmlOutEnum(String sapKey, String fieldMsaf) {
        this.sapKey = sapKey;
        this.fieldMsaf = fieldMsaf;
    }

    public String getSapKey() {
        return this.sapKey;
    }

    public void setSapKey(String sapKey) {
        this.sapKey = sapKey;
    }

    public String getFieldMsaf() {
        return this.fieldMsaf;
    }

    public void setFieldMsaf(String fieldMsaf) {
        this.fieldMsaf = fieldMsaf;
    }
}
