var source = 'kojiro musasi';
print("source: " + source.getClass());
var slice = source.slice(-6);
print("slice:  " + slice.getClass());
var cast = java.lang.String.class.cast(slice);
print(cast);