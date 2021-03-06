package com.ldgx.eshop.util;

import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class LuceneTest2 {
	
	private Directory dir;
	private IndexReader reader;
	private IndexSearcher is;
	
	@Before
	public void setUp() throws Exception{
		System.out.println("=======before=======");
		dir = FSDirectory.open(Paths.get("D:/FuFu/lucene/index"));
		reader = DirectoryReader.open(dir);
		is = new IndexSearcher(reader);
	}
	
	@After
	public void tearDown() throws Exception{
		System.out.println("=======after=======");
		reader.close();
	}
	
	/**
    * 获取IndexWriter实例
    * @return
    * @throws Exception
    */
	private IndexWriter getWriter() throws Exception{
		Analyzer analyzer = new StandardAnalyzer();//标准分词器
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(dir,iwc);
		return writer;
	}
	
	/**
	 * 保存
	 * @throws Exception
	 */
	@Test
	public void saveIndex() throws Exception{
		IndexWriter writer = this.getWriter();
		for(int i  = 0;i<10; i ++) {
			Document doc = new Document();
			doc.add(new StringField("ids",i + "",Field.Store.YES));
			doc.add(new StringField("city","城市" + i,Field.Store.YES));
			doc.add(new TextField("desc","desc,"+i,Field.Store.NO));
			writer.addDocument(doc);//添加文档
		}
		writer.close();
	}
	
	/**
    * 测试写了几个文档
    * @throws Exception
    */
	@Test
	public void testIndexWriter() throws Exception{
		IndexWriter writer = this.getWriter();
		System.out.println("写入了"+writer.numDocs()+"个文档");
		writer.close();
	}
	
	/**
    * 测试读取文档
    * @throws Exception
    */
	@Test
	public void testIndexReader() throws Exception{
		IndexReader reader = DirectoryReader.open(dir);
		System.out.println("最大文档数" + reader.maxDoc());
		System.out.println("实际文档数:"+reader.numDocs());
		reader.close();
	}
	
	/**
    * 测试删除 在合并前
    * 使用deleteDocuments删除后实际文档数减一，其实就是做了个标记未真正
    * 删除，最大文档数未变
    * @throws
    */
	@Test
	public void testDeleteBeforeMerge() throws Exception{
		IndexWriter writer = this.getWriter();
		System.out.println("删除前:" + writer.numDocs());
		writer.deleteDocuments(new Term("id","2"));// 强制删除此时删除的文档并不会被完全删除，而是存储在一个回收站中的，可以恢复
		writer.commit();//更改索引要提交，和提交数据库事务一个概念，真正的删除
		System.out.println("writer.maxDoc()："+writer.maxDoc());
        System.out.println("writer.numDocs()："+writer.numDocs());
        writer.close();
	}
	
   /**
    * 测试删除 在合并后
    * 使用forceMergeDeletes强制删除
    * 删除后实际文档数减一，最大文档数减一
    * @throws Exception
    */
   @Test
   public void testDeleteAfterMerge()throws Exception{
      IndexWriter writer=getWriter();
      System.out.println("删除前："+writer.numDocs());
      writer.deleteDocuments(new Term("id","3"));// 强制删除此时删除的文档并不会被完全删除，而是存储在一个回收站中的，可以恢复
      writer.forceMergeDeletes(); //强制合并删除的索引信息，索引量大的时候不推荐使用，真正的删除
      // writer.commit(); //更改索引要提交，和提交数据库事务一个概念，真正的删除
      writer.commit();
      System.out.println("writer.maxDoc()："+writer.maxDoc());
      System.out.println("writer.numDocs()："+writer.numDocs());
      writer.close();
   }
	
   /**
    * 测试更新
    * 实际上就是删除后新增一条
    * @throws Exception
    */
   @Test
   public void testUpdate() throws Exception{
	   IndexWriter writer = this.getWriter();
	   Document doc = new Document();
	   doc.add(new StringField("id","1",Field.Store.YES));
       doc.add(new StringField("city","qingdao",Field.Store.YES));
       doc.add(new TextField("desc", "dsss is a city.", Field.Store.NO));
       writer.updateDocument(new Term("id","1"), doc);
       writer.close();
   }
   
	/**
    * 对特定项搜索
    *  按词条搜索—TermQuery
     *TermQuery是最简单、也是最常用的Query。TermQuery可以理解成为“词条搜索”，
    * 在搜索引擎中最基本的搜索就是在索引中搜索某一词条，而TermQuery就是用来完成这项工作的。
     * 在Lucene中词条是最基本的搜索单位，从本质上来讲一个词条其实就是一个名/值对。
    * 只不过这个“名”是字段名，而“值”则表示字段中所包含的某个关键字。
    * @throws Exception
    */
	@Test
	public void testTermQuery() throws Exception{
		System.out.println("testTermQuery");
		String searchField = "id";
		String q = "2";
		Term t = new Term(searchField,q);
		Query query = new TermQuery(t);
		TopDocs hits = is.search(query, 10);
		if(hits == null) {
			System.out.println("没有查询到值");
			return;//退出
		}
		System.out.println("匹配'" + q + "',总共查询到" + hits.totalHits + "个文档");
		for(ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = is.doc(scoreDoc.doc);
			System.out.println(doc.get("city"));
		}
	}
}
