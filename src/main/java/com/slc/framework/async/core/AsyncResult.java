/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.slc.framework.async.core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AsyncResult<V> implements Future<V> {

    private final V value;

    private final ExecutionException executionException;


    /**
     * Create a new AsyncResult holder.
     *
     * @param value the value to pass through
     */
    public AsyncResult(V value) {
        this(value, null);
    }

    /**
     * Create a new AsyncResult holder.
     *
     * @param value the value to pass through
     */
    private AsyncResult(V value, ExecutionException ex) {
        this.value = value;
        this.executionException = ex;
    }


    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public V get() throws ExecutionException {
        if (this.executionException != null) {
            throw this.executionException;
        }
        return this.value;
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws ExecutionException {
        return get();
    }

}
