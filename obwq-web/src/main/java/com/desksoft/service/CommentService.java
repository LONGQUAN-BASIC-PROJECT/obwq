package com.desksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desksoft.dao.CommentDao;
import com.desksoft.entity.Comment;

/**
 * @author forever
 *
 */
@Service(value="commentService")
public class CommentService /*extends BaseServvice*/ {

	@Autowired
	private CommentDao  commentDao;
	
	public void insertComment(Comment comment){
		commentDao.insertComment(comment);
	}
	
	
}
