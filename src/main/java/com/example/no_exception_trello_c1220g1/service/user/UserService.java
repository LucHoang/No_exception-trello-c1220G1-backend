package com.example.no_exception_trello_c1220g1.service.user;

import com.example.no_exception_trello_c1220g1.model.Entity.Role;
import com.example.no_exception_trello_c1220g1.model.Entity.TagUser_Board;
import com.example.no_exception_trello_c1220g1.model.Entity.User;
import com.example.no_exception_trello_c1220g1.model.dto.UserPrinciple;
import com.example.no_exception_trello_c1220g1.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.builder()
                .id(1L)
                .roleName("ROLE_USER")
                .build());
        user.setPassWord(passwordEncoder.encode(user.getPassWord()));
        user.setAppRole(roles);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        return UserPrinciple.build(user);
    }

    @Override
    public String checkUserNameEmail(String username, String email) {
        User user1 = findByUsername(username);
        User user2 = userRepository.findByEmail(email);

        if (user1==null && user2==null) {
            return "OK";
        } else if (user1 != null && user2 == null) {
            return "nameExist";
        } else if (user1 == null) {
            return "mailExist";
        }
        return "nameEmailExist";
    }

    @Override
    public List<User> findUserAndTagUserByBoard(Long board_id) {
        return userRepository.findTagUserByBoard(board_id);
    }


}
