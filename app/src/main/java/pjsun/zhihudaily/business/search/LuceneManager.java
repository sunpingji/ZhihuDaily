package pjsun.zhihudaily.business.search;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.List;

import pjsun.zhihudaily.business.bean.DailyResult;
import pjsun.zhihudaily.business.bean.Story;

/**
 * Created by sunpingji on 2017/3/15.
 */
public class LuceneManager {
    private static LuceneManager ourInstance = new LuceneManager();

    public static LuceneManager getInstance() {
        return ourInstance;
    }

    private static final String CACHE_PATH = "/data/data/pjsun.zhihudaily/cache/lucene";

    private Directory directory;
    private Analyzer analyzer;


    private static final String IMAGE = "image";
    private static final String TITLE = "title";
    private static final String ID = "id";

    private LuceneManager() {
        File file = new File(CACHE_PATH);
        try {
            directory = FSDirectory.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
    }

    public void addIndex(DailyResult result) {
        IndexWriter indexWriter = null;
        try {
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, analyzer);
            indexWriter = new IndexWriter(directory, config);
            List<Story> stories = result.getStories();
            if (stories != null && stories.size() > 0) {
                for (int i = 0; i < stories.size(); i++) {
                    Story story = stories.get(i);
                    Document doc = new Document();
                    doc.add(new Field(IMAGE, story.getImages().get(0), Field.Store.YES,
                            Field.Index.ANALYZED));
                    doc.add(new Field(TITLE, story.getTitle(), Field.Store.YES,
                            Field.Index.ANALYZED));
                    doc.add(new Field(ID, story.getId(), Field.Store.YES,
                            Field.Index.ANALYZED));
                    indexWriter.addDocument(doc);
                }
            }
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void search(String name) {
        try {
            IndexReader ireader = IndexReader.open(directory); // read-only=true
            IndexSearcher isearcher = new IndexSearcher(ireader);
            //
            QueryParser parser = new QueryParser(Version.LUCENE_CURRENT, TITLE, analyzer);
            Query query = parser.parse(name);
            ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;

            for (int i = 0; i < hits.length; i++) {
                Document hitDoc = isearcher.doc(hits[i].doc);
            }
            isearcher.close();
            ireader.close();
            directory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
