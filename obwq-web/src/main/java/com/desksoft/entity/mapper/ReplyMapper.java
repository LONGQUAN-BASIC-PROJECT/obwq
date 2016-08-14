package com.desksoft.entity.mapper;

import com.desksoft.dao.SqlMapper;
import com.desksoft.entity.Reply;

/**
 * 回复
 * @author forever
 *
 */
public interface ReplyMapper  extends SqlMapper {

	public void insertReply(Reply reply);
}
