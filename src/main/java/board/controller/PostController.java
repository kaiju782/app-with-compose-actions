package board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import board.dto.PostDto;
import board.dto.UserDto;
import board.dto.request.PostRequest;
import board.dto.response.PostResponse;
import board.entity.constant.CategoryType;
import board.entity.constant.UserRoleType;
import board.service.PostService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostController {
	
	private final PostService postService;
	
	@GetMapping
	public String getPosts(ModelMap map) {
		
		List<PostResponse> posts = postService.getPost().stream()
														.map(PostResponse::from)
														.toList();
		map.addAttribute("posts", posts);
		
		
		
		return "posts/index";
	}
	
	@GetMapping("/form")
	public String postFormPage() {
		return "posts/form";
	}
	
	@GetMapping("/{pid}")
	public String getPostsByUid(@PathVariable Long pid, ModelMap map) {
		PostResponse post = PostResponse.from(postService.getPost(pid));
		map.addAttribute("post", post);
		return "posts/detail";
	}
	
	@PostMapping
	public String registerPosts(PostRequest postRequest) {
		// 로그인 기능 가정
		UserDto userDto = UserDto.of("admin", 
									 "admin", 
									 "admin", 
									 "admin@board.com", 
									 UserRoleType.ADMIN);
		
		// requst -> postDto
		PostDto postDto = PostDto.of(postRequest.getTitle(), 
									postRequest.getContent(), 
									CategoryType.BACKEND, 
									userDto);
		
		postService.registerPost(postDto);
		
		return "redirect:/posts";
	}
	
	@PutMapping
	public String updatePosts() {
		return "";
	}
	
	@DeleteMapping("/{uid}")
	public String deletePostsByPid() {
		return "";
	}
}