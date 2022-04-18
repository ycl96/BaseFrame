package com.ycl.service.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.nio.file.Paths;

/**
 * @author : YangChunLong
 * @date : Created in 2021/10/6 19:26
 * @description:
 * @modified By:
 * @version: :
 */
public class LuceneDemo {
    public void buildIndex () throws Exception{
        /*
         * 第一步：创建一个indexwriter对象
         * 1指定索引库的存放位置Directory对象
         * 2指定一个分析器，对文档内容进行分析。
         */
        Directory directory = FSDirectory.open(Paths.get("/Users/haokan/Home/ycl/source_code/BaseFrame/index"));
        // Directory directory = new RAMDirectory();//保存索引到内存中 （内存索引库）
        // 官方推荐分词器，对中文不友好
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, config);

        // 第二步：通过IO读取磁盘上的文件信息
        File f = new File("/Users/haokan/Home/ycl/source_code/BaseFrame/document");
        File[] listFiles = f.listFiles();
        if(listFiles != null){
            for (File file : listFiles) {
                if(file.isFile()){
                    // 第三步：创建document对象, 并把文件信息添加到document对象中
                    Document document = new Document();
                    // 文件名称
                    String file_name = file.getName();
                    Field fileNameField = new TextField("fileName", file_name, Field.Store.YES);
                    // 文件路径
                    String file_path = file.getPath();
                    Field filePathField = new StoredField("filePath", file_path);

                    // 文件大小
                    long file_size = FileUtils.sizeOf(file);
                    //索引
                    Field fileSizeField1 = new LongPoint("fileSize", file_size);
                    //存储
                    Field fileSizeField2 = new StoredField("fileSize", file_size);

                    // 文件内容
                    String file_content = FileUtils.readFileToString(file, "UTF-8");
                    Field fileContentField = new TextField("fileContent", file_content, Field.Store.NO);

                    document.add(fileNameField);
                    document.add(fileSizeField1);
                    document.add(fileSizeField2);
                    document.add(filePathField);
                    document.add(fileContentField);
                    // 第四步：使用indexwriter对象将document对象写入索引库，此过程进行索引创建。并将索引和document对象写入索引库。
                    indexWriter.addDocument(document);
                }
            }
            // 第五步：关闭IndexWriter对象。
            indexWriter.close();
        }
    }

    public void searchIndex() throws Exception{
        // 第一步：创建一个Directory对象，也就是索引库存放的位置。
        Directory directory = FSDirectory.open(Paths.get("/Users/haokan/Home/ycl/source_code/BaseFrame/index/demoIndex"));
        // 第二步：创建一个indexReader对象，需要指定Directory对象。
        IndexReader indexReader = DirectoryReader.open(directory);
        // 第三步：创建一个indexsearcher对象，需要指定IndexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        // 第四步：创建一个TermQuery对象，指定查询的域和查询的关键词。
        Query query = new TermQuery(new Term("body", "类"));
        // 第五步：执行查询。
        TopDocs topDocs = indexSearcher.search(query, 10);
        // 第六步：返回查询结果。遍历查询结果并输出。
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
//            // 文件名称
//            String fileName = document.get("fileName");
//            System.out.println(fileName);
            // 文件内容
            String fileContent = document.get("body");
            System.out.println(fileContent);
//            // 文件大小
//            String fileSize = document.get("fileSize");
//            System.out.println(fileSize);
//            // 文件路径
//            String filePath = document.get("filePath");
//            System.out.println(filePath);
            System.out.println("------------");
        }
        // 第七步：关闭IndexReader对象
        indexReader.close();
    }

    public static void main(String[] args) throws Exception{
        LuceneDemo luceneDemo = new LuceneDemo();
//        luceneDemo.buildIndex();
        luceneDemo.searchIndex();
    }
}
