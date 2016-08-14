package com.desksoft.dao;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.desksoft.entity.Comment;
import com.desksoft.entity.mapper.CommentMapper;

@Repository(value="commentDao")
public class CommentDao {

	@Inject
	private CommentMapper  commentMapper ;
	
	public void insertComment(Comment comment) {
		commentMapper.insert(comment);
	}

}
