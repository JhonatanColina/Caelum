package br.com.caelum.casadocodigo.loja.Paymentfast;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentFastService 
{
	@Autowired
	RestTemplate restTemplate;
	
	public HttpStatus finishPayment(BigDecimal total)
	{
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String url = "http://book-payment.herokuapp.com/payment";
		try {
			ResponseEntity<String> response =  restTemplate.postForEntity(new URI(url), new ContractPaymentFast(total), String.class);
			if(response.getStatusCode() != HttpStatus.OK)
			{
				System.out.println("Erro no Pagamento");
			}
			else
			{
				System.out.println("Pagamento Ok");
				status = HttpStatus.OK;
			}
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
}
