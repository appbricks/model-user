package com.leapvest.repository.user;

import com.leapvest.TestContext;
import com.leapvest.model.user.SocialConnection;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.inject.Inject;

@ContextConfiguration(classes = { TestContext.class })
public class SocialConnectionRepositoryTest
    extends AbstractTestNGSpringContextTests {

    @Inject
    private SocialConnectionRepository socialConnectionRepository;

    @Test
    public void shouldTestCreation()
        throws Exception {

//        SocialConnection SocialConnection = new SocialConnection
//                ("jane", "google", "google-jiwhiz", 1, "Yuan", "http://", "http://", "token", "secret", "token", 1000000l);
//        SocialConnectionRepository.save(SocialConnection);
//
//        SocialConnection = new SocialConnection
//                ("jiwhiz2", "google", "google-jiwhiz2", 2, "Yuan", "http://", "http://", "token", "secret", "token", 1000000l);
//        SocialConnectionRepository.save(SocialConnection);
//
//        SocialConnection = new SocialConnection
//                ("jiwhiz", "facebook", "facebook-jiwhiz", 1, "Yuan", "http://", "http://", "token", "secret", "token", 1000000l);
//        SocialConnectionRepository.save(SocialConnection);
//        SocialConnection = new SocialConnection
//                ("jiwhiz2", "facebook", "facebook-jiwhiz2", 1, "Yuan", "http://", "http://", "token", "secret", "token", 1000000l);
//        SocialConnectionRepository.save(SocialConnection);
//
//        SocialConnection = new SocialConnection
//                ("jiwhiz", "twitter", "twitter-jiwhiz", 1, "Yuan", "http://", "http://", "token", "secret", "token", 1000000l);
//        SocialConnectionRepository.save(SocialConnection);
//        SocialConnection = new SocialConnection
//                ("jiwhiz2", "twitter", "twitter-jiwhiz2", 1, "Yuan", "http://", "http://", "token", "secret", "token", 1000000l);
//        SocialConnectionRepository.save(SocialConnection);
//
//        SocialConnection = new SocialConnection
//                ("other", "facebook", "facebook-other", 1, "Yuan", "http://", "http://", "token", "secret", "token", 1000000l);
//        SocialConnectionRepository.save(SocialConnection);
//        SocialConnection = new SocialConnection
//                ("other", "twitter", "twitter-other", 1, "Yuan", "http://", "http://", "token", "secret", "token", 1000000l);
//        SocialConnectionRepository.save(SocialConnection);
    }
}