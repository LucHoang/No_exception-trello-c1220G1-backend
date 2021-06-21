package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.dto.ListResponse;
import com.example.no_exception_trello_c1220g1.model.dto.UserPrinciple;
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
import org.springframework.security.core.userdetails.UserDetails;
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
        return new ResponseEntity<>(listService.findAllListByBoardId(id),HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<?> createList(@Valid @RequestBody ListTrello list, HttpServletRequest request, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!listService.checkRole(userPrinciple, list)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        int position = listService.findListByBoardId(list.getBoard().getId()).size();
        list.setPosition(position);
        ListTrello response = listService.save(list);
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("editPositionList")
    public ResponseEntity<?> changePositionList(@RequestBody ArrayList<ListTrello> lists){
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!listService.checkRole(userPrinciple, lists.get(0))) {
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
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!listService.checkRole(userPrinciple, list)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(listService.editTitleList(list, id),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<List<ListResponse>> getAllCardByBoardId(@PathVariable Long id){
        return new ResponseEntity<>(listService.findAllListByBoardId(id),HttpStatus.OK);
    }
}
