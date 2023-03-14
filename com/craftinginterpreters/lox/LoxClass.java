package com.craftinginterpreters.lox;

import java.util.List;

public class LoxClass implements LoxCallable {
    String name;

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        LoxInstance instance = new LoxInstance(this);
        return instance;
    }

    @Override
    public int arity() {
        return 0;
    }

    public LoxClass(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}