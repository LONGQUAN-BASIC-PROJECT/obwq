package com.desksoft.entity.mapper;

import com.desksoft.dao.SqlMapper;
import com.desksoft.entity.Comment;

public interface CommentMapper  extends SqlMapper  {

	void insert(Comment comment);
	
}