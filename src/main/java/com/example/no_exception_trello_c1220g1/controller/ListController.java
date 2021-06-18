package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.dto.ListResponse;
import com.example.no_exception_trello_c1220g1.model.entity.BoardTagAppUser;
import com.example.no_exception_trello_c1220g1.model.entity.GroupTagUser;
import com.example.no_exception_trello_c1220g1.model.entity.ListTrello;
import com.example.no_exception_trello_c1220g1.model.entity.User;
import com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser.IBoardTagAppUserService;
import com.example.no_exception_trello_c1220g1.service.group.groupTagUser.IGroupTagUserService;
import com.example.no_exception_trello_c1220g1.service.listService.IListService;
import com.example.no_exception_trello_c1220g1.service.token.JwtService;
import com.example.no_exception_trello_c1220g1.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/list")
@CrossOrigin("*")
public class ListController {
    @Autowired
    private IListService listService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private IBoardTagAppUserService boardTagAppUserService;
    @Autowired
    private IGroupTagUserService groupTagUserService;
    @GetMapping("/board/{id}")
    public ResponseEntity<?> findListByBoardId(@PathVariable Long id){
        return new ResponseEntity<>(listService.findListByBoardId(id),HttpStatus.OK);
    }


    @PostMapping("createList")
    public ResponseEntity<?> createList(@Valid @RequestBody ListTrello list, HttpServletRequest request, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);

        BoardTagAppUser boardTagUserCheck = boardTagAppUserService.findByBoardIdAndUserId(list.getBoard().getId(), user.getId());
        if (list.getBoard().getGroupTrello() == null || list.getBoard().getType().equalsIgnoreCase("TYPE_PRIVATE")) {
            if (boardTagUserCheck == null || (!boardTagUserCheck.getRoleUser().equals("ROLE_ADMIN") && !boardTagUserCheck.getRoleUser().equals("ROLE_EDIT"))) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            if (boardTagUserCheck == null) {
                GroupTagUser groupTagUserCheck = groupTagUserService.findByGroupIdAndUserId(list.getBoard().getGroupTrello().getId(), list.getBoard().getUser().getId());
                if (groupTagUserCheck == null || (!groupTagUserCheck.getRoleUser().equals("ROLE_ADMIN") && !groupTagUserCheck.getRoleUser().equals("ROLE_EDIT"))) {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            } else {
                if (!boardTagUserCheck.getRoleUser().equals("ROLE_ADMIN") && !boardTagUserCheck.getRoleUser().equals("ROLE_EDIT")) {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            }
        }

//        BoardTagAppUser boardTagUserCheck = boardTagAppUserService.findByBoardIdAndUserId(list.getBoard().getId(), user.getId());
//
//        if (!boardTagUserCheck.getRoleUser().equals("ROLE_ADMIN") && !boardTagUserCheck.getRoleUser().equals("ROLE_EDIT")) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        int position = listService.findListByBoardId(list.getBoard().getId()).size();
        list.setPosition(position);
        listService.save(list);
        return new  ResponseEntity<>("Create obj List success", HttpStatus.OK);
    }
    @PutMapping("editPositionList")
    public ResponseEntity<?> changePositionList(@RequestBody ArrayList<ListTrello> lists){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(userName);
        BoardTagAppUser boardTagUserCheck = boardTagAppUserService.findByBoardIdAndUserId(lists.get(0).getBoard().getId(), user.getId());

        if (boardTagUserCheck.getRoleUser().equals("ROLE_VIEW")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        listService.editPositionList(lists);
        return new ResponseEntity<>("Change position ok",HttpStatus.OK);
    }
//    @GetMapping("findList/{id}")
//    ResponseEntity<List> findListById(@PathVariable Long id) {
//        return new ResponseEntity<>(listService.findById(id), HttpStatus.OK);
//    }
    @PutMapping("editTitleList/{id}")
    public ResponseEntity<?> changeTitleList(@RequestBody ListTrello list, @PathVariable Long id){
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        BoardTagAppUser boardTagAppUser = boardTagAppUserService.findByBoardIdAndUserId(id,user.getId());

        if(boardTagAppUser.getRoleUser().equals("ROLE_VIEW")){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(listService.editTitleList(list, id),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<List<ListResponse>> getAllCardByBoardId(@PathVariable Long id){
        return new ResponseEntity<>(listService.findAllListByBoardId(id),HttpStatus.OK);
    }
}
