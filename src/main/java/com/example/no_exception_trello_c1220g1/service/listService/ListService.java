package com.example.no_exception_trello_c1220g1.service.listService;

import com.example.no_exception_trello_c1220g1.model.dto.*;
import com.example.no_exception_trello_c1220g1.model.entity.*;
import com.example.no_exception_trello_c1220g1.repository.IListRepository;
import com.example.no_exception_trello_c1220g1.service.board.BoardService;
import com.example.no_exception_trello_c1220g1.service.board.IBoardService;
import com.example.no_exception_trello_c1220g1.service.cardService.ICardService;
import com.example.no_exception_trello_c1220g1.service.cardTagUserService.ICardTagUserService;
import com.example.no_exception_trello_c1220g1.service.commentService.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListService implements IListService{
    @Autowired
    IListRepository listRepository;
    @Autowired
    ICardService iCardService;
    @Autowired
    ICommentService commentService;
    @Autowired
    ICardTagUserService cardTagUserService;
    @Autowired
    IBoardService boardService;



    @Override
    public List<ListTrello> findAll() {
        return listRepository.findAll();
    }

    @Override
    public Optional<ListTrello> findById(Long id) {
        return listRepository.findById(id);
    }


    @Override
    public ListTrello save(ListTrello list) {
        return listRepository.save(list);
    }

    @Override
    public void delete(Long id) {
    listRepository.delete(findById(id).get());
    }


    @Override
    public void editPositionList(ArrayList<ListTrello> lists) {
            for (int i = 0; i < lists.size(); i++) {
                ListTrello update = lists.get(i);
                ListTrello list = listRepository.getById(update.getId());
                list.setPosition(update.getPosition());
                listRepository.save(list);
            }
    }

    @Override
    public java.util.List<ListTrello> findListByBoardId(Long id) {
        return listRepository.findListByBoard_IdOrderByPosition(id);
    }
    @Override
    public ListTrello editTitleList(ListTrello list, Long id) {
        list.setId(id);
        return listRepository.save(list);
    }

    @Override
    public List<ListResponse> findAllListByBoardId(Long id) {
        List<ListTrello> listTrellos = listRepository.findListByBoard_IdOrderByPosition(id);
        List<ListResponse> listResponses = new ArrayList<>();
        List<Card> cardList = new ArrayList<>();

        for (ListTrello listTrello : listTrellos
        ) {
            List<CardDto> cardDtoList = new ArrayList<>();
            cardList = iCardService.findCardsByListId(listTrello.getId());
            for (Card card : cardList
            ) {
                //add
                List<CommentResponse> comments = commentService.findAllByCardId(card.getId());
                List<UserResponse> users = cardTagUserService.findAllByCardId(card.getId());
                CardDto cardDto = new CardDto(card.getId(), card.getTitle(), card.getContent(), card.getPosition(),comments,users);
                cardDtoList.add(cardDto);
            }
            ListResponse listResponse = new ListResponse(listTrello.getId(), listTrello.getTitle(), listTrello.getPosition(), cardDtoList);
            listResponses.add(listResponse);

        }
        return listResponses;
    }

    @Override
    public Optional<ListTrello> findListByCardId(long id) {
        return listRepository.findListByCardId(id);
    }

    @Override
    public boolean checkRole(UserPrinciple userPrinciple, ListTrello listTrello) {
        BoardTagAppUser boardTagUserCheck = (BoardTagAppUser) userPrinciple.getAllRole().get(listTrello.getBoard().getId()+"btu");
        GroupTagUser groupTagUserCheck;
        if (listTrello.getBoard().getGroupTrello() == null) {
            groupTagUserCheck = null;
        } else {
            groupTagUserCheck = (GroupTagUser) userPrinciple.getAllRole().get(listTrello.getBoard().getGroupTrello().getId()+"gtu");
        }

        if (listTrello.getBoard().getGroupTrello() == null || listTrello.getBoard().getType().equalsIgnoreCase("TYPE_PRIVATE")) {
            if (boardTagUserCheck == null || (!boardTagUserCheck.getRoleUser().equals("ROLE_ADMIN") && !boardTagUserCheck.getRoleUser().equals("ROLE_EDIT"))) {
                return false;
            }
        } else {
            if (boardTagUserCheck == null) {
                if (groupTagUserCheck == null || (!groupTagUserCheck.getRoleUser().equals("ROLE_ADMIN") && !groupTagUserCheck.getRoleUser().equals("ROLE_EDIT"))) {
                    return false;
                }
            } else {
                if (!boardTagUserCheck.getRoleUser().equals("ROLE_ADMIN") && !boardTagUserCheck.getRoleUser().equals("ROLE_EDIT")) {
                    return false;
                }
            }
        }
        return true;
    }
}

