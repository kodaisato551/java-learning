var printNextYear = function(x){
    print('Next year, you will be ' + (x + 1) + ' years old.');
};

if(argument.length = 1){
    printNextYear(parseInt(arguments[0]));
}else if('AGE' in $ENV){
    printNextYear(parseInt($ENV.AGE));
}else{
    print('input again');
}