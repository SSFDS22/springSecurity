package sf.immobilier.homefinder.config;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sf.immobilier.homefinder.model.Customer;
import sf.immobilier.homefinder.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * En implementant cette classe on dit à spring security comment loader un user
 * faut decommenter le JdbcUserDetailsManager pour que ça fonctionne
 */
@AllArgsConstructor
@Service
public class SecurityUserDetailsService implements UserDetailsService {
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String userMail) throws UsernameNotFoundException {
        String userName, password = null;
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<Customer> customers = this.customerRepository.findByEmail(userMail);
        if (customers.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for the user : " + userMail);
        }
        userName = customers.getFirst().getEmail();
        password = customers.getFirst().getPwd();
        authorityList.add(new SimpleGrantedAuthority(customers.getFirst().getRole()));
        return new User(userName, password, authorityList);
    }
}
