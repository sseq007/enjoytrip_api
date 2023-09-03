package com.ssafy.tripApp.board.hotplace.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.tripApp.board.hotplace.dto.HotplaceReplyDto;

public interface HotplaceReplyDao {
	public List<HotplaceReplyDto> list() throws SQLException;
	public void updateReply(HotplaceReplyDto hotplaceReplyDto) throws SQLException;
	//답글 등록
	void writeReply(HotplaceReplyDto hotplaceReply) throws SQLException;
	//답글 삭제
	void deleteReply(HotplaceReplyDto hotplaceReply) throws SQLException;
}
