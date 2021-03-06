package com.ldgx.eshop.util;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
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

import com.ldgx.eshop.entity.Goods;

/**
 * Lucene事件应用
 * @author Administrator
 *
 */
public class LuceneUtil {
	
	private static Logger logger = LoggerFactory.getLogger(LuceneUtil.class);
	
	private static String path = "D:/FuFu/lucene/index";

	/**
	 * 判断是否存在数据
	 * @return true:该文件夹下面有文件
	 * false:该文件夹下面没有文件
	 */
	public static boolean ifExists() throws Exception{
		logger.info("===info===");
		File pfile = new File(path);
		if(pfile.exists() && pfile.isDirectory() ) {
			File[] files = pfile.listFiles();
			if(files != null && files.length > 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 保存数据到lucene
	 * @param list
	 * @throws Exception
	 */
	public static void saveGoods(List<Goods> list) throws Exception{
		logger.info("===saveGoods===");
		
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
			
			Directory directory = FSDirectory.open(Paths.get(path));
			
			
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
			//3.保存信息
			for(Goods goods : list) {
				//Document代表是一条数据，Field代表数据中的一个属性，一个Document中有多个Field
				Document doc = new Document();
				/*			 * 	
				 * 用户String类型的字段的存储，StringField是只索引不分词
				 * 对String类型的字段进行存储，TextField和StringField的不同是TextField既索引又分词
				 * 
				 * Store.YES 保存，可以查询，可以打印内容
				 * Store.NO 不保存，可以查询，不可打印内容，由于不保存内容可以节省空间
				 * Store.COMPRESS 压缩保存 可以查询 可以打印内容 可以节省生成索引文件的空间
				 */			
				doc.add(new TextField("id",goods.getId() + "",Store.YES));
				
				String goodsName = goods.getName();
				if(goodsName == null) {
					goodsName = "";
				}
				
				doc.add(new StringField("name",goodsName,Store.YES));
				
				String goodsRemark = goods.getRemark();
				if(goodsRemark == null) {
					goodsRemark = "";
				}
				
				doc.add(new TextField("remark",goodsRemark,Store.YES));
				
				writer.addDocument(doc);//添加文档
			}
			
			writer.close();//indexer创建完索引后如果没有关闭（提交）导致索引没有完整创建，就会导致搜索报错
			directory.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			
		}
		
		
		
	}
	
	/**
	 * 根据name从lucene得到Goods数据
	 * 对特定项搜索
	 * 按词条搜索-TermQuery
	 * @param name
	 * @param limit:显示几条记录
	 * @return
	 */
	public static List<Goods> queryByName(String name,int limit) throws Exception{
		logger.info("===queryByName===");
		List<Goods> list = new ArrayList<Goods>();
		
		try {
			//1.创建Directory
			Directory directory = FSDirectory.open(Paths.get(path));
			
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
			Query query = new TermQuery(new Term("name",name));
			
			//4.根据searcher搜索并返回TopDocs
			//表示返回前10行
			TopDocs topDocs = searcher.search(query, limit);
			
			//5.根据TopDocs获取ScoreDoc对象
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			
			for(ScoreDoc sd :scoreDocs) {
				
				//6.根据Searcher和ScordDoc对象获取具体的Document对象
				//获取这个温度的id
				/*int doc = sd.doc;
				Document document = searcher.doc(doc);
				
				//7.根据Document对象获取需要的值
				System.out.println("【" +doc + "找到】" +document.get("id") + "    " + document.get("name") + "    " + document.get("address") + " .." + document.get("dosometing"));
				*/
				int doc = sd.doc;
				Document document = searcher.doc(doc);
				Goods goods = new Goods();
				if(document.get("id") != null) {
					String dbids = document.get("id");
					int dbid = Integer.parseInt(dbids);
					goods.setId(dbid);
					
				}
				if(document.get("name") != null) {
					String dbname = document.get("name");
					goods.setName(dbname);
					
				}
				
				list.add(goods);
				
				
			}
			
			ireader.close();
			directory.close();
			return list;
		}catch(IOException e) {
			e.printStackTrace();
			return list;
		}
		
		
	}
	
}
