package com.linguagensextensiveis.trabalhofinal.notas;

import org.json.JSONObject;

import java.math.BigDecimal;

public class ICMS {
	public BigDecimal COFINS;
	public BigDecimal BCST;
	public BigDecimal Prod;
	public BigDecimal Seg;
	public BigDecimal NF;
	public BigDecimal PIS;
	public BigDecimal BC;
	public BigDecimal ST;
	public BigDecimal ICMS;
	public BigDecimal II;
	public BigDecimal Desc;
	public BigDecimal Outro;
	public BigDecimal IPI;
	public BigDecimal Frete;

public ICMS() {
		COFINS = BigDecimal.ZERO;
		BCST = BigDecimal.ZERO;
		Prod = BigDecimal.ZERO;
		Seg = BigDecimal.ZERO;
		NF = BigDecimal.ZERO;
		PIS = BigDecimal.ZERO;
		BC = BigDecimal.ZERO;
		ST = BigDecimal.ZERO;
		ICMS = BigDecimal.ZERO;
		II = BigDecimal.ZERO;
		Desc = BigDecimal.ZERO;
		Outro = BigDecimal.ZERO;
		IPI = BigDecimal.ZERO;
		Frete = BigDecimal.ZERO;
	}

	public BigDecimal all() {
		return COFINS.add(BCST).add(Seg).add(PIS).add(ST).add(ICMS).add(II).add(Desc).add(Outro).add(IPI).add(Frete);
	}

	public BigDecimal getFrete() {
		return Frete;
	}

	public void calculete(JSONObject total) {
		COFINS = COFINS.add(total.getBigDecimal("vCOFINS"));
		BCST   = BCST.add(total.getBigDecimal("vBCST"));
		Prod   = Prod.add(total.getBigDecimal("vProd"));
		Seg    = Seg.add(total.getBigDecimal("vSeg"));
		NF     = NF.add(total.getBigDecimal("vNF"));
		PIS    = PIS.add(total.getBigDecimal("vPIS"));
		BC     = BC.add(total.getBigDecimal("vBC"));
		ST     = ST.add(total.getBigDecimal("vST"));
		ICMS   = ICMS.add(total.getBigDecimal("vICMS"));
		II     = II.add(total.getBigDecimal("vII"));
		Desc   = Desc.add(total.getBigDecimal("vDesc"));
		Outro  = Outro.add(total.getBigDecimal("vOutro"));
		IPI    = IPI.add(total.getBigDecimal("vIPI"));
		Frete  = Frete.add(total.getBigDecimal("vFrete"));
	}
}
