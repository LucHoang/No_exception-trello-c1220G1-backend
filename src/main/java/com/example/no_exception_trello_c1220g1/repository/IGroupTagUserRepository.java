package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.Entity.GroupTagUser;
import com.example.no_exception_trello_c1220g1.model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IGroupTagUserRepository extends JpaRepository<GroupTagUser,Long> {
    Iterable<GroupTagUser> findAllByUserId(Long id);
    @Query(value = "select * from  group_tag_user join group_trello on group_tag_user.group_trello_id = group_trello.id where group_trello.type = ?1 and group_tag_user.user_id =?2",nativeQuery = true)
    Iterable<GroupTagUser> findAllByUserIdAndType(String type,Long id);
    GroupTagUser findByGroupTrello_IdAndAndUser_Id (Long groupId, Long userId);
}
