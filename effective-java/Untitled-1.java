class A{
    
}
class X extends A{
    private X instance = new X("default");
    static A getInstance(){
        if(instance == null){
            return new X();
        }
        return instance;
    }
    private X(String name){

    }
}