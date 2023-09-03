package com.ssafy.tripApp.board.hotplace.dao;

import java.sql.SQLException;
import com.ssafy.tripApp.board.hotplace.dto.HeartCommentDto;

public interface HeartCommentDao {
	// 하트를 눌렀었는지 체크
	int checkHeart(HeartCommentDto heartcommentDto) throws SQLException;
	//좋아요 누르기
	void registHeart(HeartCommentDto heartcommentDto)throws SQLException;
	//좋아요 취소
	void deleteHeart(HeartCommentDto heartcommentDto) throws SQLException;
}
