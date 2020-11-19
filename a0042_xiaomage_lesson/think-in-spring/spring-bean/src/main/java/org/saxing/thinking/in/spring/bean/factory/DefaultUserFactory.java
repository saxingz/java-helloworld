package org.saxing.thinking.in.spring.bean.factory;

import javax.annotation.PostConstruct;

/**
 * DefaultUserFactory
 *
 * @author saxing 2020/11/19 22:54
 */
public class DefaultUserFactory implements UserFactory {

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct :  UserFactory 初始化中。。。");
    }

    public void initUserFactory() {
        System.out.println("自定义初始化方法： initUserFactory: UserFactory初始化中。。。");
    }

}
