package com.github.okzhu.toolkit.base.vo;


import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@SuppressFBWarnings("USBR_UNNECESSARY_STORE_BEFORE_RETURN")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -5578427722780384201L;

    private boolean success = true;

    private Long errCode;

    private String message;

    private transient T data;

}
