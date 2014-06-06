package br.ufc.quixada.websocket;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONObject;

import br.ufc.quixada.dao.ObservacaoDao;
import br.ufc.quixada.pojo.Observacao;

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
        //Observacao observacao = new Observacao(-14.239424,-53.186502, 200, 1000, 180,"TESTE", "ADD");
        //Gson gson = new Gson();
        session.getBasicRemote().sendText(ObservacaoDao.retornarObservacoesMaisAtuais().toString());
    }

    @OnClose
    public void onClose () throws IOException {
        connections.remove(this);
    }
    
    //Formato da mensagem: latitude,longitude,grau,hex,status
    //Exemplo: -14.239424,-53.186502,180,TESTE,ADD
    public static void broadcast(Observacao obs) throws IOException{
    	JSONArray jsonArray = new JSONArray();
    	JSONObject jsonObj = new JSONObject(obs);
    	jsonArray.put(jsonObj);

    	for(WSocket client : connections){
    		try{
    			client.session.getBasicRemote().sendText(jsonArray.toString());
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