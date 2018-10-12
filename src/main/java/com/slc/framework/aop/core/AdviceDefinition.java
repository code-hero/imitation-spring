package com.slc.framework.aop.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdviceDefinition {
    private boolean hasBefore;
    private boolean hasAfter;
    private boolean hasAfterReturning;
    private boolean hasAfterThrowing;
    private boolean hasAround;

    private List<Method> beforeMethods = new ArrayList<>();
    private List<Method> afterMethods = new ArrayList<>();
    private List<Method> afterReturningMethods = new ArrayList<>();
    private List<Method> afterThrowingMethods = new ArrayList<>();
    private List<Method> aroundMethods = new ArrayList<>();

    private Map<String,String> beforeBeanName = new HashMap<>();
    private Map<String,String> afterBeanName = new HashMap<>();
    private Map<String,String> afterReturningBeanName = new HashMap<>();
    private Map<String,String> afterThrowingBeanName = new HashMap<>();
    private Map<String,String> aroundBeanName = new HashMap<>();

    public Map<String, String> getBeforeBeanName() {
        return beforeBeanName;
    }

    public void setBeforeBeanName(Map<String, String> beforeBeanName) {
        this.beforeBeanName = beforeBeanName;
    }

    public Map<String, String> getAfterBeanName() {
        return afterBeanName;
    }

    public void setAfterBeanName(Map<String, String> afterBeanName) {
        this.afterBeanName = afterBeanName;
    }

    public Map<String, String> getAfterReturningBeanName() {
        return afterReturningBeanName;
    }

    public void setAfterReturningBeanName(Map<String, String> afterReturningBeanName) {
        this.afterReturningBeanName = afterReturningBeanName;
    }

    public Map<String, String> getAfterThrowingBeanName() {
        return afterThrowingBeanName;
    }

    public void setAfterThrowingBeanName(Map<String, String> afterThrowingBeanName) {
        this.afterThrowingBeanName = afterThrowingBeanName;
    }

    public Map<String, String> getAroundBeanName() {
        return aroundBeanName;
    }

    public void setAroundBeanName(Map<String, String> aroundBeanName) {
        this.aroundBeanName = aroundBeanName;
    }

    //    private List<String> beanNames=new ArrayList<>();
//    public List<String> getBeanNames() {
//        return beanNames;
//    }
//
//    public void setBeanNames(List<String> beanNames) {
//        this.beanNames = beanNames;
//    }


    public boolean isHasBefore() {
        return hasBefore;
    }

    public void setHasBefore(boolean hasBefore) {
        this.hasBefore = hasBefore;
    }

    public boolean isHasAfter() {
        return hasAfter;
    }

    public void setHasAfter(boolean hasAfter) {
        this.hasAfter = hasAfter;
    }

    public boolean isHasAfterReturning() {
        return hasAfterReturning;
    }

    public void setHasAfterReturning(boolean hasAfterReturning) {
        this.hasAfterReturning = hasAfterReturning;
    }

    public boolean isHasAfterThrowing() {
        return hasAfterThrowing;
    }

    public void setHasAfterThrowing(boolean hasAfterThrowing) {
        this.hasAfterThrowing = hasAfterThrowing;
    }

    public boolean isHasAround() {
        return hasAround;
    }

    public void setHasAround(boolean hasAround) {
        this.hasAround = hasAround;
    }

    public List<Method> getBeforeMethods() {
        return beforeMethods;
    }

    public void setBeforeMethods(List<Method> beforeMethods) {
        this.beforeMethods = beforeMethods;
    }

    public List<Method> getAfterMethods() {
        return afterMethods;
    }

    public void setAfterMethods(List<Method> afterMethods) {
        this.afterMethods = afterMethods;
    }

    public List<Method> getAfterReturningMethods() {
        return afterReturningMethods;
    }

    public void setAfterReturningMethods(List<Method> afterReturningMethods) {
        this.afterReturningMethods = afterReturningMethods;
    }

    public List<Method> getAfterThrowingMethods() {
        return afterThrowingMethods;
    }

    public void setAfterThrowingMethods(List<Method> afterThrowingMethods) {
        this.afterThrowingMethods = afterThrowingMethods;
    }

    public List<Method> getAroundMethods() {
        return aroundMethods;
    }

    public void setAroundMethods(List<Method> aroundMethods) {
        this.aroundMethods = aroundMethods;
    }
}
