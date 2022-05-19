package org.jetbrains.plugins.template1;

public class QueryResult {
    private String Path;
    private String Text;
    private String AlteredText;
    private Position Start;
    private Position End;

    public QueryResult(String path, String text, String alteredText, Position start, Position end) {
        Path = path;
        Text = text;
        AlteredText = alteredText;
        Start = start;
        End = end;
    }

    public String getPath() {
        return Path;
    }

    public String getText() {
        return Text;
    }

    public String getAlteredText() {
        return AlteredText;
    }

    public Position getStart() {
        return Start;
    }

    public Position getEnd() {
        return End;
    }
}
