package com.huston.springboot.crudgeneric;

public class Filter {
    String fieldName;
    String comparison;
    String value;

    public enum Comparison{
        EQUAL (" = "),
        LESSER_THAN (" > "),
        GREATER_THAN (" > "),
        LIKE (" LIKE "),
        IN (" IN "),
        BETWEEN (" BETWEEN ");
        String comparisionSymbol;

        private Comparison(String comparisionSymbol){
            this.comparisionSymbol = comparisionSymbol;
        }

        public String toString() {
            return this.comparisionSymbol;
        }
    }
}
