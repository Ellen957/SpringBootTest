package com.nowcoder.dao;

import com.nowcoder.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface QuestionDAO {
    String TABLE_NAME = " question ";
    String INSERT_FIELDS = " title,content,userId,createdDate,commentCount ";
    String SELECT_FIELDS = " id," + INSERT_FIELDS;

    @Insert({"insert into ",TABLE_NAME, " ( ",INSERT_FIELDS," ) "," values(#{title},#{content},#{userId},#{createdDate},#{commentCount})"})
    int addQuestion(Question question);

    @Select({"select ",SELECT_FIELDS ," from " ,TABLE_NAME })
    List<Question> selectQuestion();

    @Select({"select ",SELECT_FIELDS,"from ",TABLE_NAME,"where title like #{%title%}"})
    List<Question> selectQuestionByTitle(String title);

    @Select({"select",SELECT_FIELDS,"from ",TABLE_NAME,"where id = #{id}"})
    Question selectQuesById(int id);

    @Select({"select * from ",TABLE_NAME ," where 1=1  order by createdDate DESC limit #{OFFSET},#{LIMIT}"})
    List<Question> selectQuestionLast(@Param("OFFSET") int OFFSET,@Param("LIMIT") int LIMIT);

    @Delete({"delete from ",TABLE_NAME," where id = #{id}"})
    void deleteQuestion(int id);

    @Select({"select createdDate from question where id = #{id}"})
    Date selectDateById(@Param("id") int id);

    @Update({"update ",TABLE_NAME,"set commentCount = #{commentCount} where id = #{id}"})
    void updateCommentCount(@Param("commentCount") int commentCount,
                            @Param("id") int id);
}
