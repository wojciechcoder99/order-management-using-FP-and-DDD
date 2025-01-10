package wfarganu.ddd;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The application service is one of building block in Domain Driven Design. It is treated as port exposing
 * operations relevant for the user. Mostly, it designs user stories. The scenario orchestrates domain objects
 * from lower layer. Often it is called Operation Script (as opposed to Transaction Script which is used with
 * data structures modelling). Simply put, this is an API for the client
 */
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ApplicationService {
}
