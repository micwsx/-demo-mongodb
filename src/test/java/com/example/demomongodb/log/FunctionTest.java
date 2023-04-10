package com.example.demomongodb.log;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author: Michael
 * @date: 5/1/2022 10:00 PM
 */
public class FunctionTest {

    interface Cache{
        String getName();
    }

    interface CacheManager{
        Cache getCache(String name);
    }

    interface CachingConfigurer{
        default CacheManager cacheManager(){
            return null;
        }
    }


    private Supplier<CacheManager> cacheManager;

    public void useCachingConfigurer(CachingConfigurerSupplier cachingConfigurerSupplier){
        this.cacheManager=cachingConfigurerSupplier.adapt(CachingConfigurer::cacheManager);
    }


    public static class CachingConfigurerSupplier{

        private CachingConfigurer cachingConfigurer;

        public CachingConfigurerSupplier(CachingConfigurer cachingConfigurer) {
            this.cachingConfigurer = cachingConfigurer;
        }

        public <T> Supplier<T> adapt(Function<CachingConfigurer,T> supplier){
            return ()->{
                if (cachingConfigurer!=null){
                    return supplier.apply(this.cachingConfigurer);
                }
                return null;
            };
        }
    }
}
