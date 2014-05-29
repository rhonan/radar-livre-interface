package br.ufc.quixada.websocket;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Pattern;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import br.ufc.quixada.pojo.Observacao;

import com.google.gson.Gson;

@ServerEndpoint("/websocket")
public class WSocket {
    private static final Set<WSocket> connections = new CopyOnWriteArraySet<>();
    private Session session;

    @OnMessage
    public String onMessage(String message) {
        return null;
    }

	@OnOpen
    public void onOpen (Session session) throws IOException {
        this.session = session;
        connections.add(this);
        Observacao observacao = new Observacao(-14.239424,-53.186502, 200, 1000, 180,"TESTE", "ADD");
        Gson gson = new Gson();
        session.getBasicRemote().sendText(gson.toJson(observacao));
    }

    @OnClose
    public void onClose () throws IOException {
        connections.remove(this);
    }
    
    //Formato da mensagem: latitude,longitude,grau,hex,status
    //Exemplo: -14.239424,-53.186502,180,TESTE,ADD
    public static void broadcast(String mensagem) throws IOException{
    	Gson gson = new Gson();

    	String[] mensagem_quebrada = mensagem.split(Pattern.quote(","));

    	for(WSocket client : connections){
    		try{
    			client.session.getBasicRemote().sendText(gson.toJson(new Observacao(Double.parseDouble(mensagem_quebrada[0]),Double.parseDouble(mensagem_quebrada[1]), Double.parseDouble(mensagem_quebrada[2]),Integer.parseInt(mensagem_quebrada[3]), Integer.parseInt(mensagem_quebrada[4]), mensagem_quebrada[5], mensagem_quebrada[6])));
    		}catch(IOException e){
    			connections.remove(client);
    			try{
    				client.session.close();
    			}catch(IOException el){

    			}
    		}

    	}
    }
    
}