package de.hbrs.se.learning.console;

import de.hbrs.se.learning.service.RpnExecutor;
import de.hbrs.se.learning.service.RpnParser;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage:   java -jar rpn.jar rpn-expression");
            System.err.println("Example: java -jar rpn.jar '3 4 + 7 *'");
            System.exit(1);
        }

        RpnExecutor executor = new RpnExecutor(new RpnParser());

        try {
            System.out.println(executor.execute(args[0]));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
