package com.java.utils;

//import com.mongodb.client.MongoClient;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;


public class Test {
    public static void main(String[] args) {
        //1.连接mongodb服务端
        MongoClient mongoClient = new MongoClient("127.0.0.1",27017);
        //2.连上指定的库
        MongoDatabase db = mongoClient.getDatabase("user");
        //3.连接指定的集合
        MongoCollection<Document> girl = db.getCollection("girl");
        //4.数据进行操作
              //数据封装为document
        Document doc = new Document();
        doc.append("name","西施1");
        doc.append("age","18");
        doc.append("pretty","99");
        girl.insertOne(doc);
        //封装方式2
        String json = "{\"name\":\"admin\",\"sex\":\"男\"}";
        Document parse = Document.parse(json);
        girl.insertOne(parse);

        //------------------------------------
        Document doc1 = new Document();
        doc1.append("name","西施");
        doc1.append("age","18");
        doc1.append("pretty","99");
        Document doc2 = new Document();
        doc2.append("name","貂蝉");
        doc2.append("age","18");
        doc2.append("pretty","99");
        Document doc3 = new Document();
        doc3.append("name","杨玉环");
        doc3.append("age","18");
        doc3.append("pretty","99");
        Document doc4 = new Document();
        doc4.append("name","王昭君");
        doc4.append("age","18");
        doc4.append("pretty","99");
        girl.insertMany(Arrays.asList(doc1,doc2,doc3,doc4));
        //--------------------------------
        FindIterable<Document> documents = girl.find();
        documents.skip(0).limit(10);
        documents.iterator().forEachRemaining(temp-> System.out.println(temp));
        //5.关流
        mongoClient.close();
    }
}
