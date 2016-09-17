import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Statement {

    private final String name;
    private final List<StatementLineItem> items;

    public Statement(String name, List<StatementLineItem> items) {
        this.name = name;
        this.items = items;
    }

    public String render() {
        List<String> lines = new ArrayList<>();

        lines.add("Rental Record for " + name);
        lines.addAll(items.stream().map(i -> "\t" + i.title + "\t" + String.valueOf(i.price)).collect(toList()));
        lines.add("You owed " + String.valueOf(items.stream().collect(summingDouble(i -> i.price))));
        lines.add("You earned " + String.valueOf(items.stream().collect(summingInt(i -> i.points))) + " frequent renter points");

        return lines.stream().collect(joining("\n", "", "\n"));
    }
}
