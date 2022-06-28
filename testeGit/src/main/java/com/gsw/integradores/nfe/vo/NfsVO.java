//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.vo;

import com.gsw.integradores.nfe.vo.DeducaoVO;
import com.gsw.integradores.nfe.vo.ItemVO;
import java.io.Serializable;
import java.util.List;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class NfsVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Long transactionId;
    private String docNum;
    private String versao;
    private String tipoAmbienteSistema;
    private String codCidade;
    private String cnpjPrestador;
    private String inscricaoPrestador;
    private String numeroRps;
    private String serieRps;
    private String dataEmissaoRps;
    private String dataCompetencia;
    private String tipoRps;
    private String situacaoRps;
    private String naturezadaOperacao;
    private String municipioPrestacao;
    private String municipioIncidencia;
    private String paisServico;
    private String descricaoRps;
    private String codigoServicoInterno;
    private String codigoCnae;
    private String itemListaServico;
    private String codigoTributacaoMunicipio;
    private String aliquotaServicos;
    private String baseCalculo;
    private String valorIss;
    private String tipoRecolhimento;
    private String valorIssRetido;
    private String responsavelRetencao;
    private String valorLiquidoNfse;
    private String valorServicos;
    private String valorPis;
    private String valorCofins;
    private String valorInss;
    private String valorIr;
    private String valorCsll;
    private String outrasRetencoes;
    private String valorDescontoIncondicionado;
    private String valorDescontoCondicionado;
    private String razaoSocialPrestador;
    private String enderecoPrestador;
    private String numeroEnderecoPrestador;
    private String complementoEnderecoPrestador;
    private String bairroPrestador;
    private String cepPrestador;
    private String ufPrestador;
    private String paisPrestador;
    private String dddPrestador;
    private String telefonePrestador;
    private String emailPrestador;
    private String regimeEspecialTributacao;
    private String optanteSimplesNacional;
    private String incentivadorCultural;
    private String cpfCnpjTomador;
    private String inscricaoMunicipalTomador;
    private String indicacaoCpfCnpj;
    private String inscricaoEstadualTomador;
    private String razaoSocialTomador;
    private String tipoLogradouroTomador;
    private String enderecoTomador;
    private String numeroEnderecoTomador;
    private String complementoEnderecoTomador;
    private String bairroTomador;
    private String cidadeTomador;
    private String ufTomador;
    private String paisTomador;
    private String cepTomador;
    private String emailTomador;
    private String dddTomador;
    private String telefoneTomador;
    private String razaoSocialIntermediarioServico;
    private String inscricaoMunicipalIntermediarioServico;
    private String cpfCnpjIntermediarioServico;
    private String codigodaObra;
    private String art;
    private String serieRpsSubstituido;
    private String numeroRpsSubstituido;
    private String nroProcessoNatureza;
    private String numeroFatura;
    private String formaPagamento;
    private String valorFatura;
    private String versaoDados;
    private List<ItemVO> itensVO;
    private List<DeducaoVO> deducoesVO;

    @JsonCreator
    public NfsVO(@JsonProperty("transactionId") Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTransactionId() {
        return this.transactionId;
    }

    public String getDocNum() {
        return this.docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getTipoAmbienteSistema() {
        return this.tipoAmbienteSistema;
    }

    public void setTipoAmbienteSistema(String tipoAmbienteSistema) {
        this.tipoAmbienteSistema = tipoAmbienteSistema;
    }

    public String getCodCidade() {
        return this.codCidade;
    }

    public void setCodCidade(String codCidade) {
        this.codCidade = codCidade;
    }

    public String getCnpjPrestador() {
        return this.cnpjPrestador;
    }

    public void setCnpjPrestador(String cnpjPrestador) {
        this.cnpjPrestador = cnpjPrestador;
    }

    public String getNumeroRps() {
        return this.numeroRps;
    }

    public void setNumeroRps(String numeroRps) {
        this.numeroRps = numeroRps;
    }

    public String getSerieRps() {
        return this.serieRps;
    }

    public void setSerieRps(String serieRps) {
        this.serieRps = serieRps;
    }

    public String getDataEmissaoRps() {
        return this.dataEmissaoRps;
    }

    public void setDataEmissaoRps(String dataEmissaoRps) {
        this.dataEmissaoRps = dataEmissaoRps;
    }

    public String getDataCompetencia() {
        return this.dataCompetencia;
    }

    public void setDataCompetencia(String dataCompetencia) {
        this.dataCompetencia = dataCompetencia;
    }

    public String getTipoRps() {
        return this.tipoRps;
    }

    public void setTipoRps(String tipoRps) {
        this.tipoRps = tipoRps;
    }

    public String getSituacaoRps() {
        return this.situacaoRps;
    }

    public void setSituacaoRps(String situacaoRps) {
        this.situacaoRps = situacaoRps;
    }

    public String getNaturezadaOperacao() {
        return this.naturezadaOperacao;
    }

    public void setNaturezadaOperacao(String naturezadaOperacao) {
        this.naturezadaOperacao = naturezadaOperacao;
    }

    public String getMunicipioPrestacao() {
        return this.municipioPrestacao;
    }

    public void setMunicipioPrestacao(String municipioPrestacao) {
        this.municipioPrestacao = municipioPrestacao;
    }

    public String getMunicipioIncidencia() {
        return this.municipioIncidencia;
    }

    public void setMunicipioIncidencia(String municipioIncidencia) {
        this.municipioIncidencia = municipioIncidencia;
    }

    public String getPaisServico() {
        return this.paisServico;
    }

    public void setPaisServico(String paisServico) {
        this.paisServico = paisServico;
    }

    public String getDescricaoRps() {
        return this.descricaoRps;
    }

    public void setDescricaoRps(String descricaoRps) {
        this.descricaoRps = descricaoRps;
    }

    public String getCodigoServicoInterno() {
        return this.codigoServicoInterno;
    }

    public void setCodigoServicoInterno(String codigoServicoInterno) {
        this.codigoServicoInterno = codigoServicoInterno;
    }

    public String getCodigoCnae() {
        return this.codigoCnae;
    }

    public void setCodigoCnae(String codigoCnae) {
        this.codigoCnae = codigoCnae;
    }

    public String getItemListaServico() {
        return this.itemListaServico;
    }

    public void setItemListaServico(String itemListaServico) {
        this.itemListaServico = itemListaServico;
    }

    public String getCodigoTributacaoMunicipio() {
        return this.codigoTributacaoMunicipio;
    }

    public void setCodigoTributacaoMunicipio(String codigoTributacaoMunicipio) {
        this.codigoTributacaoMunicipio = codigoTributacaoMunicipio;
    }

    public String getAliquotaServicos() {
        return this.aliquotaServicos;
    }

    public void setAliquotaServicos(String aliquotaServicos) {
        this.aliquotaServicos = aliquotaServicos;
    }

    public String getBaseCalculo() {
        return this.baseCalculo;
    }

    public void setBaseCalculo(String baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    public String getValorIss() {
        return this.valorIss;
    }

    public void setValorIss(String valorIss) {
        this.valorIss = valorIss;
    }

    public String getTipoRecolhimento() {
        return this.tipoRecolhimento;
    }

    public void setTipoRecolhimento(String tipoRecolhimento) {
        this.tipoRecolhimento = tipoRecolhimento;
    }

    public String getValorIssRetido() {
        return this.valorIssRetido;
    }

    public void setValorIssRetido(String valorIssRetido) {
        this.valorIssRetido = valorIssRetido;
    }

    public String getResponsavelRetencao() {
        return this.responsavelRetencao;
    }

    public void setResponsavelRetencao(String responsavelRetencao) {
        this.responsavelRetencao = responsavelRetencao;
    }

    public String getValorLiquidoNfse() {
        return this.valorLiquidoNfse;
    }

    public void setValorLiquidoNfse(String valorLiquidoNfse) {
        this.valorLiquidoNfse = valorLiquidoNfse;
    }

    public String getValorServicos() {
        return this.valorServicos;
    }

    public void setValorServicos(String valorServicos) {
        this.valorServicos = valorServicos;
    }

    public String getValorPis() {
        return this.valorPis;
    }

    public void setValorPis(String valorPis) {
        this.valorPis = valorPis;
    }

    public String getValorCofins() {
        return this.valorCofins;
    }

    public void setValorCofins(String valorCofins) {
        this.valorCofins = valorCofins;
    }

    public String getValorInss() {
        return this.valorInss;
    }

    public void setValorInss(String valorInss) {
        this.valorInss = valorInss;
    }

    public String getValorIr() {
        return this.valorIr;
    }

    public void setValorIr(String valorIr) {
        this.valorIr = valorIr;
    }

    public String getValorCsll() {
        return this.valorCsll;
    }

    public void setValorCsll(String valorCsll) {
        this.valorCsll = valorCsll;
    }

    public String getOutrasRetencoes() {
        return this.outrasRetencoes;
    }

    public void setOutrasRetencoes(String outrasRetencoes) {
        this.outrasRetencoes = outrasRetencoes;
    }

    public String getValorDescontoIncondicionado() {
        return this.valorDescontoIncondicionado;
    }

    public void setValorDescontoIncondicionado(String valorDescontoIncondicionado) {
        this.valorDescontoIncondicionado = valorDescontoIncondicionado;
    }

    public String getValorDescontoCondicionado() {
        return this.valorDescontoCondicionado;
    }

    public void setValorDescontoCondicionado(String valorDescontoCondicionado) {
        this.valorDescontoCondicionado = valorDescontoCondicionado;
    }

    public String getRazaoSocialPrestador() {
        return this.razaoSocialPrestador;
    }

    public void setRazaoSocialPrestador(String razaoSocialPrestador) {
        this.razaoSocialPrestador = razaoSocialPrestador;
    }

    public String getEnderecoPrestador() {
        return this.enderecoPrestador;
    }

    public void setEnderecoPrestador(String enderecoPrestador) {
        this.enderecoPrestador = enderecoPrestador;
    }

    public String getNumeroEnderecoPrestador() {
        return this.numeroEnderecoPrestador;
    }

    public void setNumeroEnderecoPrestador(String numeroEnderecoPrestador) {
        this.numeroEnderecoPrestador = numeroEnderecoPrestador;
    }

    public String getComplementoEnderecoPrestador() {
        return this.complementoEnderecoPrestador;
    }

    public void setComplementoEnderecoPrestador(String complementoEnderecoPrestador) {
        this.complementoEnderecoPrestador = complementoEnderecoPrestador;
    }

    public String getBairroPrestador() {
        return this.bairroPrestador;
    }

    public void setBairroPrestador(String bairroPrestador) {
        this.bairroPrestador = bairroPrestador;
    }

    public String getCepPrestador() {
        return this.cepPrestador;
    }

    public void setCepPrestador(String cepPrestador) {
        this.cepPrestador = cepPrestador;
    }

    public String getUfPrestador() {
        return this.ufPrestador;
    }

    public void setUfPrestador(String ufPrestador) {
        this.ufPrestador = ufPrestador;
    }

    public String getPaisPrestador() {
        return this.paisPrestador;
    }

    public void setPaisPrestador(String paisPrestador) {
        this.paisPrestador = paisPrestador;
    }

    public String getDddPrestador() {
        return this.dddPrestador;
    }

    public void setDddPrestador(String dddPrestador) {
        this.dddPrestador = dddPrestador;
    }

    public String getTelefonePrestador() {
        return this.telefonePrestador;
    }

    public void setTelefonePrestador(String telefonePrestador) {
        this.telefonePrestador = telefonePrestador;
    }

    public String getEmailPrestador() {
        return this.emailPrestador;
    }

    public void setEmailPrestador(String emailPrestador) {
        this.emailPrestador = emailPrestador;
    }

    public String getRegimeEspecialTributacao() {
        return this.regimeEspecialTributacao;
    }

    public void setRegimeEspecialTributacao(String regimeEspecialTributacao) {
        this.regimeEspecialTributacao = regimeEspecialTributacao;
    }

    public String getOptanteSimplesNacional() {
        return this.optanteSimplesNacional;
    }

    public void setOptanteSimplesNacional(String optanteSimplesNacional) {
        this.optanteSimplesNacional = optanteSimplesNacional;
    }

    public String getIncentivadorCultural() {
        return this.incentivadorCultural;
    }

    public void setIncentivadorCultural(String incentivadorCultural) {
        this.incentivadorCultural = incentivadorCultural;
    }

    public String getCpfCnpjTomador() {
        return this.cpfCnpjTomador;
    }

    public void setCpfCnpjTomador(String cpfCnpjTomador) {
        this.cpfCnpjTomador = cpfCnpjTomador;
    }

    public String getInscricaoMunicipalTomador() {
        return this.inscricaoMunicipalTomador;
    }

    public void setInscricaoMunicipalTomador(String inscricaoMunicipalTomador) {
        this.inscricaoMunicipalTomador = inscricaoMunicipalTomador;
    }

    public String getIndicacaoCpfCnpj() {
        return this.indicacaoCpfCnpj;
    }

    public void setIndicacaoCpfCnpj(String indicacaoCpfCnpj) {
        this.indicacaoCpfCnpj = indicacaoCpfCnpj;
    }

    public String getInscricaoEstadualTomador() {
        return this.inscricaoEstadualTomador;
    }

    public void setInscricaoEstadualTomador(String inscricaoEstadualTomador) {
        this.inscricaoEstadualTomador = inscricaoEstadualTomador;
    }

    public String getRazaoSocialTomador() {
        return this.razaoSocialTomador;
    }

    public void setRazaoSocialTomador(String razaoSocialTomador) {
        this.razaoSocialTomador = razaoSocialTomador;
    }

    public String getTipoLogradouroTomador() {
        return this.tipoLogradouroTomador;
    }

    public void setTipoLogradouroTomador(String tipoLogradouroTomador) {
        this.tipoLogradouroTomador = tipoLogradouroTomador;
    }

    public String getEnderecoTomador() {
        return this.enderecoTomador;
    }

    public void setEnderecoTomador(String enderecoTomador) {
        this.enderecoTomador = enderecoTomador;
    }

    public String getNumeroEnderecoTomador() {
        return this.numeroEnderecoTomador;
    }

    public void setNumeroEnderecoTomador(String numeroEnderecoTomador) {
        this.numeroEnderecoTomador = numeroEnderecoTomador;
    }

    public String getComplementoEnderecoTomador() {
        return this.complementoEnderecoTomador;
    }

    public void setComplementoEnderecoTomador(String complementoEnderecoTomador) {
        this.complementoEnderecoTomador = complementoEnderecoTomador;
    }

    public String getBairroTomador() {
        return this.bairroTomador;
    }

    public void setBairroTomador(String bairroTomador) {
        this.bairroTomador = bairroTomador;
    }

    public String getCidadeTomador() {
        return this.cidadeTomador;
    }

    public void setCidadeTomador(String cidadeTomador) {
        this.cidadeTomador = cidadeTomador;
    }

    public String getUfTomador() {
        return this.ufTomador;
    }

    public void setUfTomador(String ufTomador) {
        this.ufTomador = ufTomador;
    }

    public String getPaisTomador() {
        return this.paisTomador;
    }

    public void setPaisTomador(String paisTomador) {
        this.paisTomador = paisTomador;
    }

    public String getCepTomador() {
        return this.cepTomador;
    }

    public void setCepTomador(String cepTomador) {
        this.cepTomador = cepTomador;
    }

    public String getEmailTomador() {
        return this.emailTomador;
    }

    public void setEmailTomador(String emailTomador) {
        this.emailTomador = emailTomador;
    }

    public String getDddTomador() {
        return this.dddTomador;
    }

    public void setDddTomador(String dddTomador) {
        this.dddTomador = dddTomador;
    }

    public String getTelefoneTomador() {
        return this.telefoneTomador;
    }

    public void setTelefoneTomador(String telefoneTomador) {
        this.telefoneTomador = telefoneTomador;
    }

    public String getRazaoSocialIntermediarioServico() {
        return this.razaoSocialIntermediarioServico;
    }

    public void setRazaoSocialIntermediarioServico(String razaoSocialIntermediarioServico) {
        this.razaoSocialIntermediarioServico = razaoSocialIntermediarioServico;
    }

    public String getInscricaoMunicipalIntermediarioServico() {
        return this.inscricaoMunicipalIntermediarioServico;
    }

    public void setInscricaoMunicipalIntermediarioServico(String inscricaoMunicipalIntermediarioServico) {
        this.inscricaoMunicipalIntermediarioServico = inscricaoMunicipalIntermediarioServico;
    }

    public String getCpfCnpjIntermediarioServico() {
        return this.cpfCnpjIntermediarioServico;
    }

    public void setCpfCnpjIntermediarioServico(String cpfCnpjIntermediarioServico) {
        this.cpfCnpjIntermediarioServico = cpfCnpjIntermediarioServico;
    }

    public String getCodigodaObra() {
        return this.codigodaObra;
    }

    public void setCodigodaObra(String codigodaObra) {
        this.codigodaObra = codigodaObra;
    }

    public String getArt() {
        return this.art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getSerieRpsSubstituido() {
        return this.serieRpsSubstituido;
    }

    public void setSerieRpsSubstituido(String serieRpsSubstituido) {
        this.serieRpsSubstituido = serieRpsSubstituido;
    }

    public String getNumeroRpsSubstituido() {
        return this.numeroRpsSubstituido;
    }

    public void setNumeroRpsSubstituido(String numeroRpsSubstituido) {
        this.numeroRpsSubstituido = numeroRpsSubstituido;
    }

    public String getNroProcessoNatureza() {
        return this.nroProcessoNatureza;
    }

    public void setNroProcessoNatureza(String nroProcessoNatureza) {
        this.nroProcessoNatureza = nroProcessoNatureza;
    }

    public int hashCode() {
        byte result = 1;
        int result1 = 31 * result + (this.transactionId == null?0:this.transactionId.hashCode());
        return result1;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj == null) {
            return false;
        } else if(!(obj instanceof NfsVO)) {
            return false;
        } else {
            NfsVO other = (NfsVO)obj;
            if(this.transactionId == null) {
                if(other.transactionId != null) {
                    return false;
                }
            } else if(!this.transactionId.equals(other.transactionId)) {
                return false;
            }

            return true;
        }
    }

    public List<ItemVO> getItensVO() {
        return this.itensVO;
    }

    public void setItensVO(List<ItemVO> itensVO) {
        this.itensVO = itensVO;
    }

    public List<DeducaoVO> getDeducoesVO() {
        return this.deducoesVO;
    }

    public void setDeducoesVO(List<DeducaoVO> deducoesVO) {
        this.deducoesVO = deducoesVO;
    }

    public String getInscricaoPrestador() {
        return this.inscricaoPrestador;
    }

    public void setInscricaoPrestador(String inscricaoPrestador) {
        this.inscricaoPrestador = inscricaoPrestador;
    }

    public String getNumeroFatura() {
        return this.numeroFatura;
    }

    public void setNumeroFatura(String numeroFatura) {
        this.numeroFatura = numeroFatura;
    }

    public String getFormaPagamento() {
        return this.formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getValorFatura() {
        return this.valorFatura;
    }

    public void setValorFatura(String valorFatura) {
        this.valorFatura = valorFatura;
    }

    public String getVersaoDados() {
        return this.versaoDados;
    }

    public void setVersaoDados(String versaoDados) {
        this.versaoDados = versaoDados;
    }
}
