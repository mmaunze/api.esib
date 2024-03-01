package com.esib.esib.service;

import com.esib.esib.repository.UtilizadorRepository;
import com.esib.esib.security.UserSpringSecurity;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Meldo Maunze
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class.getName());

    /**
     *
     */
    @Autowired
    private UtilizadorRepository utilizadorRepository;

    /**
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.utilizadorRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        return new UserSpringSecurity(user.get().getId(), user.get().getUsername(), user.get().getPassword(), user.get().getProfiles());
    }

}
