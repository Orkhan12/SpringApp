package org.iskandarov.SpringApp.security;

import org.iskandarov.SpringApp.entities.User;
import org.iskandarov.SpringApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByName(userName);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", userName));
        }

        return user;
    }

//    @Transactional
//    public void addUser(User user) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepository.saveAndFlush(user);
//    }

//    @Transactional
//    public void updateUser(User user) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//
//    }
//
//    @Transactional
//    public void removeUser(int id) {
//
//    }
//
//    @Transactional
//    public User getUserById(int id) {
//        return null; //userDao.getUserById(id);
//    }
//
//    @Transactional
//    public List<User> listUser() {
//        return null;
//    }
//
//    @Override
//    @Transactional
//    public List<Role> listRole() {
//        return null;
//    }
//
//    @Override
//    public Role getRoleById(Long id) {
//        return null;
//    }
}