package org.linuxq.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadMain {
    public static void main(String[] args) throws IOException, ParseException, InvalidTokenOffsetsException {
        String field = "introduction";
        Path indexPath = Paths.get("cache_index");
        Directory dir = FSDirectory.open(indexPath);
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        Analyzer analyzer = new SmartChineseAnalyzer();
        QueryParser parser = new QueryParser(field, analyzer);
        Query query = parser.parse("设备搜索");
        System.out.println("Query:" + query);

        // 查询高亮
        QueryScorer score = new QueryScorer(query, field);
        SimpleHTMLFormatter fors = new SimpleHTMLFormatter("<span style=\"color:red;\">", "</span>");// 定制高亮标签
        Highlighter highlighter = new Highlighter(fors, score);// 高亮分析器

        // 返回前10条
        TopDocs tds = searcher.search(query,10);
        for (ScoreDoc sd : tds.scoreDocs) {
            Document doc = searcher.doc(sd.doc);
            System.out.println("title:" + doc.get("title"));
            Fragmenter fragment = new SimpleSpanFragmenter(score);
            highlighter.setTextFragmenter(fragment);
            String str = highlighter.getBestFragment(analyzer, field, doc.get(field));// 获取高亮的片段
            System.out.println("高亮的片段:" + str);
        }
        dir.close();
        reader.close();
    }
}
