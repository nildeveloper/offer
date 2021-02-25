package com.lhl.boot.parallel;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ParallelInvokerConfig {

    /**
     * 在Sentinel#resource使用，长度小于等于64
     */
    @Builder.Default
    private String name = "DefaultParallelInvokerName";
    /**
     * 默认熔断时间(毫秒)
     */
    @Builder.Default
    private int timeout = 400;

    @Builder.Default
    private Type type = Type.timeout;

    public static enum Type {
        await,
        timeout,
        /**
         * 手动控制并行任务的返回结果
         */
        manual


    }


}
