package cz.cvut.fel.ear.lingo.user.service;

import cz.cvut.fel.ear.lingo.user.entity.UserEntity;
import cz.cvut.fel.ear.lingo.user.entity.repository.UserEntityRepository;
import cz.cvut.fel.ear.lingo.user.security.model.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserEntityRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity user = repository.findUserEntityByUsernameAndIsRemovedIsFalse(username).get();
        if (user == null)
            throw new UsernameNotFoundException("User with username " + username + " not found.");
        return new UserDetailsImpl(user);

    }
}