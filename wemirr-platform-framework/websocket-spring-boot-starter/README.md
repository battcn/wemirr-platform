## Websocket使用

### 基础配置参考
```
  websocket:
    manager:
      type: REDIS
    heart-check:
      enabled: true
      trigger: 30 * * * * ?
```
## 这是一个配置模板,需要配置一下三个选项即可开启心跳监测
### 开启心跳检测
spring.websocket.heartCheck.enable=true
### 每隔 30s 检查一次
spring.websocket.heartCheck.trigger=30 * * * * ?
### 检查到心跳更新时间大于这么毫秒就认为断开了（心跳时间）
spring.websocket.heartCheck.timeSpan = 10000
### 容忍没有心跳次数
spring.websocket.heartCheck.errorToleration = 30;

## 接入tenantCode：租户id，identifier：token
```
@Slf4j
@Component
@ServerEndpoint(value = "/message/{tenantCode}/{identifier}")
public class WebSocketMessageEndpoint extends BaseWebSocketEndpoint {
    
    @OnOpen
    public void openSession(@PathParam("tenantCode") String tenantCode, @PathParam(IDENTIFIER) String userId, Session session) {
        connect(userId, session);
       
        if (CollectionUtil.isEmpty(messages)) {
            return;
        }
        messages.forEach(message -> senderMessage(userId, JSON.toJSONString(message)));
    }
    
    @OnMessage
    public void onMessage(@PathParam(IDENTIFIER) String userId, Session session, String message) {
        log.info("接收到的消息" + message);
    }
    
    @OnClose
    public void onClose(@PathParam(IDENTIFIER) String userId, Session session) {
        disconnect(userId);
    }
    
    @OnError
    public void onError(@PathParam(IDENTIFIER) String userId, Session session, Throwable throwable) {
        log.info("发生异常：, identifier {} ", userId);
        log.error(throwable.getMessage(), throwable);
        disconnect(userId);
    }
}
```

