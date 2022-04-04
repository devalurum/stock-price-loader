package org.dvigal.lesson.spring_2.settings;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.net.URI;


@ConstructorBinding
@ConfigurationProperties("app-config.moex")
@Validated
@RequiredArgsConstructor
@Getter
public class MoexSettings {
    @NotNull
    private final URI stockPriceUrl;
}
