package board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import board.dto.PostDto;
import board.dto.response.PostResponse;
import board.entity.Post;
import board.entity.User;
import board.repository.PostRepository;
import board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
	
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	
    public PostDto getPost(Long pid) {
    	return null;
    }
	
	@Transactional
    public void registerPost(PostDto postDto) {
		
		// User Entity
		User user = userRepository.getReferenceById(postDto.getUserDto().getUid());
		
		// Post Entity
		Post post = postDto.toEntity(user);
		postRepository.save(post);
		
    }
	
    public void updatePost(Long pid, PostDto postDto) {

    }
	
    public void deletePost(long pid, String uid) {


    }

	public List<PostDto> getPost() {
		return postRepository.findAll().stream()
									.map(PostDto::from)
									.toList();
	}
	
}
