package com.semantalytics.stardog.kibble.string.phonetic;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public class DaitchMokotoffSoundex extends AbstractFunction implements StringFunction {

    private static final org.apache.commons.codec.language.DaitchMokotoffSoundex daitchMokotoffSoundex;

    static {
        daitchMokotoffSoundex = new org.apache.commons.codec.language.DaitchMokotoffSoundex();
    }

    protected DaitchMokotoffSoundex() {
        super(1, PhoneticVocabulary.daitchMokotoffSoundex.stringValue());
    }

    private DaitchMokotoffSoundex(final DaitchMokotoffSoundex daitchMokotoffSoundex) {
        super(daitchMokotoffSoundex);
    }

    @Override
    public Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        final String string = assertStringLiteral(values[0]).stringValue();

        return literal(daitchMokotoffSoundex.encode(string));
    }

    @Override
    public Function copy() {
        return new DaitchMokotoffSoundex(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return PhoneticVocabulary.daitchMokotoffSoundex.name();
    }
}

