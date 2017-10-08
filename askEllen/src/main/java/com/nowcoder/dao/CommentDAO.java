package com.nowcoder.dao;

import com.nowcoder.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentDAO {
    String TABLE_NAME ="comment ";
    String INSERT_FIELDS = "content,userId,createdDate,entityId,entityType ";
    String SELECT_FIELDS = "id, "+INSERT_FIELDS;

    @Select({"select ",SELECT_FIELDS,"from ",TABLE_NAME})
    Comment selectComment();

    @Select({"select ",SELECT_FIELDS,"from ",TABLE_NAME,"where entityId=#{entityId} and entityType = #{entityType}"})
    List<Comment> selectCommByID(@Param("entityId") int entityId,@Param("entityType") int entityType);

    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELDS,")","values(#{content},#{userId},#{createdDate},#{entityId},#{entityType})"})
    void addComment(Comment comment);

    @Delete({"delete from ",TABLE_NAME,"where id=#{id}"})
    void deleteById(int id);

    @Select({"SELECT count(id) from ",TABLE_NAME," where entityType = 0 and entityId = #{entityId}"})
    int selectCommNumByEntityId(int id);

}
