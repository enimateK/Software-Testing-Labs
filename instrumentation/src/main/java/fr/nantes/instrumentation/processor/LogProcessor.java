package fr.nantes.instrumentation.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtStatementList;
import spoon.reflect.factory.TypeFactory;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.reference.CtTypeReference;
import spoon.support.reflect.code.CtStatementListImpl;

import java.io.PrintStream;


public class LogProcessor extends AbstractProcessor<CtInvocation> {

    private static final CtTypeReference PRINTSTREAM_REFERENCE = new TypeFactory().createReference(PrintStream.class);

    @Override
    public boolean isToBeProcessed(CtInvocation ctInvocation) {
        try {
            CtTypeReference ctTypeReference = ctInvocation.getTarget().getType();

            if (PRINTSTREAM_REFERENCE.equals(ctTypeReference)) {
                // The statement analysed is a System.out

                CtExecutableReference ctExecutableReference = ctInvocation.getExecutable();

                if (isPrint(ctExecutableReference)) {
                    //The statement analysed is a System.out.print/ln
                    return true;
                }
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void process(CtInvocation ctInvocation) {
        CtStatementList ctStatementList = new CtStatementListImpl<>();

        ctInvocation.insertBefore(getFactory().Code().createCodeSnippetStatement("/*")); //Open comment before the System.out.print

        ctStatementList.addStatement(getFactory().Code().createCodeSnippetStatement("*/")); //Close comment after System.out.print

        //Add the logger after the System.out.print
        ctStatementList.addStatement(getFactory().Code().createCodeSnippetStatement("LogWriter.out("+ctInvocation.getArguments().get(0).toString()+", "+isError(ctInvocation)+")"));

        ctInvocation.insertAfter(ctStatementList);
    }

    /**
     * checks that the given {@link CtExecutableReference} comes from a print() or println() statement.
     * @param ctExecutableReference a {@link CtExecutableReference}
     * @return true if it is a print statement, false otherwise.s
     */
    private boolean isPrint(CtExecutableReference ctExecutableReference) {
        return ctExecutableReference.toString().startsWith("println(") || ctExecutableReference.toString().startsWith("print(");
    }

    /**
     * checks that the given {@link CtInvocation} has System.err as a target.
     * @param ctInvocation a {@link CtInvocation}
     * @return true if it is a System.err, false otherwise.
     */
    private boolean isError(CtInvocation ctInvocation) {
        return ctInvocation.getTarget().toString().endsWith("err");
    }
}
