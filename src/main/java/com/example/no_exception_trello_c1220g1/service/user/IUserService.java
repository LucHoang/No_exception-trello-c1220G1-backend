package com.example.no_exception_trello_c1220g1.service.user;

import com.example.no_exception_trello_c1220g1.model.Entity.TagUser_Board;
import com.example.no_exception_trello_c1220g1.model.Entity.User;
import com.example.no_exception_trello_c1220g1.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User updateUser(User user);

    User findByUsername(String username);

    String checkUserNameEmail(String username, String email);

    List<User> findUserAndTagUserByBoard(Long board_id);
}
