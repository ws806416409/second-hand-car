package com.lemon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2021-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("carInfo")
public class Carinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cid;

    private Integer sid;

    private String cname;

    private Double price;

    private LocalDateTime time;

    private Double milage;

    private String img;


}
