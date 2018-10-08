package com.slc.framework.aop.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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

    private String beanName;


    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

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
