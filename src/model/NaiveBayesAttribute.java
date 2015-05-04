package model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * An indicator for what attributes should be used by naive bayes
 * 
 * @author Nathaniel Cotton
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface NaiveBayesAttribute {

}
