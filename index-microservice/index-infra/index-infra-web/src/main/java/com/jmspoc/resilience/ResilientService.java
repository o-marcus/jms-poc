package com.jmspoc.resilience;

import java.util.function.Supplier;

public interface ResilientService {
    public void process(Supplier<Void> method);
}
