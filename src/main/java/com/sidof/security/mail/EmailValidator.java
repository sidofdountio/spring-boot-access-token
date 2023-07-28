package com.sidof.security.mail;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

/**
 * @Author sidof
 * @Since 12/07/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Service
public class EmailValidator implements Predicate<String > {
    /**
     * Evaluates this predicate on the given argument.
     *
     * @param s the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    @Override
    public boolean test(String username) {
        return true;
    }
}
