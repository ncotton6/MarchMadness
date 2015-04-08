package model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Placing this annotation on a method will indicate that the returned value
 * from the method is information that can be used to predict the outcome of the
 * tournament.
 * 
 * @author Nathaniel Cotton
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface Attribute {

}
