//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.depara;

public enum XmlInEnum {
    DOC_NUM("DOCNUM", "docNum"),
    CNPJ_PRESTADOR("C_CNPJ", "cnpjPrestador"),
    INSCRICAO_PRESTADOR("C1_IM", "inscricaoPrestador"),
    CIDADE_TOMADOR("E1_CMUN", "cidadeTomador"),
    UF_TOMADOR("E1_UF", "ufTomador"),
    BAIRRO_PRESTADOR("C1_XBAIRRO", "bairroPrestador"),
    NUMERO_ENDERECO_PRESTADOR("C1_NRO", "numeroEnderecoPrestador"),
    NUMERO_ENDERECO_TOMADOR("E1_NRO", "numeroEnderecoTomador"),
    CEP_PRESTADOR("C1_CEP", "cepPrestador"),
    UF_PRESTADOR("C1_UF", "ufPrestador"),
    ENDERECO_PRESTADOR("C1_XLGR", "enderecoPrestador"),
    ENDERECO_TOMADOR("E1_XLGR", "enderecoTomador"),
    COD_CIDADE("C1_CMUN", "codCidade"),
    CNPJ_TOMADOR("E_CNPJ", "cpfCnpjTomador"),
    CPF_TOMADOR("E_CPF", "cpfCnpjTomador"),
    NUMERO_RPS("NNF", "numeroRps"),
    SERIE_RPS("SERIE", "serieRps"),
    RAZAO_SOCIAL_PRESTADOR("C_XNOME", "razaoSocialPrestador"),
    RAZAO_SOCIAL_TOMADOR("E_XNOME", "razaoSocialTomador"),
    VALOR_SERVICO("S2_VSERV", "valorServicos"),
    BASE_CALCULO("S2_VBC", "baseCalculo"),
    COMPLEMENTO_ENDERECO_PRESTADOR("C1_XCPL", "complementoEnderecoPrestador"),
    PAIS_PRESTADOR("C1_CPAIS", "paisPrestador"),
    INSCRICAO_ESTADUAL_TOMADOR("E1_IE", "inscricaoEstadualTomador"),
    COMPLEMENTO_ENDERECO_TOMADOR("E1_XCPL", "complementoEnderecoTomador"),
    BAIRRO_TOMADOR("E1_XBAIRRO", "bairroTomador"),
    PAIS_TOMADOR("E1_CPAIS", "paisTomador"),
    CEP_TOMADOR("E1_CEP", "cepTomador"),
    CODIGO_CNAE("C1_CNAE", "codigoCnae"),
    EMAIL_TOMADOR("EMAIL", "emailTomador"),
    INFORMACOES_COMPLEMENTARES("INFCOMP", "descricaoRps");

    private String sapKey;
    private String fieldMsaf;

    private XmlInEnum(String sapKey, String fieldMsaf) {
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
