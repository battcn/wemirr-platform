package com.wemirr.framework.websocket.configuration;


import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * 在@ServerEndpoint注解里面添加configurator属性[@ServerEndpoint(value="/socketTest",configurator=GetHttpSessionConfigurator.class)]
 * 可以在OnOpen中通过HttpSession.class.getName()获取到http session,
 * 那么我们就可以使用session.getId()来作为标识
 * <pre>@OnOpen
 * public void onOpen(Session session,EndpointConfig config) {
 * HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
 * sessionMap.put(session.getId(), session);
 * }</pre>
 *
 * @author Levin
 */
public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
//        import javax.servlet.http.HttpSession;
//        HttpSession httpSession = (HttpSession) request.getHttpSession();
//        sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }

}