package mixin.java.sdk;

import mixin.java.sdk.algorithm.JWToken;
import mixin.java.sdk.api.client.impl.TestGroupInfoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MixinJavaSdkApplicationTests {

    @Resource
    private App app;

    static Object lock = new Object();

    @Test
    void contextLoads() {
    }

    @Test
    void testApp() throws InterruptedException {
        JWToken.register(new TestGroupInfoImpl());
        app.run(1);
        while(true){
            synchronized(lock){
                // 除非有线程唤醒他 lock.notify();
                lock.wait();
            }
        }
    }
}
