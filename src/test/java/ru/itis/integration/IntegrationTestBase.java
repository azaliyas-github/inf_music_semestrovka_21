package ru.itis.integration;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import ru.itis.*;
import ru.itis.services.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@DisplayNameGeneration(ReplaceCamelCase.class)
public class IntegrationTestBase {
}
