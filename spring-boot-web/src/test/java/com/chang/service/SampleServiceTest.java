package com.chang.service;

import com.chang.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PowerMockIgnore({"javax.management.*", "javax.net.ssl.*"})
@PrepareForTest(SampleService.class)
public class SampleServiceTest {

    @Autowired
    private SampleInterface sampleService;

    @Test
    public void publicMethod() throws Exception {
        SampleInterface serviceMock = PowerMockito.spy(sampleService);
        User res = new User();
        res.setNickName("mock user");
        // if sampleService is a proxy class. mock private method will fail.
        // because sampleService's declaredMethods do not have private method.
        // TEST DO/DON't ADD @Transaction on publicMethod()
        PowerMockito.doReturn(res).when(serviceMock, "validation",
                ArgumentMatchers.anyInt(), ArgumentMatchers.anyString(), ArgumentMatchers.any(User.class));

        User user = serviceMock.publicMethod(999, "", new User());
        System.out.println(user);
    }

}