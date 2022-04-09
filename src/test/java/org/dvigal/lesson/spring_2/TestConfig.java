package org.dvigal.lesson.spring_2;

import org.dvigal.lesson.spring_2.settings.MoexSettings;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class TestConfig {

    @MockBean
    private MoexSettings moexSettings;

}
