function createMyArrayList() {
    return new (Java.extend(java.util.ArrayList)) {
      add: function(x) {
        print('Adding ' + x);
        return Java.super(arr).add(x);
      }
    }
  }
  var arr = createMyArrayList();
  arr.add("sato");
  arr.add("tanaka");
  arr.add("yamada");
  print(arr);