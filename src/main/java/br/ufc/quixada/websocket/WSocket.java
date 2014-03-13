package br.ufc.quixada.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import br.ufc.quixada.pojo.Observacao;

@ServerEndpoint("/websocket")
public class WSocket {
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public String onMessage(String message) {
        return null;
    }

	@OnOpen
    public void onOpen (Session peer) throws IOException {
        peers.add(peer);
        Observacao observacao = new Observacao(-14.239424,-53.186502, 180,"TESTE", "ADD");
        Gson gson = new Gson();
        peer.getBasicRemote().sendText(gson.toJson(observacao));
        simulador();
    }

    @OnClose
    public void onClose (Session peer) {
        peers.remove(peer);
    }
    
    //Formato da mensagem: latitude,longitude,grau,hex,status
    //Exemplo: -14.239424,-53.186502,180,TESTE,ADD
    private static void simulador() throws IOException{
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  
    	Gson gson = new Gson();
    	String mensagem;
    	
    	while(true){
    		System.out.println(":");
    		
    		mensagem = in.readLine();
			String[] mensagem_quebrada = mensagem.split(Pattern.quote(","));
    		
        	for(Session peer : peers){
                peer.getBasicRemote().sendText(gson.toJson(new Observacao(Double.parseDouble(mensagem_quebrada[0]),Double.parseDouble(mensagem_quebrada[1]), Integer.parseInt(mensagem_quebrada[2]),mensagem_quebrada[3], mensagem_quebrada[4])));
        	}
    		
    	}
    }
    
}