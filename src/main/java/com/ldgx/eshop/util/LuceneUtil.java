package com.ldgx.eshop.util;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * lucene是apache组织的基于java的全文检索开源项目
 * @author Administrator
 *
 */
public class LuceneUtil {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 创建索引
	 */
	public void index() {
		IndexWriter writer = null;
		
		try {
			/*
			 * 1.创建Directory
			 * Directory directory = new RAMDirectory();//这个方法是建立在内存中的索引
			 * 在Lucene工具当中有两个子类分别是RAMDirectory 和 FSDirectory
			 * 这两个目录度可以作为索引的存储路径
			 * RAMDirectory是存放到内存当中的一个区域，FSDirectory是存放到文件系统中的磁盘里
			 * 虽然向其添加Document的过程与使用FSDDirectory一样，但是由于它是内存中的一块区域
			 * 因此，如果不将RAMDirectory中的内存写入磁盘，当虚拟机退出后，里面的内容也会随之消失。
			 * 一次需要将RAMDirectory中的内容转到FSDirectory中
			 */
			
			Directory directory = FSDirectory.open(Paths.get("D:/FuFu/lucene/index"));
			
			
			/*
			 * 2.创建IndexWriter,用完后要关闭
			 * 创建IndexWriter实例时，通过IndexWriterConfig来设置其相关配置：
			 * public IndexWriterConfig(Analyzer analyzer)
			 * analyzer：分词器对象
			 *  StandardAnalyzer是lucene中内置的“标准分析器”，可以做如下功能:
			 *  对原有句子按照空格进行了分词
			 *  所有的大写字母都可以能转换为小写的字母
			 *  可以去掉一些没有用处的单词，例如"is","the","are"等单词，也删除了所有的标点		            
			 */
			  
			IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
			 /*
             * IndexWriter用于更新或创建索引。它不是用来读取索引。
             * 创建索引写入对象，该对象既可以把索引写入到磁盘中也可以写入到内存中。 参数说明：
             * public IndexWriter(Directory directory, IndexWriterConfig conf)
             * directory:目录对象,也可以是FSDirectory 磁盘目录对象
             * conf:写入对象的控制
             */
			writer = new IndexWriter(directory,config);
			
			/*
			 * 3.创建Document对象
			 * 创建Document 文档对象，在lucene中创建的索引可以看成数据库中的一张表，
        	 * 表中也可以有字段,往里面添加内容之后可以根据字段去匹配查询
             * 下面创建的doc对象中添加了三个字段，分别为name,sex,dosomething,
			 */
			Document doc = new Document();
			
			/*
			 *  public Field(String name, String value, IndexableFieldType type)
			 *  @param name field name:字段名称
		     *  @param value string value:字段的值
		     *  @param type field type:  TextField.TYPE_STORED:存储字段值
			 */
			doc.add(new Field("id", "-1000", TextField.TYPE_STORED));
			doc.add(new StringField("text", "4text", Field.Store.NO));
			doc.add(new Field("name","4lin zhengle",TextField.TYPE_NOT_STORED));
			doc.add(new Field("address","3中国上海",TextField.TYPE_STORED));
			doc.add(new Field("dosometing","3I am learning lucene",TextField.TYPE_STORED));
			
			writer.addDocument(doc);
			
			
			writer.close();//indexer创建完索引后如果没有关闭（提交）导致索引没有完整创建，就会导致搜索报错
			directory.close();
			
		}catch(IOException e) {
			logger.error("lucene,index",e);
			e.printStackTrace();
		}finally {
			
		}
	}
	
	/**
	 * 这里演示根据已生成的索引，来查询
	 */
	public void searcher() {
		try {
			//1.创建Directory
			Directory directory = FSDirectory.open(Paths.get("D:/FuFu/lucene/index"));
			
			//2.创建IndexSearcher检索索引的对象，里面要传递上面写入的内存目录对象directory
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(ireader);
			
			/*
			 * 3.创建索引的Query
			 *  public Term(String fld, String searchKey)
			 *  @param fld:代表要搜索的Field
			 *  @param searchKey:代表查询关键字
			 *  Term这个类是搜索的最低单位，它是在索引过程中类似Field。建立搜索单元
			*/
			Query query = new TermQuery(new Term("dosometing","lucene"));
			
			//4.根据searcher搜索并返回TopDocs
			//表示返回前10行
			TopDocs topDocs = searcher.search(query, 10);
			
			//5.根据TopDocs获取ScoreDoc对象
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			
			for(ScoreDoc sd :scoreDocs) {
				
				//6.根据Searcher和ScordDoc对象获取具体的Document对象
				//获取这个温度的id
				int doc = sd.doc;
				Document document = searcher.doc(doc);
				
				//7.根据Document对象获取需要的值
				System.out.println("【" +doc + "找到】" +document.get("id") + "    " + document.get("name") + "    " + document.get("address") + " .." + document.get("dosometing"));
				
				
			}
			
			ireader.close();
			directory.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除索引
	 */
	public void delete() {
		try {
			IndexWriter writer = null;
			//1.创建Directory
			//Directory directory = new RAMDirectory();//这个方法是建立在内存中的索引
			Directory directory = FSDirectory.open(Paths.get("D:/FuFu/lucene/index"));
			
			//2.创建IndexWriter,用完后要关闭
			IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
			writer = new IndexWriter(directory,config);
			//删除全部索引
			//writer.deleteAll();
			
			//参数可以为一个查询的Query,也可以为一个Term,它是一个精确的值，代表着把id为1的给删除掉
			//注意，这里的删除，并不是真的删除。执行完之后，可以在索引的目录里面看到多了一个.del的文件，那是一个类似回收站的文件，在回收站中的文件是可以进行还原的
			writer.deleteDocuments(new Term("id","-1000"));
			
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	 /**
     * 删除索引并不是完全删除，它是有着一个回收站的功能
     * 上面的delete删除了一个索引，这里进行恢复
     */
    public void recovery(){
      /*  try {
        	//这一步很重要，因为默认打开的reader是只读的，所以这里要通过构造方法，把它的readonly设置为false，否则会抛出异常
            IndexReader reader = IndexReader.open(directory,false);
            //还原所有已删除的数据
            reader.undeleteAll();

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    
	public static void main(String[] args) {
		LuceneUtil util = new LuceneUtil();
		util.index();
		util.searcher();
		//util.delete();
	}
}
