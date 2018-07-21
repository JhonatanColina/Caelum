package br.com.caelum.casadocodigo.loja.Paymentfast;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

class ContractPaymentFast {
	@JsonProperty("value")
	private BigDecimal value;

	public ContractPaymentFast(BigDecimal value) {
		super();
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}
	
}