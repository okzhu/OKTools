package com.github.okzhu.toolkit.base.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Result<T> implements Serializable {

    private boolean success = true;

    private Long errCode;

    private String message;

    private T data;

}
