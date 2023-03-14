package com.craftinginterpreters.lox;

public class LoxInstance {
    LoxClass klass;

    public LoxInstance(LoxClass klass) {
        this.klass = klass;
    }

    @Override
    public String toString() {
        return this.klass.name + " instance";
    }
}
