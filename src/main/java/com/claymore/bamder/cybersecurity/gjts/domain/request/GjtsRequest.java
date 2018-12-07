package com.claymore.bamder.cybersecurity.gjts.domain.request;

import com.claymore.bamder.common.domain.CyberRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author li.zhuo
 * @create 2018/11/2 10:51 PM
 * @since 1.0.0
 */
@Setter
@Getter
public class GjtsRequest extends CyberRequest {
    private Integer days;
}
