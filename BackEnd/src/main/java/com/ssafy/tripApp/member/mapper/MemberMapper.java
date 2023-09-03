package com.ssafy.tripApp.member.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tripApp.member.dto.MemberDto;

@Mapper
public interface MemberMapper {

	// 회원가입
	void registerMember(MemberDto memberDto) throws SQLException;
	// 로그인
	MemberDto login(Map<String, String> map) throws SQLException;
	// 회원수정
	void updateMember(MemberDto memberDto) throws SQLException;
	// 회원삭제
	void deleteMember(String userId) throws SQLException;
	// 아이디체크
	int idCheck(String userId) throws SQLException;
	int nicknameCheck(String userNickname) throws SQLException;
	// 회원리스트
	List<MemberDto> listMember() throws SQLException;
	// 회원정보
	MemberDto viewMember(String userId) throws SQLException;
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String userId) throws SQLException;
	void deleteRefreshToken(Map<String, String> map) throws SQLException;

}
