package org.devalurum.stockprice;

import org.devalurum.stockprice.settings.MoexSettings;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class TestConfig {

    @MockBean
    private MoexSettings moexSettings;

}
