package com.ssafy.tripApp.board.notice.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tripApp.board.Sharetravel.dto.ShareTravelDto;
import com.ssafy.tripApp.board.notice.dto.NoticeDto;


@Mapper
public interface NoticeMapper {

		//공지사항쓰기
		void writeNotice(NoticeDto noticeDto) throws SQLException;
		//공지사항수정
		void updateNotice(NoticeDto noticeDto) throws SQLException;
		//공지사항삭제
		void deleteNotice(int aticleNo) throws SQLException;
		//조회수업데이트
		void updateHit(int articleNo) throws SQLException;
		//공지사항리스트
		List<NoticeDto> listNotice(Map<String, Object> param) throws SQLException;
		//공지사항가져오기
		NoticeDto viewNotice(int articleNo) throws SQLException;
}
