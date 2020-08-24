package com.lhl.boot.spider.pipeline;

import com.alibaba.fastjson.JSON;
import com.lhl.boot.spider.entity.AiQiYiListEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-08-24
 * @time 11:19
 * @describe:
 */
@Slf4j
@Component
public class AiQiYiListPipeline implements Pipeline {


    @Override
    public void process(ResultItems resultItems, Task task) {
        List<String> numberList = resultItems.get("numberList");
        List<String> urlList = resultItems.get("urlList");
        List<String> titleList = resultItems.get("titleList");
        List<AiQiYiListEntity> ret = Lists.newArrayList();
        for (int i = 0; i < numberList.size(); i++) {
            AiQiYiListEntity entity = AiQiYiListEntity.builder()
                    .number(numberList.get(i))
                    .url(urlList.get(i))
                    .title(titleList.get(i))
                    .build();
            ret.add(entity);
        }
        log.info(JSON.toJSONString(ret));
    }
}
