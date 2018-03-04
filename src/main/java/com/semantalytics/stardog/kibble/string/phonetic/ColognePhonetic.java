package com.semantalytics.stardog.kibble.string.phonetic;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class ColognePhonetic extends AbstractFunction implements StringFunction {

    private static final org.apache.commons.codec.language.ColognePhonetic colognePhonetic;

    static {
        colognePhonetic = new org.apache.commons.codec.language.ColognePhonetic();
    }

    protected ColognePhonetic() {
        super(1, PhoneticVocabulary.colognePhonetic.stringValue());
    }

    private ColognePhonetic(final ColognePhonetic colognePhonetic) {
        super(colognePhonetic);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        final String string = assertStringLiteral(values[0]).stringValue();
        return literal(colognePhonetic.encode(string));
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public Function copy() {
        return new ColognePhonetic(this);
    }

    @Override
    public String toString() {
        return PhoneticVocabulary.colognePhonetic.name();
    }
}
