package ashis.com.backend.Street_light.config;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    @Bean(name = "asyncExecutor")
    public AsyncTaskExecutor asyncExecutor() {
        HikariConfig config = new HikariConfig();
        config.setMinimumIdle(5);      // Minimum number of idle connections
        config.setMaximumPoolSize(20); // Maximum size of the connection pool

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(40);
        executor.setQueueCapacity(85);
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }
}