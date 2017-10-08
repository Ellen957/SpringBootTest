package com.nowcoder.dao;

import com.nowcoder.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDAO {
    String TABLE_NAME = " user ";
    String INSERT_FIELDS = " name,password,salt,headUrl ";
    String SELECT_FIELDS = " id " + INSERT_FIELDS;

    @Insert({"insert into", TABLE_NAME , "(", INSERT_FIELDS , ") values(#{name},#{password},#{salt},#{headUrl})"})
    int addUser(User user);

    @Select({"select * from " ,TABLE_NAME ," where id = #{id}"})
    User selectById(@Param("id") int id);

    @Select({"select * from " ,TABLE_NAME ," where name = #{name}"})
    User selectUserByName(@Param("name") String name);

    @Update({"update ",TABLE_NAME," set password = #{password} where id = #{id}"})
    void updatePassword(User user);

    @Delete({"delete from ",TABLE_NAME , "where id = #{id}"})
    void deleteById(int id);

    //查找用户名是否存在
    @Select({"select name from ",TABLE_NAME,"where name = #{name}"})
    String selectByName(@Param("name") String name);
}
