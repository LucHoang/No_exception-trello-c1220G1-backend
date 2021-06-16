package com.example.no_exception_trello_c1220g1.service.user;

import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByEmail(String email);

    User updateUser(User user);

    User findByUsername(String username);

    String checkUserNameEmail(String username, String email);

    List<User> findUserAndTagUserByBoard(Long board_id);

    List<User> findAllUserByGroup(Long group_id);

}
