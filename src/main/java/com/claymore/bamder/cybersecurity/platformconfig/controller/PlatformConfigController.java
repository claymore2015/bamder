package com.claymore.bamder.cybersecurity.platformconfig.controller;

import com.claymore.bamder.common.domain.BaseResponse;
import com.claymore.bamder.common.domain.PageResult;
import com.claymore.bamder.cybersecurity.platformconfig.entity.PlatformConfigEntity;
import com.claymore.bamder.cybersecurity.platformconfig.repository.PlatformConfigRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author li.zhuo
 * @create 2018/11/18 11:20 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping("/platform_config")
public class PlatformConfigController {

    @Autowired
    private PlatformConfigRepository platformConfigRepository;

    @GetMapping("/{id}")
    @ApiOperation(value = "query platform config with id", consumes = "GET", response = PlatformConfigEntity.class)
    public BaseResponse<PlatformConfigEntity> findById(@PathVariable("id") Long id) {
        return new BaseResponse<>(platformConfigRepository.findById(id).get());
    }

    @PostMapping
    public BaseResponse create(@RequestBody PlatformConfigEntity platformConfigEntity) {
        platformConfigRepository.save(platformConfigEntity);
        return BaseResponse.instance();
    }

    @PutMapping
    public BaseResponse update(@RequestBody PlatformConfigEntity updatedPlatformConfigEntity) {
        platformConfigRepository.save(updatedPlatformConfigEntity);
        return BaseResponse.instance();
    }

    @GetMapping("/{pageSize}/{pageNo}")
    public BaseResponse<PageResult<PlatformConfigEntity>> page(@PathVariable("pageSize") int pageSize, @PathVariable("pageNo") int pageNo) {
        Page<PlatformConfigEntity> page = platformConfigRepository.findAll(PageRequest.of(pageNo - 1, pageSize));
        PageResult<PlatformConfigEntity> result = new PageResult<PlatformConfigEntity>();
        result.setContent(page.getContent());
        result.setPageNo(pageNo);
        result.setPageSize(pageSize);
        result.setTotalRecords(page.getTotalElements());
        return new BaseResponse(result);
    }
}
