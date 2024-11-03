package com.prometheus.money.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.prometheus.money.entity.MessageBoard;
import com.prometheus.money.mapper.MessageBoardMapper;
import com.prometheus.money.res.Res;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Heisenberg
 * @since 2024-11-02
 */
@RestController
@RequestMapping("/messageBoard")
public class MessageBoardController {
	@Autowired
	private MessageBoardMapper messageBoardMapper;

	@PostMapping("/save")
	public Res<String> save(@RequestBody MessageBoard messageBoard) {
		MessageBoard entity = new MessageBoard();
		BeanUtils.copyProperties(messageBoard, entity);

		messageBoardMapper.insertOrUpdate(entity);
		return Res.success("保存成功");
	}

	@GetMapping("/list")
	public Res<Page<MessageBoard>> list(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

		QueryWrapper<MessageBoard> queryWrapper = new QueryWrapper<>();
		Page<MessageBoard> page = new Page<>(currentPage, pageSize);
		Page<MessageBoard> userPage = messageBoardMapper.selectPage(page, queryWrapper);
		List<MessageBoard> resultList = userPage.getRecords();
		return Res.success(userPage);
	}
	
	
	@GetMapping("/out/list")
	public Res<Page<MessageBoard>> outlist(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

		QueryWrapper<MessageBoard> queryWrapper = new QueryWrapper<>();
		Page<MessageBoard> page = new Page<>(currentPage, pageSize);
		Page<MessageBoard> userPage = messageBoardMapper.selectPage(page, queryWrapper);
		List<MessageBoard> resultList = userPage.getRecords();
		return Res.success(userPage);
	}

	@PostMapping("/reply")
	public Res<String> reply(@RequestBody MessageBoard messageBoard) {
		QueryWrapper<MessageBoard> queryWrapper = new QueryWrapper<>();
		MessageBoard  messageBoardU = messageBoardMapper.selectById(messageBoard.getId());
		messageBoardU.setReplyContent(messageBoard.getReplyContent());
		messageBoardU.setIsReplied(true);
		messageBoardMapper.updateById(messageBoardU);
		return Res.success("回复成功");
	}
}
