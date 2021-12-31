package com.lemon.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
public class Buyer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer bid;

    private String email;

    private String password;


}