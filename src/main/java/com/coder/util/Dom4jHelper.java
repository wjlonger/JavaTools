/**
 *
 */
package com.coder.util;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class Dom4jHelper {

    public Document parse(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    }

    public void treeWalk(Document document) {
        treeWalk(document.getRootElement());
    }

    public void treeWalk(Element element) {
        for (int i = 0, size = element.nodeCount(); i < size; i++) {
            Node node = element.node(i);
            if (node instanceof Element) {
                treeWalk((Element) node);
            } else {
            }
        }
    }

    public static Element parse(String xmlPath, String encoding) throws Exception {
        File file = new File(xmlPath);
        if (!file.exists()) {
            throw new Exception("文件不存在：" + xmlPath);
        }
        SAXReader reader = new SAXReader(false);
        Document doc = reader.read(new FileInputStream(file), encoding);
        Element root = doc.getRootElement();
        return root;
    }

    public static void save(Document doc, String xmlPath, String encoding) throws Exception {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(encoding);
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(xmlPath), encoding), format);
        writer.write(doc);
        writer.flush();
        writer.close();
    }

    public static void modifyDocument(File inputXml, String nodes, String attributename, String value, String outXml) {
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputXml);
            List list = document.selectNodes(nodes);
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                Attribute attribute = (Attribute) iter.next();
                if (attribute.getName().equals(attributename)) {
                    attribute.setValue(value);
                }
            }
            XMLWriter output;
            if (outXml != null) {
                output = new XMLWriter(new FileWriter(new File(outXml)));
            } else {
                output = new XMLWriter(new FileWriter(inputXml));
            }
            output.write(document);
            output.close();
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String toString(Document doc, String encoding) throws Exception {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(encoding);
        ByteArrayOutputStream byteOS = new ByteArrayOutputStream();
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(byteOS, encoding), format);
        writer.write(doc);
        writer.flush();
        writer.close();
        writer = null;
        return byteOS.toString(encoding);
    }

}
