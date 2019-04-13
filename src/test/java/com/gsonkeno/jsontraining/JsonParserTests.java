package com.gsonkeno.jsontraining;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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

        /**jackson 没有jsonObject对象，一般就是把json字符串转成业务对象**/
        SearchResp searchResp = om.readValue(json, SearchResp.class);
        System.out.println(searchResp);


    }


}

/**
 * JsonIgnoreProperties注解，可以忽略掉json字符串中业务不需要的json键
 */
@JsonIgnoreProperties({"Head","PicInfo","RequestId","Msg","Success","Code"})
class SearchResp{

    /**JsonProperty注解在业务上的java属性上，值为与之匹配的json字符串中的json键**/
    @JsonProperty(value = "Auctions")
    List<Auction> actions;


    @Override
    public String toString() {
        return "SearchResp{" +
                "actions=" + actions +
                '}';
    }
}

@JsonIgnoreProperties({"CustomContent","StrAttr"})
class Auction{
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
