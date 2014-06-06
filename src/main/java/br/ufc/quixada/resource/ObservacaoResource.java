package br.ufc.quixada.resource;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.ufc.quixada.dao.ObservacaoDao;
import br.ufc.quixada.pojo.Observacao;
import br.ufc.quixada.websocket.WSocket;

@Path("/observacao")
public class ObservacaoResource {
	
	@POST
	@Consumes("application/json")
	public static Response adicionarObservacao(Observacao obs) throws IOException{
		ObservacaoDao.adicionarObservacao(obs.getRadar(), obs.getLatitude(), obs.getLongitude(), obs.getAltitude(), obs.getVelocidade(), obs.getAngulo(), obs.getHora(), obs.getRoda_id(), obs.getHex());
		obs.removerAspas();
		WSocket.broadcast(obs);
		String result = "Ok";
		return Response.status(201).entity(result).build();
	}
	
}
