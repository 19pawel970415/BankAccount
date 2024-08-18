package org.example;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class Provider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(0.0, 0.0, 0.0),
                Arguments.of(Double.MAX_VALUE, Double.MAX_VALUE, 0.0),
                Arguments.of(5923926.9, 926.5, 5923000.4),
                Arguments.of(0.02, 0.01, 0.01));
    }
}
