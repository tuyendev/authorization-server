package io.github.tuyendev.passport.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@Configuration
@EnableJpaRepositories(basePackages = "io.github.tuyendev.passport.repository",
        repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)
@EnableJpaAuditing(auditorAwareRef = "jpaAuditorProvider")
public class JpaPersistenceConfigurer {

    @Bean(name = "jpaAuditorProvider")
    AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

    static class AuditorAwareImpl implements AuditorAware<String> {

        //TODO get user from SecurityContextHolder
        @Override
        public Optional<String> getCurrentAuditor() {
            return Optional.empty();
        }
    }
}
