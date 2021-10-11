# websocket
웹소켓 sockjs, stomp 코드남기기

1. WebSocketConfig
@Configuration : 어노테이션기반 환경구성을 도움, 해당 클래스 bean의 설정을 나타냄 
@EnableWebSocketMessageBroker : websocket서버를 사용한다는 설정

public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws").withSockJS();
}
=> websocket연결 엔드포인트를 등록

public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.setApplicationDestinationPrefixes("/app"); 
    // "/app"으로 시작하는 메세지가 메세지 핸들러로 라우팅되도록 정의
    registry.enableSimpleBroker("/topic");
    // "/topic"으로 시작하는 주자레르 가진 메세지 핸들러로 라이팅하여 해당 주제에 가입한 모든 클라이언트에게 메세지 "broadcast" 한다
}
=> 한 클라이언트에서 다른 클라이언트로 메세지 라우팅 할 때 사용하는 브로커를 구성


2. ChatController
@MessageMapping : 클라이언트에서 보내는 메시지 매핑
@SendTo : 1:n으로 메시지를 뿌릴때 사용하는 구조

3. WebSocketEventListener
@Component  
 => 스프링 빈 설정 XML 파일에 <bean id="..." class="..."/>나 자바 @Configuration 클래스에서 @Bean을 붙여 빈을 등록하던 것처럼 빈 클래스에 @Component 애노테이션을 붙여 빈을 등록할 수 있다.
    즉 @Component를 사용해서 빈 설정 파일이 아니라 빈 클래스에서 빈을 직접 등록할 수 있다.
@EventListener : Spring 4.2부터는 이벤트 리스너가 ApplicationListener 인터페이스를 구현하는 Bean 일 필요가 없어졌습니다.

4. index.html
 1) function connect(event)
 2) function onConnected()
 3) function onError(error)
 4) function sendMessage(event)
 5) function onMessageReceived(payload)



* 참고블로그
- https://atoz-develop.tistory.com/
- https://ratseno.tistory.com/
 