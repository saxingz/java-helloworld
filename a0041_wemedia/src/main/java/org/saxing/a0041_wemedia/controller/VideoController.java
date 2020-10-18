package org.saxing.a0041_wemedia.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.saxing.a0041_wemedia.domain.entity.VideoDO;
import org.saxing.a0041_wemedia.logic.IVideoLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * <p>
 * 原始视频表 前端控制器
 * </p>
 *
 * @author saxing
 * @since 2020-10-18
 */
@RestController
@RequestMapping("/v1/video")
@Api(tags = "原始视频接口")
@Validated
public class VideoController {

    @Autowired
    private IVideoLogic videoLogic;

    /**
     * 解析youtube json
     * @param json json
     * @return res
     */
    @ApiOperation("解析youtube json")
    @ApiImplicitParam(name = "json", value = "youtube json内容")
    @PostMapping("/parse-json")
    public List<VideoDO> parseYouTubeJson(@RequestParam String json){
        return videoLogic.parseYoutubeJson(json);
    }

    /**
     * 查看原始视频
     */
    @ApiOperation("查看原始视频")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(name = "pageNum", value = "每页条数", defaultValue = "1")
    })
    @PostMapping("/list-video")
    public Page<VideoDO> listVideos(@RequestBody VideoDO video,
                                    @RequestParam @Min(value = 1, message = "页码不得少于1") Integer page,
                                    @RequestParam @Min(value = 1, message = "每页条数不得少于1")
                                    @Max(value = 100, message = "每页条数不大于100") Integer pageNum ){
        return videoLogic.page(new Page<>(page, pageNum), new QueryWrapper<>(video));
    }

    /**
     * 修改原始视频
     * @param video video
     * @return res
     */
    @ApiOperation("修改原始视频")
    @PostMapping("/update-video")
    public Boolean updateVideo(@RequestBody VideoDO video){
        return videoLogic.updateById(video);
    }

}

