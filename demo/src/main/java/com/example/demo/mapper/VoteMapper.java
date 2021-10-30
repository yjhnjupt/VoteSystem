package com.example.demo.mapper;

import com.example.demo.entity.Vote;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteMapper {
    @Select("select * from voter_vote where username=#{name}")
    List<Vote> getVote(@Param("name") String name);
    @Select("select id from voter_vote")
    Integer[] getMaxId();
    @Select("select * from voter_vote where username=#{name} and id=#{id}")
    Vote checkVote(@Param("name") String name,@Param("id") Integer id);
    @Update("update voter_vote set selection = #{selection} where username=#{name} and id= #{id}")
    int updateVote(@Param("selection") String selection, @Param("name") String name,@Param("id") Integer id);
    @Insert("insert into voter_vote" +
            " (`username`,`title`, `describe`, `selection`) VALUES" +
            " (#{username},#{title}, #{describe}, #{selection})")
    void insertVote(@Param("username") String username,@Param("title") String title, @Param("describe") String describe, @Param("selection") String selection);
    @Delete("delete from voter_vote where username=#{username} and id=#{id}")
    void deleteVote(@Param("username") String username,@Param("id") Integer id);
}
