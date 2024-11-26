package com.example.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.RAMDirectory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;


class InMemoryLuceneIndexTest {

    @Test
    public void givenSearchQueryWhenFetchedDocumentThenCorrect() {
        InMemoryLuceneIndex inMemoryLuceneIndex 
        = new InMemoryLuceneIndex(new RAMDirectory(), new StandardAnalyzer());
        inMemoryLuceneIndex.indexDocument("Hello world", "Some hello world ");
        inMemoryLuceneIndex.indexDocument("Hello Karnataka", "Hello Bangalore! ");

        List<Document> documents = inMemoryLuceneIndex.searchIndex("body", "Bangalore");

        Assertions.assertEquals("Hello Karnataka", documents.get(0).get("title"));
    }

    @Test
    public void givenTermQueryWhenFetchedDocumentThenCorrect() {
        InMemoryLuceneIndex inMemoryLuceneIndex 
        = new InMemoryLuceneIndex(new RAMDirectory(), new StandardAnalyzer());
        inMemoryLuceneIndex.indexDocument("activity", "running in track");
        inMemoryLuceneIndex.indexDocument("activity", "Cars are running on road");

        Term term = new Term("body", "running");
        Query query = new TermQuery(term);

        List<Document> documents = inMemoryLuceneIndex.searchIndex(query);
        assertEquals(2, documents.size());
    }

    @Test
    public void givenPrefixQueryWhenFetchedDocumentThenCorrect() {
        InMemoryLuceneIndex inMemoryLuceneIndex 
        = new InMemoryLuceneIndex(new RAMDirectory(), new StandardAnalyzer());
        inMemoryLuceneIndex.indexDocument("article", "Lucene introduction");
        inMemoryLuceneIndex.indexDocument("article", "Conclusion to Lucene");

        Term term = new Term("body", "intro");
        Query query = new PrefixQuery(term);

        List<Document> documents = inMemoryLuceneIndex.searchIndex(query);
        assertEquals(1, documents.size());
    }

     @Test
    public void givenWildCardQueryWhenFetchedDocumentThenCorrect() {
        InMemoryLuceneIndex inMemoryLuceneIndex = new InMemoryLuceneIndex(new RAMDirectory(), new StandardAnalyzer());
        inMemoryLuceneIndex.indexDocument("article", "Lucene introduction");
        inMemoryLuceneIndex.indexDocument("article", "Introducing Lucene with Spring");

        Term term = new Term("body", "intro*");
        Query query = new WildcardQuery(term);

        List<Document> documents = inMemoryLuceneIndex.searchIndex(query);
        assertEquals(2, documents.size());
    }


}