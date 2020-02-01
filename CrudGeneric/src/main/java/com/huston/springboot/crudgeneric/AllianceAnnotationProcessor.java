package com.huston.springboot.crudgeneric;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * Annotation should be in  CLASS retention policy to work this
 * this will check the condition of the code we cannot use this for RUNTIME retention
 */
@SupportedAnnotationTypes( "com.huston.springboot.crudgeneric.Alliance" )
public class AllianceAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        for (final Element element : roundEnv.getElementsAnnotatedWith(Alliance.class)) {
            if (element instanceof VariableElement) {
                final VariableElement variableElement = (VariableElement) element;
                String test = variableElement.asType().toString();
                if (!"Set".equals(test)) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            "annotation kela wela");
                }
                return true;
            }
        }
        return true;
    }

}
