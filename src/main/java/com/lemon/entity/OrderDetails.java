package com.lemon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author qaq
 * @since 2021-12-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "oid", type = IdType.AUTO)
    private Integer oid;

    private Integer bid;

    private Integer sid;

    private String cname;

    private Double cost;

    private LocalDateTime time;


}
