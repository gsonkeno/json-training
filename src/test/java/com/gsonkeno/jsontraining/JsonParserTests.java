package com.gsonkeno.jsontraining;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonParserTests {

    @Test
    public void testReadFile() throws IOException {
        ClassPathResource cpr = new ClassPathResource("case1.json");
        InputStream is = cpr.getInputStream();
        String json = IOUtils.toString(is);
        System.out.println(json);

        ObjectMapper om = new ObjectMapper();
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        // 第三方的接口响应结果，我们经常只想要其中的一部分数据，那么
        // 定义响应model时，做了如下配置后，只需要包含想要的属性即可，
        // 其他结果在序列化化时会忽略掉
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 值为null的字段不参与序列化
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);


        /**jackson 没有jsonObject对象，一般就是把json字符串转成业务对象**/
        SearchResp searchResp = om.readValue(json, SearchResp.class);
        System.out.println(searchResp);


    }


}

/**
 * JsonIgnoreProperties注解，可以忽略掉json字符串中业务不需要的json键
 * 但是如果配置了 objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
 * 这个注解也可以忽略掉
 */
//@JsonIgnoreProperties({"Head","PicInfo","RequestId","Msg","Success","Code"})
class SearchResp {

    /**
     * JsonProperty注解在业务上的java属性上，值为与之匹配的json字符串中的json键
     **/
    @JsonProperty(value = "Auctions")
    List<Auction> actions;


    @Override
    public String toString() {
        return "SearchResp{" +
                "actions=" + actions +
                '}';
    }
}

//@JsonIgnoreProperties({"CustomContent","StrAttr"})
class Auction {
    @JsonProperty(value = "CategoryId")
    int categoryId;
    @JsonProperty(value = "PicName")
    String picName;

    @JsonProperty(value = "SortExprValues")
    String sortExprValues;

    @JsonProperty(value = "ProductId")
    String productId;

    @Override
    public String toString() {
        return "Auction{" +
                "categoryId=" + categoryId +
                ", picName='" + picName + '\'' +
                ", sortExprValues='" + sortExprValues + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }
}
