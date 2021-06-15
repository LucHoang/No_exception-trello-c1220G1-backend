package com.example.no_exception_trello_c1220g1.controller;

import com.example.no_exception_trello_c1220g1.model.Entity.BoardTagAppUser;
import com.example.no_exception_trello_c1220g1.model.Entity.ListTrello;
import com.example.no_exception_trello_c1220g1.model.Entity.User;
import com.example.no_exception_trello_c1220g1.service.board.boardTagAppUser.IBoardTagAppUserService;
import com.example.no_exception_trello_c1220g1.service.listService.IListService;
import com.example.no_exception_trello_c1220g1.service.token.JwtService;
import com.example.no_exception_trello_c1220g1.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("")
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
//    @GetMapping("/board/{id}")
//    public ResponseEntity<?> findListByBoardId(@PathVariable Long id){
//        return new ResponseEntity<>(listService.findListByBoardId(id),HttpStatus.OK);
//    }
    @PostMapping("createList")
    public ResponseEntity<?> createList(@Valid @RequestBody ListTrello list, HttpServletRequest request, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String authHeader = request.getHeader("Authorization");
        String userName = jwtService.getUserNameFromJwtToken(authHeader.replace("Bearer ", ""));
        User user = userService.findByUsername(userName);
        BoardTagAppUser boardTagUserCheck = boardTagAppUserService.findByBoardIdAndUserId(list.getBoard().getId(), user.getId());

        if (boardTagUserCheck.getRoleUser().equals("ROLE_VIEW")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        int position = listService.findListByBoardId(list.getBoard().getId()).size();
        list.setPosition(position);
        listService.save(list);
        return new  ResponseEntity<>("Create obj List success", HttpStatus.OK);
    }
//    @PutMapping("editPositionList")
//    public ResponseEntity<?> changePositionList(@RequestBody ArrayList<List> lists){
//        listService.editPositionList(lists);
//        return new ResponseEntity(new ResultResponse("Change position ok"),HttpStatus.OK);
//    }
//    @GetMapping("findList/{id}")
//    ResponseEntity<List> findListById(@PathVariable Long id) {
//        return new ResponseEntity<>(listService.findById(id), HttpStatus.OK);
//    }
//    @PutMapping("editTitleList/{id}")
//    public ResponseEntity<?> changeTitleList(@RequestBody List list, @PathVariable Long id){
//        return new ResponseEntity<>(listService.editTitleList(list, id),HttpStatus.OK);
//    }
}
