package br.ufc.quixada.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import br.ufc.quixada.dao.ObservacaoDao;
import br.ufc.quixada.pojo.ObservacaoJson;

import com.google.gson.JsonObject;

@Path("/observacao")
public class ObservacaoResource {
	
	@POST
	@Consumes("application/json")
	public static Response adicionarObservacao(ObservacaoJson obs){
		ObservacaoDao.adicionarObservacao(obs.getRadar(), obs.getLatitude(), obs.getLongitude(), obs.getAltitude(), obs.getVelocidade(), obs.getAngulo(), obs.getHora(), obs.getRoda_id(), obs.getHex());
		String result = "Ok";
		return Response.status(201).entity(result).build();
	}
	
}
