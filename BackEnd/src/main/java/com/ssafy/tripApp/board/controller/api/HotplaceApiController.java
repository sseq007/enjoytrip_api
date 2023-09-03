package com.ssafy.tripApp.board.controller.api;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.tripApp.board.ResponseDto;
import com.ssafy.tripApp.board.hotplace.dto.HeartCommentDto;
import com.ssafy.tripApp.board.hotplace.dto.HotplaceDto;
import com.ssafy.tripApp.board.hotplace.dto.HotplaceReplyDto;
import com.ssafy.tripApp.board.hotplace.service.HeartCommentService;
import com.ssafy.tripApp.board.hotplace.service.HotplaceReplyService;
import com.ssafy.tripApp.board.hotplace.service.HotplaceService;
import com.ssafy.tripApp.member.dto.MemberDto;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/hotplace")
@CrossOrigin("*")
public class HotplaceApiController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Value("${file.path}")
	private String uploadPath;
	
	@Autowired
	private HotplaceService hotplaceService;
	@Autowired
	private HeartCommentService heartService;
	@Autowired
	private HotplaceReplyService replyService;
	
	@GetMapping("/list")
	public ResponseEntity<?>list() throws Exception{
		List<HotplaceDto> hotplaceList = hotplaceService.listHotple();
		return new ResponseEntity(hotplaceList, HttpStatus.OK);
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@GetMapping("/view/{articleNo}")
	public ResponseEntity<?> view(@PathVariable("articleNo") @ApiParam(value = "얻어올 글의 번호", required = true) int articleNo){
		try {
			HotplaceDto viewHotple = hotplaceService.viewHotple(articleNo);
			if(viewHotple != null) {
				return new ResponseEntity<HotplaceDto>(viewHotple, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/write")
	public ResponseEntity<?> write(HotplaceDto hotplaceDto, MultipartFile file){
		System.out.println(hotplaceDto);
		try {
			if(file != null) {
				String realPath = uploadPath;
				String saveFolder = realPath;
				String saveFileName;
				File folder = new File(saveFolder);
				if (!folder.exists()) folder.mkdirs();
				String origin = file.getOriginalFilename();
				if(!origin.isEmpty()) {
					saveFileName = UUID.randomUUID().toString()
							+ origin;
					file.transferTo(new File(folder, saveFileName));
					hotplaceDto.setImageName(saveFileName);
				}
				hotplaceDto.setImageUrl(saveFolder);
				
			}
			System.out.println(hotplaceDto);
			hotplaceService.writeHotple(hotplaceDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(HotplaceDto hotplaceDto, @RequestParam(value="file", required=false) MultipartFile file){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			HotplaceDto updateHotplace = hotplaceService.viewHotple(hotplaceDto.getArticleNo());
			if(file != null) {
				String realPath = uploadPath;
				String saveFolder = realPath;
				String saveFileName;
				File folder = new File(saveFolder);
				if (!folder.exists()) folder.mkdirs();
				String origin = file.getOriginalFilename();
				if(!origin.isEmpty()) {
					saveFileName = UUID.randomUUID().toString()
							+ origin;
					file.transferTo(new File(folder, saveFileName));
					hotplaceDto.setImageName(saveFileName);
				}
				hotplaceDto.setImageUrl(saveFolder);
				
			}else {
				hotplaceDto.setImageName(updateHotplace.getImageName());
				hotplaceDto.setImageUrl(updateHotplace.getImageUrl());
			}
			hotplaceService.updateHotple(hotplaceDto);
			
			
			List<HotplaceDto> hotplaceList = hotplaceService.listHotple();
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/delete/{articleNo}")
	public ResponseEntity<?> delete(@PathVariable("articleNo") int articleNo){
		try {
			hotplaceService.deleteHotple(articleNo);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/like/get")
	public ResponseEntity<?> check(HeartCommentDto heartCommentDto){
		try {
			int data = heartService.checkHeart(heartCommentDto);
			return new ResponseEntity<Integer>(data, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/like")
	public ResponseEntity<?> like(HeartCommentDto heartCommentDto){
		System.out.println(heartCommentDto);
		try {
			heartService.registHeart(heartCommentDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/dislike")
	public ResponseEntity<?> dislike(HeartCommentDto heartCommentDto){
		System.out.println(heartCommentDto);
		try {
			heartService.deleteHeart(heartCommentDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/reply/{articleNo}")
	public ResponseEntity<?> listReply(@PathVariable("articleNo") int articleNo) throws SQLException{
		List<HotplaceReplyDto> list = replyService.listReply(articleNo);
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PostMapping("/write/reply")
	public ResponseEntity<?> registReply(@RequestBody HotplaceReplyDto hotplaceReplyDto){
		System.out.println(hotplaceReplyDto);
		try {
			replyService.writeReply(hotplaceReplyDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/modify/reply")
	public ResponseEntity<?> updateReply(@RequestBody HotplaceReplyDto hotplaceReplyDto){
		System.out.println(hotplaceReplyDto);
		try {
			replyService.updateReply(hotplaceReplyDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/delete/reply")
	public ResponseEntity<?> deleteReply(@RequestBody HotplaceReplyDto hotplaceReplyDto){
		System.out.println(hotplaceReplyDto);
		try {
			replyService.deleteReply(hotplaceReplyDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/max/{articleNo}/{replyNo}")
	public ResponseEntity<?> listReply(@PathVariable("articleNo") int articleNo, @PathVariable("replyNo") int replyNo) throws SQLException{
		int max = replyService.getMax(articleNo, replyNo);
		return new ResponseEntity(max, HttpStatus.OK);
	}
	
	
}
