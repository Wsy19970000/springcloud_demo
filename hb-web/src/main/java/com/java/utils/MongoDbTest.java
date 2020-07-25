package com.java.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class MongoDbTest {
    InputStream ins = null;
    MongoCollection<Document> collection = null;

    @Before
    public void init(){
        try {
            //1、创建Properties对象来表示mongo.properties文件
            Properties prop = new Properties();
            //2、关联
            ins = MongoUtil.class.getClassLoader().getResourceAsStream("mongo.properties");
            prop.load(ins);
            //3、获取文件中的数据
            String host = prop.getProperty("host");
            int port = Integer.parseInt(prop.getProperty("port"));
            String databaseName = prop.getProperty("databaseName");
            String collectionName = prop.getProperty("collectionName");
            MongoClient mongoClient = new MongoClient(host,port);
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            collection = database.getCollection(collectionName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //分页查询
    public void select(){
        FindIterable<Document> documents = collection.find();
        FindIterable<Document> limit = documents.skip(0).limit(10);
        limit.iterator().forEachRemaining(temp-> System.out.println(temp));
    }

    //带条件查询
    public void select2(){
        //bson是document的父类型,传递document对象
        Document document = new Document();
        document.append("name","西施");
        FindIterable<Document> documents = collection.find(document);
        //直接构建gson
        Document parse = Document.parse("\"name\":\"西施");
        FindIterable<Document> documents1 = collection.find(parse);
        //bsongeshi
        Bson age = Filters.gt("age",18);
        Bson name = Filters.eq("name","西施");
        Bson bson = Filters.and(age, name);
        FindIterable<Document> documents2 = collection.find(bson);
    }

    public void remove(){
        Document parse = Document.parse("{\"age\":{$gt:18}}");
        DeleteResult deleteResult = collection.deleteMany(parse);
    }

    @After
    public void close(){
        try {
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
