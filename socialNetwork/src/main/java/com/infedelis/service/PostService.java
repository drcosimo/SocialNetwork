package com.infedelis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infedelis.repository.PostCrudRepository;

@Service("Post")
@Transactional
public class PostService {
	@Autowired
	private PostCrudRepository postRepo; 
}
