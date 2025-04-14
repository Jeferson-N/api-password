
package br.com.jeferson.password.v1.rule;

public interface PasswordRule {
    boolean validate(String password);

    String message();
}