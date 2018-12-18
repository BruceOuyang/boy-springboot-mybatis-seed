package club.bugmakers.seed.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * JSON 工具类
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
public class FastJsonUtils {

    public static  SerializeConfig serializeConfig;

    static{
        serializeConfig = new SerializeConfig();
        //配置类的序列化方式
        serializeConfig.put(java.util.Date.class,new JSONLibDataFormatSerializer());
        serializeConfig.put(java.sql.Date.class,new JSONLibDataFormatSerializer());
    }

    /**
     * 序列化时候的特性
     * @Author Bruce
     * @Date 10:57 2018/12/18
     * @Param 
     * @return 
     **/
    public static SerializerFeature[] serializerFeatures = {
            SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullNumberAsZero,
            SerializerFeature.WriteNullListAsEmpty,
            //当字段为null时候，也显示此字段。默认不显示
            SerializerFeature.WriteMapNullValue,
            //定义默认格式化 yyyy-MM-dd HH:mm:ss
//            SerializerFeature.WriteDateUseDateFormat,
            //当为null时候转换为""。
            SerializerFeature.WriteNullStringAsEmpty
    };


    public static String toJsonString(Object object){
        return toJsonString(object, serializeConfig);
    }

    public static String toJsonString(Object object,SerializeConfig serializeConfig){
        return toJsonString(object, serializeConfig, serializerFeatures);
    }

    public static String toJsonString(Object object,SerializeConfig serializeConfig,SerializerFeature ...serializerFeatures){
        return JSONObject.toJSONString(object,serializeConfig,serializerFeatures);
    }

    public static <T> T toBean(String jsonString,Class<T> T){
        return JSONObject.parseObject(jsonString,T);
    }

    public static <T> List<T> toListBean(String jsonString,Class<T> T){
        return JSONObject.parseArray(jsonString, T);
    }

    public static Map toMap(String jsonString){
        return (Map)JSONObject.parse(jsonString);
    }

}
