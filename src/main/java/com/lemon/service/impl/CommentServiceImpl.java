package com.lemon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.entity.Comment;
import com.lemon.mapper.CommentMapper;
import com.lemon.service.ICommentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qaq
 * @since 2021-12-30
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
