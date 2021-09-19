package com.vmware.weathervane.workloadDriver.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.spring.autoconfigure.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WavefrontMicrometerConfig {

    @Bean
    MeterRegistryCustomizer meterRegistryCustomizer(MeterRegistry meterRegistry) {
        return mr -> {
            meterRegistry.config()
                    .commonTags("application", "wv-workload-runner");
        };
    }
}
