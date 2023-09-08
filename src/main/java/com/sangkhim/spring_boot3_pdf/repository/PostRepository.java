package com.sangkhim.spring_boot3_pdf.repository;

import com.sangkhim.spring_boot3_pdf.model.entity.Post;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  Page<Post> findByTitleContainingIgnoreCase(String title, Pageable pageable);

  @Modifying
  @Query(value = "SELECT p FROM Post p WHERE lower(p.title) LIKE lower(concat('%', ?1,'%'))")
  List<Post> selectPosts(String title);

  @Modifying
  @Query("DELETE FROM Post p WHERE p.title = :title OR p.body = :body")
  int deletePosts(@Param("title") String title, @Param("body") String body);
}
