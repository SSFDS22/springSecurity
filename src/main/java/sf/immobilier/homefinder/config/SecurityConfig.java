package sf.immobilier.homefinder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests.requestMatchers("/test").permitAll().anyRequest().authenticated())
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

    /**
     * Cette methode va etre utiliser par springSecurité pour Hasher.
     * @return  BCryptPasswordEncoder
     * Spring va utiliser une instance de PasswordEncoder(interface) pour valider le mdp
     * use BCryptPasswordEncoder algorithme
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Cette Implementation va se basée sur la BDD qui est configuré.
     * Spring va créer l'object DataSource
     * @param dataSource database configurer
     * Pas compatible avec le implement UserDetails
     * @return
     */
    @Bean
    public UserDetailsService  userDetailsService(DataSource dataSource){
        return new  JdbcUserDetailsManager(dataSource);
    }

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        UserDetails admin = User.builder()
//                .username("salif")
//                .password(passwordEncoder().encode("salif"))
//                .authorities("admin")
//                .build();
//        return new InMemoryUserDetailsManager(admin);
//    }
}
