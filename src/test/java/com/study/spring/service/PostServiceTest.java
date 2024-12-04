package com.study.spring.service;

import com.study.spring.domain.Post;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class PostServiceTest {

//    @LocalServerPort
//    int port;
//
//    @Autowired
//    TestRestTemplate restTemplate;
//
//    @Autowired
//    PostService postService;
//
//    @Autowired
//    EntityManager em;
//
//    @Test
//    public void 사용자_저장() throws Exception{
//        //given
//        String title = "테스트 제목";
//        String content = "테스트 본문";
//        String author = "seonbin";
//        String password = "1234";
//
//        Post post = new Post();
//        post.setTitle(title);
//        post.setContent(content);
//        post.setAuthor(author);
//        post.setPassword(password);
//
//        String url = "http://localhost:"+port+"/post";
//
//        //when
//        ResponseEntity<PostDTO> responseEntity = restTemplate.postForEntity(url, post, PostDTO.class);
//        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(responseEntity.getBody()).isInstanceOf(PostDTO.class);
//        assertThat(responseEntity.getBody().getTitle()).isEqualTo(title);
//        assertThat(responseEntity.getBody().getContent()).isEqualTo(content);
//        assertThat(responseEntity.getBody().getAuthor()).isEqualTo(author);
//    }
//
//    @Test
//    public void 게시글_전체조회() throws Exception {
//        Post post1 = new Post();
//        post1.setTitle("title1");
//        post1.setContent("content1");
//        post1.setAuthor("seonbin1");
//        post1.setPassword("12341");
//
//        postService.savePost(post1);
//
//        Post post2 = new Post();
//        post2.setTitle("title2");
//        post2.setContent("content2");
//        post2.setAuthor("seonbin2");
//        post2.setPassword("12342");
//
//        postService.savePost(post2);
//
//        List<Post> posts = postService.findPosts();
//        assertThat(posts.size()).isEqualTo(2);
//
//        // 각 게시글의 내용 확인
//        Post retrievedPost1 = posts.get(0);
//        assertThat(retrievedPost1.getTitle()).isEqualTo("title1");
//        assertThat(retrievedPost1.getContent()).isEqualTo("content1");
//        assertThat(retrievedPost1.getAuthor()).isEqualTo("seonbin1");
//
//        Post retrievedPost2 = posts.get(1);
//        assertThat(retrievedPost2.getTitle()).isEqualTo("title2");
//        assertThat(retrievedPost2.getContent()).isEqualTo("content2");
//        assertThat(retrievedPost2.getAuthor()).isEqualTo("seonbin2");
//    }
//
//    @Test
//    public void 게시글_조회() throws Exception {
//        Post post = new Post();
//        post.setTitle("title1");
//        post.setContent("content1");
//        post.setAuthor("seonbin1");
//        post.setPassword("12341");
//
//        Post savePost = postService.savePost(post);
//        Post findPost = postService.findPost(savePost.getId());
//
//        //when
//        assertThat(findPost.getTitle()).isEqualTo("title1");
//        assertThat(findPost.getContent()).isEqualTo("content1");
//        assertThat(findPost.getAuthor()).isEqualTo("seonbin1");
//    }
//
//    @Test
//    public void 게시글_수정() throws Exception {
//        Post post = new Post();
//        post.setTitle("title1");
//        post.setContent("content1");
//        post.setAuthor("seonbin1");
//        post.setPassword("12341");
//
//        Post savePost = postService.savePost(post);
//        Long updateId = savePost.getId();
//
//        String updateTitle = "updateTitle";
//        String updateContent = "updateContent";
//
//        String url = "http://localhost:"+port+"/post/" + updateId + "/edit";;
//
//        Post updatePost = new Post();
//        updatePost.setTitle(updateTitle);
//        updatePost.setContent(updateContent);
//        updatePost.setPassword("12341");
//
//        HttpEntity<Post> requestEntity = new HttpEntity<>(updatePost);
//
//        //when
//        ResponseEntity<PostDTO> responseEntity = restTemplate.
//                exchange(url, HttpMethod.PUT, requestEntity, PostDTO.class);
//
//        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
}