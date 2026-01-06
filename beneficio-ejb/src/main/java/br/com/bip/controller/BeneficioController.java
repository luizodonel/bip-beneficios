package br.com.bip.controller;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bip.dto.TransferirDTO;
import br.com.bip.service.BeneficioEjbService;

@Path("/beneficios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BeneficioController {

    @EJB
    private BeneficioEjbService beneficioService;

    @POST
    @Path("/transferir")
    public Response transferir(TransferirDTO dto) {
       return beneficioService.transferir(dto.getFromId(), dto.getToId(), dto.getAmount());
    }
}	