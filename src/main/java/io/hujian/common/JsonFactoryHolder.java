package io.hujian.common;

import com.fasterxml.jackson.core.JsonFactory;

/**
 * Created by hujian06 on 2017/11/2.
 *
 * the fastJson factory
 */
public class JsonFactoryHolder {

    private static final JsonFactory JSON_FACTORY = new JsonFactory();

    /**
     * get the only json factory.
     * @return shared handler
     */
    public static JsonFactory getJsonFactory() {
        return JSON_FACTORY;
    }

}
