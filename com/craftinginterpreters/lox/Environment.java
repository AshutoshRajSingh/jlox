package com.craftinginterpreters.lox;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    private final Map<String, Object> values = new HashMap<>();
    final Environment enclosing;

    public Environment() {
        enclosing = null;
    }

    public Environment(Environment enclosing) {
        this.enclosing = enclosing;
    }

    void define(String name, Object value) {
        values.put(name, value);
    }

    void assign(Token name, Object value) {
        if (values.containsKey(name.lexeme)) {
            values.put(name.lexeme, value);
            return;
        }

        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }

        throw new RuntimeError(name,
                "Undefined target for assignment '" + name.lexeme + "'.");

    }

    public void assignAt(Token name, Object value, int depth) {
        ancestor(depth).values.put(name.lexeme, value);
    }

    public Object getAt(int depth, String name) {
        return ancestor(depth).values.get(name);
    }

    Environment ancestor(int which) {
        Environment parent = this.enclosing;

        for (int i = 1; i < which; i++) {
            parent = parent.enclosing;
        }
        return parent;
    }

    Object get(Token name) {
        if (values.containsKey(name.lexeme)) {
            return values.get(name.lexeme);
        } 

        if (enclosing != null) return enclosing.get(name);

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
        
    }
}
