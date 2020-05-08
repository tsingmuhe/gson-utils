package com.sunchangpeng.gson;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GSONTest {
    @Test
    public void testToJSONString1() {
        Person person = new Person();
        person.setName("");
        person.setAge(0);
        person.setMale(false);

        assertEquals("{\"name\":\"\",\"age\":0,\"male\":false}", GSON.toJSONString(person));
        assertEquals("[{\"name\":\"\",\"age\":0,\"male\":false}]", GSON.toJSONString(Collections.singletonList(person)));
        assertEquals("{\"person\":{\"name\":\"\",\"age\":0,\"male\":false}}", GSON.toJSONString(Collections.singletonMap("person", person)));
    }

    @Test
    public void testToJSONString2() {
        Person person = new Person();
        person.setName("");
        person.setAge(0);
        person.setMale(false);
        System.out.println(GSON.toJSONString(person, true));
    }

    @Test
    public void testParse() {
        System.out.println(GSON.parse("{}"));
        System.out.println(GSON.parse(" [] "));
        System.out.println(GSON.gson.fromJson(" {\n" +
                "  \"name\": \"\",\n" +
                "  \"age\": 0,\n" +
                "  \"male\": false\n" +
                "} ", JsonElement.class));
    }

    @Test
    public void testParseObject1() {
        System.out.println(GSON.parseObject("{\"name\":\"\",\"age\":0,\"male\":false}", Person.class));
    }

    @Test
    public void testParseObject2() {
        List<Person> list = GSON.parseObject(" [{\"name\":\"\",\"age\":0,\"male\":false}] ", new TypeToken<List<Person>>() {
        });
        System.out.println(list);
    }

    @Test
    public void testParseList() {
        List<JsonElement> objects = GSON.parseList(" [{\"name\":\"\",\"age\":0,\"male\":false}] ");
        System.out.println(objects);
    }

    @Test
    public void testParseList1() {
        List<Person> objects = GSON.parseList(" [{\"name\":\"\",\"age\":0,\"male\":false}] ", Person.class);
        System.out.println(objects);
    }

    @Test
    public void testParseList2() {
        List<Person> objects = GSON.parseList(" [{\"name\":\"\",\"age\":0,\"male\":false}] ", Person.class);
        System.out.println(objects);
    }

    @Test
    public void testParseList3() {
        List<Person> objects = GSON.parseList(" [{\"person\":{\"name\":\"\",\"age\":0,\"male\":false}}] ", new TypeToken<HashMap<String, Person>>() {
        });
        System.out.println(objects);
    }


    @Test
    public void testParseMap() {
        Map<String, JsonElement> objectMap1 = GSON.parseMap("{\"name\":\"\",\"age\":0,\"male\":false}");
        System.out.println(objectMap1);

        Map<String, JsonElement> objectMap2 = GSON.parseMap("{\"person\":{\"name\":\"\",\"age\":0,\"male\":false}}");
        System.out.println(objectMap2);
    }
}