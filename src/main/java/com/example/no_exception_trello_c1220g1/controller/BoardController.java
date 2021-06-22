package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.dto.BoardDto;
import com.example.no_exception_trello_c1220g1.model.dto.UserPrinciple;
import com.example.no_exception_trello_c1220g1.model.entity.Board;
import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.service.board.IBoardService;
import com.example.no_exception_trello_c1220g1.model.entity.BoardTagAppUser;
import com.example.no_exception_trello_c1220g1.model.entity.GroupTagUser;
import com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser.IBoardTagAppUserService;
import com.example.no_exception_trello_c1220g1.service.group.groupTagUser.IGroupTagUserService;
import com.example.no_exception_trello_c1220g1.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private IBoardService boardService;
    @Autowired
    private IGroupTagUserService groupTagUserService;
    @Autowired
    private IBoardTagAppUserService boardTagAppUserService;
    @Autowired
    private UserService userService;



    @GetMapping("")
    public ResponseEntity<List<Board>> showAll() {
        return new ResponseEntity<>(boardService.findAll(), HttpStatus.OK);
    }
    @GetMapping("list-board-in-group/{id}")
    public ResponseEntity<Iterable<Board>> showListBoardInGroup(@PathVariable Long id){
        return new ResponseEntity<>(boardService.findBoardByGroupId(id),HttpStatus.OK);
    }
//
    @PostMapping()
    public ResponseEntity<Board> create(@Valid @RequestBody Board board, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (board.getGroupTrello() != null) {
            GroupTagUser groupTagUserCheck = groupTagUserService.findByGroupIdAndUserId(board.getGroupTrello().getId(), board.getUser().getId());
            if (groupTagUserCheck.getRoleUser().equals("ROLE_VIEW")) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);

        board.setUser(user);
        Board response = boardService.save(board);
        BoardTagAppUser boardTagAppUser = BoardTagAppUser.builder()
                .appUser(user)
                .board(response)
                .roleUser("ROLE_ADMIN")
                .build();

        boardTagAppUserService.save(boardTagAppUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> findBoardById(@PathVariable Long id) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);

        Optional<Board> board = boardService.findById(id);
        if (!board.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (board.get().getType().equalsIgnoreCase("TYPE_PRIVATE")) {
            if (board.get().getGroupTrello() == null) {
                if (!board.get().getUser().getUserName().equalsIgnoreCase(userName)) {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            } else {
                GroupTagUser groupTagUser = groupTagUserService.findByGroupIdAndUserId(board.get().getGroupTrello().getId(), user.getId());
                if (groupTagUser == null) {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            }
        }
        return new ResponseEntity<>(board.get(), HttpStatus.OK);
    }

    @GetMapping("showAllBoardPrivate")
    public ResponseEntity<List<Board>> showAllBoardPrivate() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);

        return new ResponseEntity<>(boardService.findBoardByType(user.getId(), "TYPE_PRIVATE"), HttpStatus.OK);
    }

    @GetMapping("showAllBoardGroup")
    public ResponseEntity<List<Board>> showAllBoardGroup() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);

        List<BoardTagAppUser> boardTagAppUsers = boardTagAppUserService.findBoardByUserIdAndBoardType(user.getId(), "TYPE_GROUP");
        List<Board> boards = new ArrayList<>();
        for (BoardTagAppUser boardTagAppUser: boardTagAppUsers) {
            boards.add(boardTagAppUser.getBoard());
        }

        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @GetMapping("showAllBoardPublic")
    public ResponseEntity<List<Board>> showAllBoardPublic() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);

        List<BoardTagAppUser> boardTagAppUsers = boardTagAppUserService.findBoardByUserIdAndBoardType(user.getId(), "TYPE_PUBLIC");
        List<Board> boards = new ArrayList<>();
        for (BoardTagAppUser boardTagAppUser: boardTagAppUsers) {
            boards.add(boardTagAppUser.getBoard());
        }

        return new ResponseEntity<>(boards, HttpStatus.OK);
    }
    @GetMapping("showAllBoardCreateByUserName")
    public ResponseEntity<List<BoardDto>> showAllBoardCreatedByUserName(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(boardService.findBoardByUsername(userName),HttpStatus.OK);
    }
}
