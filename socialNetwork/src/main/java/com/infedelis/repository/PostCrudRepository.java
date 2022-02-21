package com.infedelis.repository;

import org.springframework.data.repository.CrudRepository;

import com.infedelis.model.Post;

public interface PostCrudRepository extends CrudRepository<Post,Integer>{

}
