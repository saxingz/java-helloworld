package org.saxing.a0028_context_hierarchy_demo.context;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class TestBean {
    private String context;

    public void hello() {
        log.info("hello " + context);
    }
}
