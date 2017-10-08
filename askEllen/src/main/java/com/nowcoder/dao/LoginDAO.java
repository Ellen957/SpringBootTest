package com.nowcoder.dao;

import com.nowcoder.model.LoginTicket;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LoginDAO {
    String TABLE_NAME ="loginTicket ";
    String INSERT_FIELDS = "userId,ticket,expired,status ";
    String SELECT_FIELDS = "id, "+INSERT_FIELDS;

    //添加loginticket
    @Insert({"insert into ",TABLE_NAME,"( ",INSERT_FIELDS,") ","values (#{userId},#{ticket},#{expired},#{status}) "})
    void addTicket(LoginTicket loginTicket);

    //筛选
    @Select({"select ",SELECT_FIELDS,"from ",TABLE_NAME,"where ticket=#{ticket}"})
    LoginTicket selectByTicket(String ticket);

    //更新状态
    @Update({"update ",TABLE_NAME," set status = #{status} where ticket = #{ticket} "})
    void updateStatus(@Param("status") int status,@Param("ticket") String ticket);
}
