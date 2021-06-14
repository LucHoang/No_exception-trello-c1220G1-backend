package com.example.no_exception_trello_c1220g1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("")
@CrossOrigin("*")
public class TrelloController {
//    @Autowired
//    private IListService listService;
//    @GetMapping("/board/{id}")
//    public ResponseEntity<?> findListByBoardId(@PathVariable Long id){
//        return new ResponseEntity<>(listService.findListByBoardId(id),HttpStatus.OK);
//    }
//    @PostMapping("createList")
//    public ResponseEntity<?> createList(@RequestBody List list){
//        int position = listService.findListByBoardId(list.getBoard().getId()).size();
//        list.setPosition(position);
//        listService.save(list);
//        return new  ResponseEntity<>(new ResultResponse("Create obj List success"), HttpStatus.OK);
//    }
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
