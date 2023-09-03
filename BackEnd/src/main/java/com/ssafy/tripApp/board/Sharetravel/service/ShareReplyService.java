package com.ssafy.tripApp.board.Sharetravel.service;

import java.util.List;

import com.ssafy.tripApp.board.Sharetravel.dto.ShareReplyDto;

public interface ShareReplyService {
	//답글 쓰기
	void writeReply(ShareReplyDto shareReplyDto) throws Exception;
	//답글 삭제
	void deleteReply(int replyNo) throws Exception;
	//답글 가져오기
	List<ShareReplyDto> listReply(int articleNo) throws Exception;
}
