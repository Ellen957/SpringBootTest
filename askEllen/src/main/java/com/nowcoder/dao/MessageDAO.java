package com.nowcoder.dao;

import com.nowcoder.model.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageDAO {
    String TABLE_NAME ="message ";
    String INSERT_FIELDS = "fromId,toId,content,conversationId,createdDate ";
    String SELECT_FIELDS = "id, "+INSERT_FIELDS;

    @Insert({"insert into ",TABLE_NAME,"( ",INSERT_FIELDS,") ","values(#{fromId},#{toId},#{content},#{conversationId},#{createdDate} "})
    public void addMessage(Message message);

    @Select({"select *,count(id) as count from (select * from message ORDER BY createdDate desc)tt GROUP BY conversationId "})
    public List<Message> selectMessage();

    @Select({"select ",SELECT_FIELDS,"from ",TABLE_NAME,"where conversationId = #{conversationId} order by createdDate desc"})
    public List<Message> selectMessageByCId(@Param("conversationId") String conversationId);

    @Delete({"delete from ",TABLE_NAME,"where id = #{id}"})
    public void deleteLetter(@Param("id") int id);
}
