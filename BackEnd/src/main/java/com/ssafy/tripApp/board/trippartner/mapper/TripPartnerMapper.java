package com.ssafy.tripApp.board.trippartner.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tripApp.board.trippartner.dto.KeywordOneDto;
import com.ssafy.tripApp.board.trippartner.dto.KeywordTwoDto;
import com.ssafy.tripApp.board.trippartner.dto.NoteDto;
import com.ssafy.tripApp.board.trippartner.dto.TripPartnerDto;
import com.ssafy.tripApp.board.trippartner.dto.UserDto;

@Mapper
public interface TripPartnerMapper {

	// 동행찾기쓰기
	void writePartner(TripPartnerDto trippartnerDto) throws SQLException;

	// 동행찾기수정
	void updatePartner(TripPartnerDto trippartnerDto) throws SQLException;
	void updateKeywordOne(KeywordOneDto keywordoneDto) throws SQLException;
	void updateKeywordTwo(KeywordTwoDto keywordoneDto) throws SQLException;

	// 동행찾기삭제
	void deletePartner(int aticleNo) throws SQLException;

	// 조회수업데이트
	void updateHit(int articleNo) throws SQLException;

	// 동행찾기리스트
	List<TripPartnerDto> listPartner(Map<String, String> param) throws SQLException;

	// 동행찾기가져오기
	TripPartnerDto viewPartner(int articleNo) throws SQLException;

	UserDto viewUser(String userId) throws SQLException;
	// 동행키워드쓰기
	void writeKeywordOne(KeywordOneDto keywordoneDto) throws SQLException;

	void writeKeywordTwo(KeywordTwoDto keywordtwoDto) throws SQLException;

	// 최신 글번호 가져오기
	int getarticleNo() throws SQLException;

	// 동행키워드가져오기
	List<TripPartnerDto> listKeywordOne(int articleNo) throws SQLException;

	List<TripPartnerDto> listKeywordTwo(int articleNo) throws SQLException;

	void updateisEndOn(TripPartnerDto trippartnerDto) throws SQLException;

	void updateisEndOff(TripPartnerDto trippartnerDto) throws SQLException;

//	모집순(모집중)
	List<TripPartnerDto> listPartnerOn(Map<String, String> param) throws SQLException;
//	모집순(모집종료)
	List<TripPartnerDto> listPartnerOff(Map<String, String> param) throws SQLException;
//인기순
	List<TripPartnerDto> listPartnerHit(Map<String, String> param) throws SQLException;
	
	List<TripPartnerDto> listPartnerhitOff(Map<String, String> param) throws SQLException;
	
	List<TripPartnerDto> listPartnernewOn(Map<String, String> param) throws SQLException;
	
	List<TripPartnerDto> listPartnernewOff(Map<String, String> param) throws SQLException;
	
	
}
