package com.linguagensextensiveis.trabalhofinal.notas.objects;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Xml {
	public NfeProc nfeProc;

	@JacksonXmlProperty(localName = "versao", isAttribute = true)
	public String versao;

	public NfeProc getNfeProc() {
		return nfeProc;
	}

	public void setNfeProc(NfeProc nfeProc) {
		this.nfeProc = nfeProc;
	}
}

class NfeProc {
	public NFe NFe;
	public ProtNFe protNFe;

	public NFe getNFe() {
		return NFe;
	}

	public void setNFe(NFe NFe) {
		this.NFe = NFe;
	}
}

class NFe {
	public InfNFe infNFe;
	@JacksonXmlProperty(localName = "versao", isAttribute = true)
	public String versao;

	@JacksonXmlProperty(localName = "Id", isAttribute = true)
	public String Id;

	public Signature Signature;
}

class ProtNFe {
	public InfProt infProt;
}

class InfNFe {
	public Ide ide;
	public Emit emit;
	public Dest dest;
	public Entrega entrega;
	public Det[] det;
	public Total total;
	public Transp transp;
	public InfAdic infAdic;
}

class Signature {
	public SignedInfo SignedInfo;
	public String SignatureValue;
	public KeyInfo KeyInfo;
}

class InfProt {
	public String tpAmb;
	public String verAplic;
	public String chNFe;
	public String dhRecbto;
	public String nProt;
	public String digVal;
	public String cStat;
	public String xMotivo;
}

class Ide {
	public String cUF;
	public String cNF;
	public String natOp;
	public String indPag;
	public String mod;
	public String serie;
	public String nNF;
	public String dEmi;
	public String tpNF;
	public String cMunFG;
	public String tpImp;
	public String tpEmis;
	public String cDV;
	public String tpAmb;
	public String finNFe;
	public String procEmi;
	public String verProc;
}

class Emit {
	public String CNPJ;
	public String xNome;
	public String xFant;
	public Ender enderEmit;
	public String IE;
	public String CRT;
}

class Dest {
	public String CPF;
	public String xNome;
	public Ender enderDest;
	public String IE;
	public String email;
}

class Entrega {
	public String CNPJ;
	public String xLgr;
	public String nro;
	public String xCpl;
	public String xBairro;
	public String cMun;
	public String xMun;
	public String UF;
}

class Det {
	public Prod prod;
	public Imposto imposto;
	public String infAdProd;
}

class Total {
	public ICMSTot ICMSTot;
}

class Transp {
	public String modFrete;
	public Transporta transporta;
	public Vol vol;
}

class InfAdic {
	public String infCpl;
}

class SignedInfo {
	public String canonicalizationMethod;
	public String signatureMethod;
	public Reference reference;
}

class KeyInfo {
	public XCertificate X509Data;
}

class Ender {
	public String xLgr;
	public String nro;
	public String xCpl;
	public String xBairro;
	public String cMun;
	public String xMun;
	public String UF;
	public String CEP;
	public String cPais;
	public String xPais;
	public String fone;
}

class Prod {
	public String cProd;
	public String cEAN;
	public String xProd;
	public String NCM;
	public String CFOP;
	public String uCom;
	public String qCom;
	public String vUnCom;
	public String vProd;
	public String cEANTrib;
	public String uTrib;
	public String qTrib;
	public String vUnTrib;
	public String vFrete;
	public String indTot;
}

class Imposto {
	public ICMS ICMS;
	public IPI IPI;
	public PIS PIS;
	public COFINS COFINS;
}

class ICMS {
	public ICMS00 ICMS00;
	public ICMS60 ICMS60;
}

class IPI {
	public String cEnq;
	public Nt IPINT;
}

class PIS {
	public PISAliq PISAliq;
	public Nt PISNT;
}

class COFINS {
	public COFINSAliq COFINSAliq;
	public Nt COFINSNT;
}

class ICMS00 {
	public String orig;
	public String CST;
	public String modBC;
	public String vBC;
	public String pICMS;
	public String vICMS;
}

class ICMS60 {
	public String orig;
	public String CST;
	public String vBCSTRet;
	public String vICMSSTRet;
}

class Nt {
	public String CST;
}

class PISAliq {
	public String CST;
	public String vBC;
	public String pPIS;
	public String vPIS;
}

class COFINSAliq {
	public String CST;
	public String vBC;
	public String pCOFINS;
	public String vCOFINS;
}

class ICMSTot {
	public String vBC;
	public String vICMS;
	public String vBCST;
	public String vST;
	public String vProd;
	public String vFrete;
	public String vSeg;
	public String vDesc;
	public String vII;
	public String vIPI;
	public String vPIS;
	public String vCOFINS;
	public String vOutro;
	public String vNF;
}

class Transporta {
	public String CNPJ;
	public String xNome;
	public String IE;
	public String xEnder;
	public String xMun;
	public String UF;
}

class Vol {
	public String qVol;
	public String esp;
	public String pesoL;
	public String pesoB;
}




