package org.linuxq.dictionary.searchTool;

import io.netty.util.internal.StringUtil;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.linuxq.dictionary.data.SearchReq;
import org.linuxq.dictionary.util.FileGetterUtil;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class KeyFindTool {

    Logger logger = LoggerFactory.getLogger(KeyFindTool.class);

    private static final String TITLE_FILED_KEY = "title";
    private static final String INTRODUCTION_FILED_KEY = "introduction";
    private static final String CONTEXT_FILED_KEY = "context";
    private IndexReader reader;
    private Directory dir;
    public static final KeyFindTool tool = new KeyFindTool();
    private Map<String, DocItem> docItemMap;

    public KeyFindTool() {
        Path indexPath = null;
        Directory dir = null;
        try {
            indexPath = Paths.get("cache_index");
            dir = FSDirectory.open(indexPath);
            reader = DirectoryReader.open(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DocItem findAccurateItem(String key) {
        String path = "doc/" + key + ".md";
        InputStream inputStream = FileGetterUtil.getInputStream(path);
        if (inputStream == null) {
            DocItem docItem = new DocItem();
            docItem.setTitle("暂无结果");
            docItem.setContext("no DATA");
            return docItem;
        }
        try {
            String context = new String(inputStream.readAllBytes());
            DocItem docItem = new DocItem();
            docItem.setTitle(key);
            docItem.setContext(context);
            return docItem;
        } catch (IOException e) {
            DocItem docItem = new DocItem();
            docItem.setTitle("暂无结果");
            docItem.setContext("no DATA");
            return docItem;
        }
    }

    public List<SearchReq> findSampleItemList(String key) throws IOException, ParseException, InvalidTokenOffsetsException {
        if(StringUtil.isNullOrEmpty(key)){
            return new ArrayList<>();
        }
        IndexSearcher searcher = new IndexSearcher(reader);
        Analyzer analyzer = new SmartChineseAnalyzer();
        // 表达式查询方法
        QueryParser parser = new QueryParser(INTRODUCTION_FILED_KEY, analyzer);
        Query query = parser.parse(key);
        // 查询高亮
        QueryScorer score = new QueryScorer(query, INTRODUCTION_FILED_KEY);
        SimpleHTMLFormatter fors = new SimpleHTMLFormatter("<span>", "</span>");// 定制高亮标签
        Highlighter highlighter = new Highlighter(fors, score);// 高亮分析器

        List<SearchReq> searchReqs = new ArrayList<>();

        // 返回前10条
        TopDocs tds = searcher.search(query, 100);
        for (ScoreDoc sd : tds.scoreDocs) {
            SearchReq searchReq = new SearchReq();
            Document doc = searcher.doc(sd.doc);
            searchReq.setTitle(doc.get("title"));
            Fragmenter fragment = new SimpleSpanFragmenter(score);
            highlighter.setTextFragmenter(fragment);
            String str = highlighter.getBestFragment(analyzer, INTRODUCTION_FILED_KEY, doc.get(INTRODUCTION_FILED_KEY));// 获取高亮的片段
            searchReq.setData(str);
            searchReqs.add(searchReq);
        }
        return searchReqs;
    }

    public void close() {
        try {
            dir.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
