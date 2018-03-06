package com.semantalytics.stardog.kibble.string.phonetic;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.Test;
import org.openrdf.model.Literal;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestCarverphone2 extends AbstractStardogTest {

    @Test
    public void testOneArg() {

        final String aQuery = PhoneticVocabulary.sparqlPrefix("phonetic") +
                "select ?result where { bind(phonetic:carverphone2(\"Stardog\") as ?result) }";

        try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {

            assertTrue("Should have a result", aResult.hasNext());

            final Value aValue = aResult.next().getValue("result");

            assertTrue(aValue instanceof Literal);

            final String aLiteralValue = aValue.stringValue();

            assertEquals("STTK111111", aLiteralValue);
            assertFalse("Should have no more results", aResult.hasNext());
        }
    }

    @Test
    public void testTooFewAargs() {

        final String aQuery = PhoneticVocabulary.sparqlPrefix("phonetic") +
                "select ?result where { bind(phonetic:carverphone2() as ?result) }";

        try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {

            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
            assertFalse("Should have no more results", aResult.hasNext());
        }
    }

    @Test
    public void testTooManyAargs() {

        final String aQuery = PhoneticVocabulary.sparqlPrefix("phonetic") +
                "select ?result where { bind(phonetic:carverphone2(\"one\", \"two\") as ?result) }";

        try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {

            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
            assertFalse("Should have no more results", aResult.hasNext());
        }
    }

    @Test
    public void testFirstArgWrongType() {

        final String aQuery = PhoneticVocabulary.sparqlPrefix("phonetic") +
                "select ?result where { bind(phonetic:carverphone2(7) as ?result) }";

        try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {

            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
            assertFalse("Should have no more results", aResult.hasNext());
        }
    }
}
