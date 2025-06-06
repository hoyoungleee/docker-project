package com.playdata.orderingservice.ordering.controller;

import com.playdata.orderingservice.common.auth.TokenUserInfo;
import com.playdata.orderingservice.ordering.dto.OrderResponseDto;
import com.playdata.orderingservice.ordering.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class SseController { //서버센트이벤트,
    // 알림을 위한 연결을 생성할 때, 각관리자의 이메일을 키로 해서 emitter 객체를 저장
    // ConcurrentHashMap : 멀티 스레드 기반 해시맵(HashMap은 싱글 스레드 기반)
    Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    @GetMapping("/subscribe")
    public SseEmitter subscribe(@AuthenticationPrincipal TokenUserInfo userInfo) {
        //알림 서비스 구현 핵심 객체
        SseEmitter emitter = new SseEmitter(24 * 60 * 60 * 1000L);
        log.info("subscribe to {}", userInfo.getEmail());

        //관리자의 이메일을 키값으로 emitter 객체 저장.
        emitters.put(userInfo.getEmail(), emitter);

        //연결 성공 메세지 전송
        try {
            emitter.send(
                SseEmitter.event()
                        .name("connect")
                        .data("connected!!")
            );

            //30초 마다 heartbeat 메세지를 전송해서 연결유지
            //클라이언트에서 사용하는 EventSourcePolyfill이 45초동안 활동이 없으면 지망대로 연결 종료.
            Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() ->{
               //일정하게 동작 시킬 로직을 작성
                try {
                    emitter.send(
                            SseEmitter.event()
                                    .name("heartbeat")
                                    .data("keep-alive") //클라이언트 단이 살아있는지 확인
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                    log.info("Failed to send heartbeat event");
                }
            }, 30, 30, TimeUnit.SECONDS);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return emitter;
    }


}
