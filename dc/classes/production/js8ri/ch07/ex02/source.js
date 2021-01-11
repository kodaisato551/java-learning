var Paths = java.nio.file.Paths;
var Files = java.nio.file.Files;
var StandardCharsets = java.nio.charset.StandardCharsets;
var Arrays = java.util.Arrays;
var JString = java.lang.String;

var INPUT_FILE = "/Users/satokodai/Study/java-learning/js8ri/src/ch07/ex02/in.txt";
var LONG_WORD_LENGTH = 12;

var contents = new JString(Files.readAllBytes(Paths.get(INPUT_FILE)), StandardCharsets.UTF_8);
var stream = Arrays.asList(contents.split(/[\W]+/)).stream();
var result = stream.filter(function(w) {w.length() > LONG_WORD_LENGTH}).map(function(w){ w.toLowerCase()}).distinct().sorted();
result.forEach(function(w){ print(w)});


