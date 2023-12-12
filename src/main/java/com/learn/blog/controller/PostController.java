package com.learn.blog.controller;

import com.learn.blog.config.AppConstants;
import com.learn.blog.payloads.PostDto;
import com.learn.blog.payloads.PostResponse;
import com.learn.blog.service.FileService;
import com.learn.blog.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @PostMapping("user/{userId}/category/{categoryId}/post")
    private ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        PostDto createdPostDto = this.postService.createPost(postDto, userId, categoryId);

        return ResponseEntity.ok(createdPostDto);
    }


    @GetMapping("category/{categoryId}/posts")
    private ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> postDtos = this.postService.getPostByCategory(categoryId);

        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("user/{userId}/posts")
    private ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> postDtos = this.postService.getPostByUser(userId);

        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("posts/search/{keyword}")
    private ResponseEntity<List<PostDto>> getPostByUser(@PathVariable String keyword) {
        List<PostDto> postDtos = this.postService.searchPost(keyword);

        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/posts")
    private ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize, @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy, @RequestParam(value = "sortDir", defaultValue = "date", required = false) String sortDir) {
        PostResponse postResponse = this.postService.getAllPost(pageSize, pageNumber, sortBy, sortDir);

        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/posts/{postId}")
    private ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto postDto = this.postService.getPostById(postId);

        return ResponseEntity.ok(postDto);
    }


    @PutMapping("/updatePost/{postId}")
    private ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatedPostDto = this.postService.updatePost(postDto, postId);

        return ResponseEntity.ok(updatedPostDto);
    }

    @DeleteMapping("/deletePost/{postId}")
    private ResponseEntity<?> deletePostById(@PathVariable Integer postId) {
        this.postService.deletePost(postId);

        return ResponseEntity.ok(new HashMap<>());
    }


    @PostMapping("post/image/upload/{postId}")
    private ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws IOException {
        String fileName = this.fileService.uploadImage(path, image);
        PostDto postDto = this.postService.getPostById(postId);
        postDto.setPostImage(fileName);
        PostDto updatedPostDto = this.postService.updatePost(postDto, postId);
        return ResponseEntity.ok(updatedPostDto);
    }

    @GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName, HttpServletResponse httpServletResponse) throws FileNotFoundException, IOException {
        InputStream inputStream = this.fileService.getResource(path, imageName);
        httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream, httpServletResponse.getOutputStream());
    }


    @PostMapping("/netPosition")
    public HashMap<String, Object> netPosition(@RequestBody HashMap<String, Object> request) {

        String URI = "";
        HashMap<String, Object> stringObjectResponse = new HashMap<String, Object>();
        try {
            URI = "https://tfmomsuat.heytorus.com/RupeeBootUE/NetPosition";
            String stringNetPosition = restTemplate.postForObject(URI, request, String.class);
            JSONParser parser = new JSONParser(stringNetPosition);
            HashMap<String, Object> stringObjectNPResponse = (HashMap<String, Object>) parser.parse();
            System.out.println(stringObjectNPResponse);
//            JSONPObject jsonpObject  = new JSONPObject(stringNetPosition);

            if (stringObjectNPResponse.get("status").equals("success")) {
                stringObjectResponse.put("URL", URI);
                stringObjectResponse.put("Status", "Y");
                stringObjectResponse.put("message", stringObjectNPResponse.get("message"));
                stringObjectResponse.put("data", stringObjectNPResponse.get("data"));
            } else {
                stringObjectResponse.put("URL", URI);
                stringObjectResponse.put("Status", "N");
                stringObjectResponse.put("message", stringObjectNPResponse.get("message"));
                stringObjectResponse.put("data", stringObjectNPResponse);
            }

        } catch (Exception e) {
            stringObjectResponse.put("Status", "N");
            stringObjectResponse.put("message", e.getMessage());
            stringObjectResponse.put("URL", URI);
            stringObjectResponse.put("data", new ArrayList<>());

        }
        return stringObjectResponse;
    }


}
