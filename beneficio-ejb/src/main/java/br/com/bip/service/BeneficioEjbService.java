package br.com.bip.service;

import br.com.bip.entity.Beneficio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

@Stateless
public class BeneficioEjbService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Response transferir(Long fromId, Long toId, BigDecimal amount) {
        try {
            Beneficio from = Optional.ofNullable(em.find(Beneficio.class, fromId, LockModeType.PESSIMISTIC_WRITE))
                    .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));
            
            Beneficio to = Optional.ofNullable(em.find(Beneficio.class, toId, LockModeType.PESSIMISTIC_WRITE))
                    .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));

            validarSaldo(from, amount);

            from.setValor(from.getValor().subtract(amount));
            to.setValor(to.getValor().add(amount));

            em.merge(from);
            em.merge(to);
            
        } catch (Exception e) {
            throw new RuntimeException("Erro na transferência: " + e.getMessage(), e);
        }
		
        return Response.ok("Transferência Realizada").build();
        
       
    }

    private void validarSaldo(Beneficio conta, BigDecimal valor) {
        if (conta.getValor().compareTo(valor) < 0) {
            throw new IllegalStateException("Saldo insuficiente para a operação");
        }
    }
}