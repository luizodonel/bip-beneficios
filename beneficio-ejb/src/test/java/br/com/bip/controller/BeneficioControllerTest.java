package br.com.bip.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.bip.dto.TransferirDTO;
import br.com.bip.service.BeneficioEjbService;

public class BeneficioControllerTest {

	@InjectMocks
	private BeneficioController beneficioController;

	@Mock
	private BeneficioEjbService beneficioService;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void testEndpointTransferirRetornoOk() {
	    TransferirDTO dto = new TransferirDTO();
	    dto.setFromId(10L);
	    dto.setToId(20L);
	    dto.setAmount(new BigDecimal(10));

	    Response mockResponse = Response.ok("Transferência Realizada").build();

	    when(beneficioService.transferir(10L, 20L, new BigDecimal(10)))
	        .thenReturn(mockResponse);

	    Response response = beneficioController.transferir(dto);

	    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	    assertEquals("Transferência Realizada", response.getEntity());
	}
}