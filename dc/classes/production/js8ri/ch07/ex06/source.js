function pipe(){
    var result;
    for (i = 0; i < arguments.length; i++) {
        result = $EXEC(arguments[i], result);
    }
    return result;
}
print(pipe('find .', 'grep .js', 'sort'));
