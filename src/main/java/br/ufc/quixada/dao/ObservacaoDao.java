package br.ufc.quixada.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import br.ufc.quixada.utils.ConexaoBanco;

public class ObservacaoDao {
	
	public static void adicionarObservacao(String radar, String latitude, String longitude, String altitude, String velocidade, String angulo, String hora, String rota_id, String hex){
		Connection connection;
		String sql = "INSERT INTO observacao (radar,latitude,longitude,altitude,velocidade,angulo,hora,rota_id,hex) VALUES ("+ radar +","+ latitude +","+ longitude +","+ altitude +","+ velocidade+","+ angulo +","+ hora+","+rota_id+","+hex+")";
		try {
			
			connection = ConexaoBanco.AbrirConexao();
			ConexaoBanco.executeInsert(connection, sql);
			connection.close();
			
			

	
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	
	}
	}
	
	public static JSONArray retornarObservacoes(){
		Connection connection;
		String sql="SELECT id,radar,latitude,longitude,altitude,velocidade,angulo,hora,rota_id,hex FROM observacao";
		
		String id;
		String radar;
		String latitude;
		String longitude;
		String altitude;
		String velocidade;
		String angulo;
		String hora;
		String rota_id;
		String hex;
		
		JSONArray arrayObj = new JSONArray();
		
		try {
			connection = ConexaoBanco.AbrirConexao();
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				JSONObject observacoes = new JSONObject();
				id= (rs.getString("id"));
				radar = (rs.getString("radar"));
				latitude = (rs.getString("latitude"));
				longitude = (rs.getString("longitude"));
				altitude = (rs.getString("altitude"));
				velocidade = (rs.getString("velocidade"));
				angulo = (rs.getString("angulo"));
				hora = (rs.getString("hora"));
				rota_id = (rs.getString("rota_id"));
				hex = (rs.getString("hex"));
				observacoes.put("id",id);
				observacoes.put("radar",radar);
				observacoes.put("latitude",latitude);
				observacoes.put("longitude",longitude);
				observacoes.put("altitude",altitude);
				observacoes.put("velocidade",velocidade);
				observacoes.put("angulo",angulo);
				observacoes.put("hora",hora);
				observacoes.put("rota_id",rota_id);
				observacoes.put("hex",hex);
				//	System.out.println(isbn + " " + nome_titulo + " " + tipo_titulo);
				arrayObj.put(observacoes);
				
			}
			rs.close();
			pstm.close();
			connection.close();

	
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	
	}
		
		return arrayObj;
	}
	
	public static JSONArray retornarObservacaoPorId(String id_observacaoBuscado){
		Connection connection;
		String sql="SELECT id,radar,latitude,longitude,altitude,velocidade,angulo,hora,rota_id,hex FROM observacao WHERE id='"+id_observacaoBuscado+"'";
		
		String id;
		String radar;
		String latitude;
		String longitude;
		String altitude;
		String velocidade;
		String angulo;
		String hora;
		String rota_id;
		String hex;
		
		JSONArray arrayObj = new JSONArray();
		
		try {
			connection = ConexaoBanco.AbrirConexao();
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				JSONObject observacoes = new JSONObject();
				id= (rs.getString("id"));
				radar = (rs.getString("radar"));
				latitude = (rs.getString("latitude"));
				longitude = (rs.getString("longitude"));
				altitude = (rs.getString("altitude"));
				velocidade = (rs.getString("velocidade"));
				angulo = (rs.getString("angulo"));
				hora = (rs.getString("hora"));
				rota_id = (rs.getString("rota_id"));
				hex = (rs.getString("hex"));
				observacoes.put("id",id);
				observacoes.put("radar",radar);
				observacoes.put("latitude",latitude);
				observacoes.put("longitude",longitude);
				observacoes.put("altitude",altitude);
				observacoes.put("velocidade",velocidade);
				observacoes.put("angulo",angulo);
				observacoes.put("hora",hora);
				observacoes.put("rota_id",rota_id);
				observacoes.put("hex",hex);
				//	System.out.println(isbn + " " + nome_titulo + " " + tipo_titulo);
				arrayObj.put(observacoes);
				
			}
			rs.close();
			pstm.close();
			connection.close();

	
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	
	}
		
		return arrayObj;
	}
	
	public static void atualizarObservacao(String id, String radar, String latitude, String longitude, String altitude, String velocidade, String angulo, String hora, String rota_id, String hex)
	{
		Connection connection;
		
		String sql = "UPDATE observacao SET radar='"+ radar +"', latitude='"+ latitude +"', longitude='"+ longitude +"', altitude='"+altitude+"', velocidade='"+velocidade+"', angulo='"+angulo+"', hora='"+hora+"', rota_id='"+rota_id+"', hex='"+hex+"' WHERE id='"+ id +"'";

		try {
			
			connection = ConexaoBanco.AbrirConexao();
			ConexaoBanco.executeQuery(connection, sql);
			
			connection.close();
			

	
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	
	}
	}
	
	public static JSONArray retornarObservacoesMaisAtuais(){
		Connection connection;
		String sql="SELECT * FROM observacao WHERE hora >= (now() - interval '3 minutes');";
		
		String id;
		String radar;
		String latitude;
		String longitude;
		String altitude;
		String velocidade;
		String angulo;
		String hora;
		String rota_id;
		String hex;
		
		JSONArray arrayObj = new JSONArray();
		
		try {
			connection = ConexaoBanco.AbrirConexao();
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				if(rs.getString("latitude") != null){
					JSONObject observacoes = new JSONObject();
					id= (rs.getString("id"));
					radar = (rs.getString("radar"));
					latitude = (rs.getString("latitude"));
					longitude = (rs.getString("longitude"));
					altitude = (rs.getString("altitude"));
					velocidade = (rs.getString("velocidade"));
					angulo = (rs.getString("angulo"));
					hora = (rs.getString("hora"));
					rota_id = (rs.getString("rota_id"));
					hex = (rs.getString("hex"));
					observacoes.put("id",id);
					observacoes.put("radar",radar);
					observacoes.put("latitude",latitude);
					observacoes.put("longitude",longitude);
					observacoes.put("altitude",altitude);
					observacoes.put("velocidade",velocidade);
					observacoes.put("angulo",angulo);
					observacoes.put("hora",hora);
					observacoes.put("rota_id",rota_id);
					observacoes.put("hex",hex);
					arrayObj.put(observacoes);
				}
			}
			rs.close();
			pstm.close();
			connection.close();

	
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	
	}
		
		return arrayObj;

	}
	
}