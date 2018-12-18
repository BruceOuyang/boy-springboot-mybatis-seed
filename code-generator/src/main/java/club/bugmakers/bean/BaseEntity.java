package club.bugmakers.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

/**
 * 实体基类
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
public abstract class BaseEntity implements Serializable {

	@Override
    public String toString() {
		try {
			return this.getClass().getName() + " = " + JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
		} catch (Exception e) {
			return super.toString();
		}
	}
}