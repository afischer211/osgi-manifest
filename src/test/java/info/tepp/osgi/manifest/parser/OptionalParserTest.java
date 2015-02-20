package info.tepp.osgi.manifest.parser;

import info.tepp.osgi.manifest.parser.Parser.OptionalParser;
import org.junit.Test;

import static info.tepp.osgi.manifest.parser.Parser.Maybe;
import static org.junit.Assert.assertEquals;

public class OptionalParserTest {

    @Test
    public void parsesOptionalDotCharacter() {
        OptionalParser<String> parser = Maybe(Token.Char('.'));
        assertEquals(Result.Success.of(Maybe.Some("."), "rest"), parser.parse(".rest"));
    }

    @Test
    public void parsesOptionalDotCharacter2() {
        OptionalParser<String> parser = Maybe(Token.Char('.'));
        assertEquals(Result.Success.of(Maybe.None(), "rest"), parser.parse("rest"));
    }


}
