package com.park.springboot.web.service.posts;

import com.park.springboot.domain.posts.Posts;
import com.park.springboot.domain.posts.PostsRepository;
import com.park.springboot.web.dto.PostsListResponseDto;
import com.park.springboot.web.dto.PostsResponseDto;
import com.park.springboot.web.dto.PostsSaveRequestDto;
import com.park.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시물 서비스
 */
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    /**
     * 게시물 저장
     * @param requestDto
     * @return
     */
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /**
     * 게시물 수
     * @param id
     * @param requestDto
     * @return
     */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    /**
     * 게시물 조회
     * @param id
     * @return
     */
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }

    /**
     * 게시물 목록 조회
     * @return
     */
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 게시물 삭제
     * @param id
     */
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRepository.delete(posts);
    }
}
