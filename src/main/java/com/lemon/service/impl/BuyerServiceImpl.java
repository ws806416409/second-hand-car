package com.lemon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.entity.Buyer;
import com.lemon.mapper.BuyerMapper;
import com.lemon.service.IBuyerService;
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
public class BuyerServiceImpl extends ServiceImpl<BuyerMapper, Buyer> implements IBuyerService {

}
