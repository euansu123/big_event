package com.euansu.mapper;

import com.euansu.pojo.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Insert("INSERT INTO article (title, content, cover_img, category_id, create_user, state, create_time, update_time) " +
            "VALUES (#{title},#{content},#{coverImg},#{categoryId},#{createUser},#{state},#{createTime},#{updateTime})")
    void addArticle(Article article);


    List<Article> list(Integer userId, Integer categoryId, String state);


    @Update("UPDATE article SET title=#{title}, content=#{content}, cover_img=#{coverImg}, category_id=#{categoryId}, state=#{state}, update_time=#{updateTime}  WHERE id=#{id}")
    void update(Article article);

    @Delete("DELETE FROM article WHERE id=#{id}")
    void delete(Integer id);
}
