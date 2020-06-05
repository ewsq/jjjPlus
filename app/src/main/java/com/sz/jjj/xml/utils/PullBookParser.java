package com.sz.jjj.xml.utils;

import android.util.Xml;

import com.sz.jjj.xml.model.Books;
import com.sz.jjj.xml.BookParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjj on 2017/8/23.
 *
 * @description:
 */

public class PullBookParser implements BookParser {

    @Override
    public List<Books> parse(InputStream is) throws Exception {
        List<Books> books = null;
        Books book = null;
        Books.Test test = null;

        XmlPullParser parser = Xml.newPullParser(); //由android.util.Xml创建一个XmlPullParser实例
        parser.setInput(is, "UTF-8");               //设置输入流 并指明编码方式

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    books = new ArrayList<Books>();
                    break;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("book")) {
                        book = new Books();
                    } else if (parser.getName().equals("id")) {
                        eventType = parser.next();
                        book.setId(Integer.parseInt(parser.getText()));
                    } else if (parser.getName().equals("name")) {
                        eventType = parser.next();
                        book.setName(parser.getText());
                    } else if (parser.getName().equals("price")) {
                        eventType = parser.next();
                        book.setPrice(Float.parseFloat(parser.getText()));
                    } else if (parser.getName().equals("test")) {
                        test = new Books.Test();
                    } else if (parser.getName().equals("aa")) {
                        eventType = parser.next();
                        test.setAa(parser.getText());
                    } else if (parser.getName().equals("bb")) {
                        eventType = parser.next();
                        test.setBb(parser.getText());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("book")) {
                        books.add(book);
                        book = null;
                    } else if (parser.getName().equals("test")) {
                        book.setTest(test);
                        test = null;
                    }

                    break;
            }
            eventType = parser.next();
        }
        return books;
    }

    @Override
    public String serialize(List<Books> books) throws Exception {
//      XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//      XmlSerializer serializer = factory.newSerializer();

        XmlSerializer serializer = Xml.newSerializer(); //由android.util.Xml创建一个XmlSerializer实例
        StringWriter writer = new StringWriter();
        serializer.setOutput(writer);   //设置输出方向为writer
        serializer.startDocument("UTF-8", true);
        serializer.startTag("", "books");
        for (Books book : books) {
            serializer.startTag("", "book");
            serializer.attribute("", "id", book.getId() + "");

            serializer.startTag("", "name");
            serializer.text(book.getName());
            serializer.endTag("", "name");

            serializer.startTag("", "price");
            serializer.text(book.getPrice() + "");
            serializer.endTag("", "price");

            serializer.endTag("", "book");
        }
        serializer.endTag("", "books");
        serializer.endDocument();

        return writer.toString();
    }
}
