package com.example.no_exception_trello_c1220g1.service.user;

import com.example.no_exception_trello_c1220g1.model.entity.*;

import com.example.no_exception_trello_c1220g1.model.dto.UserPrinciple;
import com.example.no_exception_trello_c1220g1.repository.*;
import com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser.BoardTagAppUserService;
import com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser.IBoardTagAppUserService;
import com.example.no_exception_trello_c1220g1.service.group.groupTagUser.IGroupTagUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    IGroupTagUserRepository groupTagUserRepository;
    @Autowired
    IBoardTagAppUserRepository boardTagAppUserRepository;
    @Autowired
    IGroupRepository groupRepository;
    @Autowired
    IBoardRepository boardRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
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
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null ){
            user = userRepository.findByEmail(username);
        }

        HashMap<String, Object> allRole = new HashMap();
        List<BoardTagAppUser> boardTagUserCheck = boardTagAppUserRepository.findAllByAppUser_Id(user.getId());
        List<GroupTagUser> groupTagUserCheck = groupTagUserRepository.findAllByUserId(user.getId());
        List<GroupTrello> groupTrellos = groupRepository.findAllByUser(username);
        List<Board> boards = boardRepository.findAllByUser_Id(user.getId());

        for (BoardTagAppUser boardTagAppUser: boardTagUserCheck) {
            allRole.put(boardTagAppUser.getBoard().getId()+"btu", boardTagAppUser);
        }
        for (GroupTagUser groupTagUser: groupTagUserCheck) {
            allRole.put(groupTagUser.getGroupTrello().getId()+"gtu", groupTagUser);
        }
        for (GroupTrello groupTrello: groupTrellos) {
            allRole.put(groupTrello.getId()+"g", groupTrello);
        }
        for (Board board: boards) {
            allRole.put(board.getId()+"b", board);
        }

        return UserPrinciple.build(user, allRole);
    }

    public void updateAllRole (String username, HttpServletRequest request) {
        UserDetails userDetails = loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public String checkUserNameEmail(String username, String email) {
        User user1 = findByUsername(username);
        User user2 = userRepository.findByEmail(email);

        if (user1==null && user2==null) {
            return "OK";
        } else if (user1 != null) {
            return "nameExist";
        } else {
            return "mailExist";
        }
    }

    @Override
    public List<User> findUserAndTagUserByBoard(Long board_id) {
        return userRepository.findTagUserByBoard(board_id);
    }

    @Override
    public List<User> findAllUserByGroup(Long group_id) {
        return userRepository.findUserByGroup(group_id);
    }


}
