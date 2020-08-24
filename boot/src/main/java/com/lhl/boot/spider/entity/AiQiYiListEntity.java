package com.lhl.boot.spider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-08-24
 * @time 11:15
 * @describe:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiQiYiListEntity {

    private String number;

    private String url;

    private String title;
}
