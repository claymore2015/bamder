package com.claymore.bamder.cybersecurity.wgxm.domain.response;

import com.claymore.bamder.cybersecurity.gj.domain.event.response.EventPoint;
import com.claymore.bamder.cybersecurity.gj.domain.event.response.PointTransfer;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author li.zhuo
 * @create 2018/12/2 3:23 PM
 * @since 1.0.0
 */
@Data
@ApiModel
public class WgxwEventResponse {
    private List<EventPoint> points;
    private List<PointTransfer> pointTransfers;
}
