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
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    private Integer uid;

    private String cname;

    private Double price;

    private LocalDateTime time;

    private Double milage;

    private String img;

    private String describe;


}
