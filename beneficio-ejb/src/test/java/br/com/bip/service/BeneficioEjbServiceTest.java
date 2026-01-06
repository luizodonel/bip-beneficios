package br.com.bip.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;

import javax.persistence.EntityManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.bip.dto.TransferirDTO;
import br.com.bip.entity.Beneficio;

public class BeneficioEjbServiceTest {

	@InjectMocks
	private BeneficioEjbService beneficioService;

	@Mock
	private EntityManager em;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testTransferirComSucesso() {

		TransferirDTO dto = new TransferirDTO();
		dto.setFromId(1L);
		dto.setToId(2L);
		dto.setAmount(new BigDecimal(50));

		Beneficio origem = new Beneficio();
		origem.setId(1L);
		origem.setValor(new BigDecimal(100.0));

		Beneficio destino = new Beneficio();
		destino.setId(2L);
		destino.setValor(new BigDecimal(20));

		when(em.find(Beneficio.class, 1L)).thenReturn(origem);
		when(em.find(Beneficio.class, 2L)).thenReturn(destino);

		 beneficioService.transferir(1l, 2l, new BigDecimal(10));

		verify(em).merge(origem);
		verify(em).merge(destino);
	}
}