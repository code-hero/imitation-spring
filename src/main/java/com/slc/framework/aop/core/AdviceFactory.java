package com.slc.framework.aop.core;

import java.util.HashMap;
import java.util.Map;

public class AdviceFactory {

    public Map<String, AdviceDefinition> adviceDefinitionMap = new HashMap<>();

    private static final AdviceFactory INSTANCE = new AdviceFactory();

    private AdviceFactory() {
    }

    public static AdviceFactory getInstance() {
        return INSTANCE;
    }


}
