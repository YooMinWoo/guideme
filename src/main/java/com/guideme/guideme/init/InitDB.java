package com.guideme.guideme.init;

import com.guideme.guideme.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDB implements ApplicationRunner {

    private final UserInit userInit;
    private final RegionInit regionInit;
    private final PostInit postInit;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        User user = userInit.userSignup();      // 일반 회원
        User admin = userInit.adminSignup();    // 관리자
        User guide = userInit.guideSignup();    // 가이드

        regionInit.createRegion(admin.getId());     // 관리자로 지역 등록
        postInit.createPost(guide.getId());     // 가이드로 게시물 등록
    }
}
