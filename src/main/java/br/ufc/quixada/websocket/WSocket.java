package br.ufc.quixada.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
    }

    @OnClose
    public void onClose (Session peer) {
        peers.remove(peer);
    }
}