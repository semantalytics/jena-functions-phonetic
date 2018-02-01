package com.semantalytics.stardog.kibble.strings.string;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class PrependIfMissingTest  extends AbstractStardogTest {

    @Test
    public void testAbbreviateMiddle() {

       final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?abbreviation where { bind(string:prependIfMissing(\"Stardog graph database\", \"...\", 8) AS ?abbreviation) }";


            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("abbreviation").stringValue();

                assertEquals("Stard...", aValue);
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testEmptyString() {
    
       final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?abbreviation where { bind(string:prependIfMissing(\"\", 5) as ?abbreviation) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("abbreviation").stringValue();

                assertEquals("", aValue);
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testTooFewArgs() {

       final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?abbreviation where { bind(string:prependIfMissing(\"one\") as ?abbreviation) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testTooManyArgs() {

       final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?abbreviation where { bind(string:prependIfMissing(\"one\", 2, \"three\") as ?abbreviation) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testWrongTypeFirstArg() {
    
       final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?abbreviation where { bind(string:prependIfMissing(4, 5) as ?abbreviation) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testWrongTypeSecondArg() {
     
       final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?abbreviation where { bind(string:prependIfMissing(\"one\", \"two\") as ?abbreviation) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testLengthTooShort() {
      
       final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?abbreviation where { bind(string:prependIfMissing(\"Stardog\", 3) as ?abbreviation) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }
}
